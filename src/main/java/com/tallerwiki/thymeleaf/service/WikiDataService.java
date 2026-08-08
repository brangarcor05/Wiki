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


    public List<Category> obtenerCategorias() {
        return categorias;
    }

    public Optional<Category> obtenerCategoriaPorId(String id) {
        return categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }


    public List<WikiPage> obtenerPaginas() {
        return paginas;
    }

    public Optional<WikiPage> obtenerPaginaPorId(String id) {
        return paginas.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<WikiPage> obtenerPaginasPorCategoria(String categoryId) {
        return paginas.stream()
                .filter(p -> p.getCategoryId().equals(categoryId))
                .toList();
    }


    public List<Miembro> obtenerEquipo() {
        return equipo;
    }


    private void cargarCategorias() {

        categorias.add(new Category(
                "resumen",
                "Resumen del Proyecto",
                "Información general, propósito y alcance del sistema."
        ));

        categorias.add(new Category(
                "funcionalidades",
                "Funcionalidades",
                "Principales características y capacidades del sistema."
        ));

        categorias.add(new Category(
                "arquitectura",
                "Arquitectura y Datos",
                "Arquitectura propuesta, tecnologías y modelo de datos."
        ));
    }

    private void cargarPaginas() {


        paginas.add(new WikiPage(
                "descripcion-general",
                "resumen",
                "Descripción General",
                "Sistema de Gestión de Procesos Multiempresa.",
                List.of(

                        new Section(
                                "¿Qué es?",
                                "Es un sistema web orientado a la gestión y modelado de procesos organizacionales. "
                                        + "Permite que diferentes empresas utilicen la plataforma manteniendo sus "
                                        + "espacios de trabajo y su información separados."
                        ),

                        new Section(
                                "Objetivo",
                                "El objetivo principal es facilitar la creación, administración, consulta y "
                                        + "seguimiento de procesos organizacionales mediante una plataforma "
                                        + "centralizada."
                        ),

                        new Section(
                                "Alcance",
                                "El sistema contempla la gestión de empresas, usuarios, roles, procesos, "
                                        + "actividades, decisiones e historial de cambios."
                        ),

                        new Section(
                                "Beneficios",
                                "La plataforma busca mejorar la organización de los procesos, facilitar "
                                        + "la trazabilidad de los cambios y establecer diferentes niveles "
                                        + "de acceso según el rol de cada usuario."
                        )
                )
        ));



        paginas.add(new WikiPage(
                "gestion-procesos",
                "funcionalidades",
                "Gestión de Procesos",
                "Administración de procesos organizacionales mediante operaciones CRUD, "
                        + "historial y trazabilidad.",
                List.of(

                        new Section(
                                "Creación de procesos",
                                "Los usuarios autorizados pueden crear nuevos procesos indicando "
                                        + "información como nombre, descripción, categoría y estado."
                        ),

                        new Section(
                                "Datos del proceso",
                                "Cada proceso registra nombre, descripción, categoría y estado. "
                                        + "Los estados contemplados inicialmente son borrador y publicado."
                        ),

                        new Section(
                                "Edición de procesos",
                                "Los usuarios con permisos suficientes pueden modificar la información "
                                        + "de un proceso existente."
                        ),

                        new Section(
                                "Historial y trazabilidad",
                                "El sistema mantiene un historial de cambios para conocer las modificaciones "
                                        + "realizadas sobre cada proceso."
                        ),

                        new Section(
                                "Búsqueda y filtros",
                                "La plataforma permite realizar búsquedas y aplicar filtros para facilitar "
                                        + "la consulta de procesos."
                        ),

                        new Section(
                                "Eliminación lógica",
                                "Los procesos no necesariamente se eliminan físicamente. Se puede utilizar "
                                        + "un estado inactivo para conservar la información histórica."
                        )
                )
        ));



        paginas.add(new WikiPage(
                "modelado-procesos",
                "funcionalidades",
                "Modelado de Procesos",
                "Representación visual de procesos mediante actividades, arcos y gateways.",
                List.of(

                        new Section(
                                "Actividades",
                                "Representan las tareas que deben ejecutarse dentro de un proceso "
                                        + "y pueden estar asociadas a roles funcionales."
                        ),

                        new Section(
                                "Arcos",
                                "Representan las conexiones y la secuencia del flujo entre diferentes "
                                        + "elementos del proceso."
                        ),

                        new Section(
                                "Gateways",
                                "Representan puntos de decisión dentro del proceso y permiten establecer "
                                        + "diferentes caminos de ejecución."
                        ),

                        new Section(
                                "Tipos de decisiones",
                                "El modelo contempla ramificaciones exclusivas, paralelas o inclusivas "
                                        + "dependiendo de la lógica requerida por el proceso."
                        ),

                        new Section(
                                "Gestión de roles",
                                "Los roles funcionales pueden ser creados, consultados, modificados "
                                        + "y eliminados cuando no estén siendo utilizados."
                        ),

                        new Section(
                                "Validación del modelo",
                                "El sistema debe verificar que las relaciones entre actividades, arcos "
                                        + "y gateways sean coherentes antes de publicar un proceso."
                        )
                )
        ));



        paginas.add(new WikiPage(
                "multiempresa-seguridad",
                "funcionalidades",
                "Multiempresa y Seguridad",
                "Cada empresa dispone de un espacio de trabajo independiente y protegido.",
                List.of(

                        new Section(
                                "Registro independiente",
                                "Cada organización puede registrarse en el sistema y disponer de "
                                        + "su propio espacio de trabajo."
                        ),

                        new Section(
                                "Roles de usuario",
                                "El sistema contempla diferentes roles de acceso: administrador, "
                                        + "editor y usuario de solo lectura."
                        ),

                        new Section(
                                "Administrador",
                                "Tiene permisos para administrar la configuración de la empresa, "
                                        + "usuarios, roles y procesos."
                        ),

                        new Section(
                                "Editor",
                                "Puede crear y modificar información relacionada con los procesos "
                                        + "de acuerdo con los permisos establecidos."
                        ),

                        new Section(
                                "Solo lectura",
                                "Puede consultar la información disponible, pero no realizar "
                                        + "modificaciones."
                        ),

                        new Section(
                                "Separación de información",
                                "Los procesos y demás recursos pertenecen a una empresa específica, "
                                        + "evitando que la información de diferentes organizaciones se mezcle."
                        ),

                        new Section(
                                "Autenticación y acceso",
                                "El acceso a la información debe estar condicionado por la identidad "
                                        + "del usuario y los permisos asociados a su rol y empresa."
                        )
                )
        ));



        paginas.add(new WikiPage(
                "arquitectura-propuesta",
                "arquitectura",
                "Arquitectura Propuesta",
                "Descripción de la arquitectura tecnológica y organización interna del sistema.",
                List.of(

                        new Section(
                                "Backend",
                                "El backend se plantea utilizando Spring Boot y una arquitectura "
                                        + "basada en el patrón MVC."
                        ),

                        new Section(
                                "Modelo",
                                "El modelo representa las entidades y los datos principales del "
                                        + "sistema, como empresas, usuarios, procesos y roles."
                        ),

                        new Section(
                                "Servicios",
                                "La capa de servicios concentra operaciones relacionadas con la "
                                        + "gestión y procesamiento de los datos."
                        ),

                        new Section(
                                "Controladores",
                                "Los controladores reciben las solicitudes HTTP, preparan la información "
                                        + "necesaria y la envían a las vistas."
                        ),

                        new Section(
                                "Vistas",
                                "Las vistas se construyen utilizando Thymeleaf para generar contenido "
                                        + "HTML dinámico a partir de la información proporcionada por el backend."
                        ),

                        new Section(
                                "Persistencia",
                                "En esta primera etapa se utilizan datos simulados almacenados en memoria. "
                                        + "Posteriormente se contempla la integración con JPA/Hibernate y una base de datos."
                        )
                )
        ));



        paginas.add(new WikiPage(
                "modelo-datos",
                "arquitectura",
                "Modelo de Datos",
                "Principales entidades y relaciones identificadas para el sistema.",
                List.of(

                        new Section(
                                "Entidades principales",
                                "Las principales entidades identificadas son Empresa, Usuario, Proceso, "
                                        + "HistorialCambio, RolFuncional, Actividad, Arco y Gateway."
                        ),

                        new Section(
                                "Empresa",
                                "Representa una organización registrada en la plataforma y funciona "
                                        + "como límite para la separación de la información."
                        ),

                        new Section(
                                "Usuario",
                                "Representa a una persona que tiene acceso al sistema y pertenece "
                                        + "a una empresa determinada."
                        ),

                        new Section(
                                "Proceso",
                                "Representa un proceso organizacional y pertenece a una empresa específica."
                        ),

                        new Section(
                                "Historial de cambios",
                                "Registra las modificaciones realizadas sobre los procesos para "
                                        + "mantener trazabilidad."
                        ),

                        new Section(
                                "Rol funcional",
                                "Representa los roles que pueden participar en las actividades "
                                        + "de los procesos."
                        ),

                        new Section(
                                "Actividad",
                                "Representa una tarea o acción que forma parte del flujo de un proceso."
                        ),

                        new Section(
                                "Arco",
                                "Representa la conexión entre diferentes elementos del proceso."
                        ),

                        new Section(
                                "Gateway",
                                "Representa un punto de decisión o control dentro del flujo."
                        ),

                        new Section(
                                "Relaciones clave",
                                "Un Usuario pertenece a una Empresa; un Proceso pertenece a una Empresa; "
                                        + "una Actividad está asociada a un RolFuncional; y un Arco conecta "
                                        + "dos nodos, que pueden ser Actividades o Gateways."
                        )
                )
        ));
    }


    private void cargarEquipo() {

        equipo.add(new Miembro(
                "Elieen",
                "Líder de proyecto",
                ""
        ));

        equipo.add(new Miembro(
                "Brandon",
                "Desarrollador",
                ""
        ));

        equipo.add(new Miembro(
                "Carol",
                "Desarrolladora",
                ""
        ));

        equipo.add(new Miembro(
                "Juliana",
                "Desarrolladora",
                ""
        ));

        equipo.add(new Miembro(
                "Juan",
                "Desarrollador",
                ""
        ));
    }
}