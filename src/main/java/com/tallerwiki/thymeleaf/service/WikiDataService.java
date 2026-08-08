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
                        ),

                        new Section(
                                "Registro de empresas",
                                "Cada organización puede registrarse en la plataforma y disponer de un "
                                        + "espacio de trabajo independiente para administrar sus propios "
                                        + "procesos, usuarios y roles."
                        ),

                        new Section(
                                "Seguridad y control de acceso",
                                "El acceso está protegido mediante autenticación y un control de acceso "
                                        + "por empresa y por rol: administrador, editor y usuario de "
                                        + "solo lectura."
                        ),

                        new Section(
                                "Nombre oficial y alcance del proyecto",
                                "El proyecto se conoce oficialmente como 'Editor de Procesos' (visor y editor "
                                        + "de procesos empresariales). Está orientado únicamente a la "
                                        + "visualización y edición de procesos, no a su ejecución: los usuarios "
                                        + "pueden consultar, crear, modificar y organizar procesos, pero el "
                                        + "sistema no dispara flujos automáticos ni tareas de procesamiento. "
                                        + "Su enfoque es académico, orientado a evaluar arquitectura web, "
                                        + "separación de responsabilidades, seguridad básica y buenas prácticas."
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
                        ),

                        new Section(
                                "Control de acceso por rol",
                                "La creación, edición y publicación de procesos depende del rol del usuario: "
                                        + "el editor puede modificar, el usuario de solo lectura únicamente "
                                        + "consulta, y el administrador administra la empresa."
                        ),

                        new Section(
                                "Pertenencia de los procesos",
                                "Cada proceso pertenece a una empresa específica y no a un usuario en "
                                        + "particular, lo que garantiza la consistencia y el control de la "
                                        + "evolución de los procesos dentro de la organización."
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
                        ),

                        new Section(
                                "Pools y lanes",
                                "El modelo puede organizarse en pools, que representan cada proceso o "
                                        + "participante, y dividirse en lanes según responsabilidades "
                                        + "funcionales de la empresa."
                        ),

                        new Section(
                                "Mensajes entre procesos",
                                "El sistema contempla el envío y la recepción de mensajes entre procesos, "
                                        + "notificaciones a sistemas externos y la correlación de los mensajes "
                                        + "con las instancias de proceso."
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
                        ),

                        new Section(
                                "Registro de usuarios en la empresa",
                                "Cada empresa administra sus propios usuarios. El administrador puede "
                                        + "registrar, consultar y activar o desactivar usuarios dentro "
                                        + "de su organización."
                        ),

                        new Section(
                                "Control de acceso por organización",
                                "Los recursos se limitan al espacio de trabajo de la empresa a la que "
                                        + "pertenece el usuario, evitando que la información de distintas "
                                        + "organizaciones se mezcle."
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
                        ),

                        new Section(
                                "Elementos complementarios",
                                "El modelo se amplía con el pool y sus lanes, los mensajes para la "
                                        + "comunicación entre procesos y hacia sistemas externos, y los "
                                        + "identificadores de correlación que vinculan cada mensaje "
                                        + "con la instancia de proceso correcta."
                        )
                )
        ));



        paginas.add(new WikiPage(
                "pools-y-lanes",
                "funcionalidades",
                "Pool y Lanes (Swimlanes)",
                "Organización de los procesos en pools y en lanes según responsabilidades.",
                List.of(

                        new Section(
                                "Configuración del pool",
                                "Cada empresa puede configurar los pools de sus procesos. El pool "
                                        + "delimita el contenido de cada proceso y define su alcance "
                                        + "dentro del espacio de trabajo."
                        ),

                        new Section(
                                "Diferencia entre pool y lane",
                                "El pool representa el proceso o participante completo, mientras que "
                                        + "el lane (swimlane) divide el pool en franjas que agrupan "
                                        + "las actividades por responsabilidad funcional."
                        ),

                        new Section(
                                "Compartir procesos entre pools",
                                "Un pool puede compartirse entre procesos respetando el alcance y los "
                                        + "límites: se define claramente qué actividades y mensajes "
                                        + "pertenecen a cada proceso."
                        ),

                        new Section(
                                "Roles y permisos en el pool",
                                "A cada pool se asocian los roles y permisos de la empresa, de modo "
                                        + "que solo los usuarios con el rol adecuado puedan participar "
                                        + "o modificar su contenido."
                        )
                )
        ));


        paginas.add(new WikiPage(
                "comunicacion-entre-procesos",
                "funcionalidades",
                "Comunicación y Mensajería entre Procesos",
                "Envío y recepción de mensajes entre procesos, notificaciones y correlación.",
                List.of(

                        new Section(
                                "Enviar mensaje (Message Throw)",
                                "Un proceso puede lanzar un mensaje hacia otro proceso o hacia un "
                                        + "sistema externo para indicar un evento o solicitar información."
                        ),

                        new Section(
                                "Notificaciones a sistemas externos",
                                "El envío de mensajes permite notificar a sistemas externos sobre "
                                        + "cambios o estados relevantes, por ejemplo al terminar "
                                        + "un proceso."
                        ),

                        new Section(
                                "Recibir mensaje (Message Catch)",
                                "Un proceso puede quedar a la espera de un mensaje y activarse cuando "
                                        + "lo recibe, permitiendo que los procesos se sincronicen entre sí."
                        ),

                        new Section(
                                "Correlación de mensajes",
                                "Cada mensaje incluye un identificador de correlación que permite "
                                        + "asociarlo con la instancia de proceso correcta y continuar "
                                        + "el flujo en el punto esperado."
                        )
                )
        ));



        paginas.add(new WikiPage(
                "plan-de-entregas",
                "resumen",
                "Plan de Entregas",
                "El proyecto se desarrolla de forma incremental, en tres entregas.",
                List.of(

                        new Section(
                                "Entrega 1 · Aplicación web con Spring Boot, Thymeleaf y JPA (14/09/2026)",
                                "Diseño e implementación de una aplicación web server-side estructurada, "
                                        + "aplicando el patrón MVC y el modelo en capas: modelo de datos con JPA, "
                                        + "lógica de negocio en servicios, vistas dinámicas con Thymeleaf para "
                                        + "gestionar empresas, usuarios y procesos, repositorios con JPQL y "
                                        + "named queries, controladores, validación y manejo de excepciones."
                        ),

                        new Section(
                                "Entrega 2 · API REST y frontend con Angular (21/10/2026)",
                                "Exposición de la lógica del sistema mediante servicios REST y desarrollo "
                                        + "de una interfaz en Angular que los consume: componentes reutilizables, "
                                        + "consumo de servicios con HttpClient y RxJS, formularios con paginación, "
                                        + "despliegue empaquetado en contenedores Docker y documentación de la "
                                        + "API con Swagger/OpenAPI."
                        ),

                        new Section(
                                "Entrega final · Seguridad, pruebas y despliegue (25/11/2026)",
                                "Autenticación y autorización con Spring Security garantizando el aislamiento "
                                        + "de información entre empresas, funcionamiento completo e integrado del "
                                        + "sistema, pruebas de integración y pruebas automatizadas de extremo a "
                                        + "extremo (E2E), despliegue final y presentación del proyecto."
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