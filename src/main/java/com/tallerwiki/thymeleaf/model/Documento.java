package com.tallerwiki.thymeleaf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Uno de los documentos de la entrega final del proyecto.
 * Los datos se cargan desde data.sql y el archivo vive en static/documentos/.
 */
@Entity
@Table(name = "documentos")
public class Documento {

    /** Número del documento según el enunciado (1 a 8); también define el orden. */
    @Id
    @Column(name = "numero")
    private Integer numero;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    /** Nombre exacto del archivo dentro de static/documentos/, por ejemplo 01_Resumen_Ejecutivo.docx. */
    @Column(name = "archivo", nullable = false, length = 100)
    private String archivo;

    /** No se guarda: lo calcula DocumentoService según si el archivo ya fue subido. */
    @Transient
    private boolean disponible;

    public Documento() {
    }

    public Documento(Integer numero, String titulo, String descripcion, String archivo) {
        this.numero = numero;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.archivo = archivo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
