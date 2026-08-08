package com.tallerwiki.thymeleaf.service;

import com.tallerwiki.thymeleaf.model.Category;
import com.tallerwiki.thymeleaf.model.Miembro;
import com.tallerwiki.thymeleaf.model.Section;
import com.tallerwiki.thymeleaf.model.WikiPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WikiDataService {

    private final List<Category> categorias = new ArrayList<>();
    private final List<WikiPage> paginas = new ArrayList<>();
    private final List<Miembro> equipo = new ArrayList<>();

    public WikiDataService() {
        cargarCategorias();
        cargarPaginas();
        cargarEquipo();
    }

    // ---------- Categorías ----------

    public List<Category> obtenerCategorias() {
        return categorias;
    }

    public Optional<Category> obtenerCategoriaPorId(String id) {
        return categorias.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    // ---------- Páginas ----------

    public List<WikiPage> obtenerPaginas() {
        return paginas;
    }

    public Optional<WikiPage> obtenerPaginaPorId(String id) {
        return paginas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<WikiPage> obtenerPaginasPorCategoria(String categoryId) {
        return paginas.stream()
                .filter(p -> p.getCategoryId().equals(categoryId))
                .toList();
    }

    // ---------- Equipo ----------

    public List<Miembro> obtenerEquipo() {
        return equipo;
    }

    // ---------- Carga de datos simulados ----------

    private void cargarCategorias() {
        categorias.add(new Category("resumen", "Resumen del Proyecto",
                "Idea general del sistema que se va a desarrollar."));
        categorias.add(new Category("funcionalidades", "Funcionalidades",
                "Qué hace el sistema: gestión de procesos, modelado y multiempresa."));
        categorias.add(new Category("arquitectura", "Arquitectura y Datos",
                "Cómo se propone construir el sistema y qué entidades maneja."));
    }

    private void cargarPaginas() {
        // ---- Resumen ----
        paginas.add(new WikiPage("descripcion-general", "resumen", "Descripción General",
                "Sistema de Gestión de Procesos multiempresa.",
                List.of(
                        new Section("¿Qué es?",
                                "Un sistema que permite a cada organización (empresa) registrarse, tener su propio espacio independiente y administrar usuarios con distintos roles: administrador, editor y solo lectura."),
                        new Section("Objetivo",
                                "Garantizar autenticación segura, control de acceso por empresa y separación de la información, de modo que los procesos pertenezcan a la empresa y no a usuarios individuales.")
                )));

        // ---- Funcionalidades ----
        paginas.add(new WikiPage("gestion-procesos", "funcionalidades", "Gestión de Procesos",
                "CRUD de procesos organizacionales con historial y trazabilidad.",
                List.of(
                        new Section("Datos del proceso",
                                "Cada proceso registra nombre, descripción, categoría y estado (borrador o publicado)."),
                        new Section("Historial y trazabilidad",
                                "El sistema mantiene un historial de cambios sobre cada proceso."),
                        new Section("Búsqueda y eliminación lógica",
                                "Permite búsquedas y filtros, y usa eliminación lógica (estado inactivo) para no perder información histórica.")
                )));

        paginas.add(new WikiPage("modelado-procesos", "funcionalidades", "Modelado de Procesos",
                "Actividades, arcos y gateways para representar el flujo de un proceso.",
                List.of(
                        new Section("Actividades",
                                "Están asociadas a roles funcionales definidos por cada empresa."),
                        new Section("Arcos",
                                "Representan la secuencia del flujo entre actividades y decisiones."),
                        new Section("Gateways (decisiones)",
                                "Permiten ramificaciones exclusivas, paralelas o inclusivas."),
                        new Section("Gestión de roles",
                                "Se pueden crear, editar, consultar y eliminar roles, validando que no estén en uso antes de eliminarlos.")
                )));

        paginas.add(new WikiPage("multiempresa-seguridad", "funcionalidades", "Multiempresa y Seguridad",
                "Cada empresa tiene su espacio propio, aislado de las demás.",
                List.of(
                        new Section("Registro independiente",
                                "Cada organización se registra y obtiene su propio espacio de trabajo."),
                        new Section("Roles de usuario",
                                "Administrador, editor y solo lectura, con distintos niveles de acceso."),
                        new Section("Separación de información",
                                "Los procesos pertenecen a la empresa, no a un usuario individual, y no se mezclan datos entre empresas.")
                )));

        // ---- Arquitectura y Datos ----
        paginas.add(new WikiPage("arquitectura-propuesta", "arquitectura", "Arquitectura Propuesta",
                "Cómo se plantea construir el sistema.",
                List.of(
                        new Section("Backend", "Spring Boot, siguiendo el patrón MVC (modelo / servicio / controlador)."),
                        new Section("Persistencia", "En una primera etapa, datos simulados en memoria; más adelante se migrará a JPA/Hibernate con base de datos."),
                        new Section("Vistas", "Esta misma Wiki, construida con Thymeleaf, es la que presenta la idea del proyecto.")
                )));

        paginas.add(new WikiPage("modelo-datos", "arquitectura", "Modelo de Datos",
                "Entidades principales identificadas para el sistema.",
                List.of(
                        new Section("Entidades", "Empresa, Usuario, Proceso, HistorialCambio, RolFuncional, Actividad, Arco y Gateway."),
                        new Section("Relaciones clave", "Un Usuario pertenece a una Empresa; un Proceso pertenece a una Empresa; una Actividad está asociada a un RolFuncional; un Arco conecta dos nodos (Actividad o Gateway).")
                )));
    }

    private void cargarEquipo() {
        equipo.add(new Miembro("Elieen", "Líder de proyecto", ""));
        equipo.add(new Miembro("Brandon", "Desarrollador", ""));
        equipo.add(new Miembro("Carol", "Desarrolladora", ""));
        equipo.add(new Miembro("Juliana", "Desarrolladora", ""));
        equipo.add(new Miembro("Juan", "Desarrollador", ""));
    }
}
