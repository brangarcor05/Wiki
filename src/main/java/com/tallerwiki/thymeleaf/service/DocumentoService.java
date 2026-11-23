package com.tallerwiki.thymeleaf.service;

import com.tallerwiki.thymeleaf.model.Documento;
import com.tallerwiki.thymeleaf.repository.DocumentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Documentos de la entrega final. Los datos (título, descripción, nombre del archivo)
 * vienen de la base de datos; el archivo se publica desde static/documentos/ para
 * descargarlo y se convierte a HTML con Mammoth para verlo dentro de la Wiki.
 */
@Service
public class DocumentoService {

    private static final Logger log = LoggerFactory.getLogger(DocumentoService.class);

    /** Carpeta dentro del classpath donde se guardan los archivos para descargar. */
    public static final String CARPETA = "static/documentos/";

    private final DocumentoRepository documentoRepository;
    /**
     * Mammoth ya convierte "Título 1, 2, 3..." en h1, h2, h3. Se agregan los estilos
     * "Título" y "Subtítulo" de la portada de las plantillas, que no trae por defecto.
     */
    private final DocumentConverter convertidor = new DocumentConverter()
            .addStyleMap("p[style-name='Title'] => h1.documento-portada:fresh\n"
                    + "p[style-name='Subtitle'] => p.documento-subtitulo:fresh");

    /**
     * HTML ya convertido, por nombre de archivo. Los archivos viajan dentro de la imagen
     * y no cambian mientras la aplicación corre, así que cada uno se convierte una sola vez.
     */
    private final Map<String, String> htmlPorArchivo = new ConcurrentHashMap<>();

    @Autowired
    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    /** Lista los documentos en orden y marca cuáles ya tienen su archivo subido. */
    public List<Documento> obtenerDocumentos() {
        List<Documento> documentos = documentoRepository.findAllByOrderByNumeroAsc();
        documentos.forEach(this::marcarDisponibilidad);
        return documentos;
    }

    public Optional<Documento> obtenerDocumento(Integer numero) {
        Optional<Documento> documento = documentoRepository.findById(numero);
        documento.ifPresent(this::marcarDisponibilidad);
        return documento;
    }

    /**
     * Contenido del documento como HTML (títulos, párrafos, listas, tablas e imágenes).
     * Devuelve vacío si el archivo no existe o no se pudo leer.
     */
    public Optional<String> obtenerHtml(Documento documento) {
        if (!archivoExiste(documento.getArchivo())) {
            return Optional.empty();
        }
        try {
            return Optional.of(htmlPorArchivo.computeIfAbsent(documento.getArchivo(), this::convertir));
        } catch (IllegalStateException e) {
            log.error("No se pudo convertir {} a HTML", documento.getArchivo(), e.getCause());
            return Optional.empty();
        }
    }

    private String convertir(String archivo) {
        try (InputStream entrada = new ClassPathResource(CARPETA + archivo).getInputStream()) {
            Result<String> resultado = convertidor.convertToHtml(entrada);
            if (!resultado.getWarnings().isEmpty()) {
                log.debug("Avisos al convertir {}: {}", archivo, resultado.getWarnings());
            }
            return resultado.getValue();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void marcarDisponibilidad(Documento documento) {
        documento.setDisponible(archivoExiste(documento.getArchivo()));
    }

    private boolean archivoExiste(String archivo) {
        return archivo != null && new ClassPathResource(CARPETA + archivo).exists();
    }
}
