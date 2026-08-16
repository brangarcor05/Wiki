-- Datos iniciales de la Wiki
-- Las tablas son creadas por Hibernate (ddl-auto=create)

-- Categorías
INSERT INTO categories (id, name, description) VALUES
('resumen', 'Resumen del Proyecto', 'Información general, propósito y alcance del sistema.'),
('funcionalidades', 'Funcionalidades', 'Principales características y capacidades del sistema.'),
('arquitectura', 'Arquitectura y Datos', 'Arquitectura propuesta, tecnologías y modelo de datos.');

-- Páginas
INSERT INTO wiki_pages (id, category_id, title, summary) VALUES
('descripcion-general', 'resumen', 'Descripción General', 'Sistema de Gestión de Procesos Multiempresa.'),
('gestion-procesos', 'funcionalidades', 'Gestión de Procesos', 'Administración de procesos organizacionales mediante operaciones CRUD, historial y trazabilidad.'),
('modelado-procesos', 'funcionalidades', 'Modelado de Procesos', 'Representación visual de procesos mediante actividades, arcos y gateways.'),
('multiempresa-seguridad', 'funcionalidades', 'Multiempresa y Seguridad', 'Cada empresa dispone de un espacio de trabajo independiente y protegido.'),
('arquitectura-propuesta', 'arquitectura', 'Arquitectura Propuesta', 'Descripción de la arquitectura tecnológica y organización interna del sistema.'),
('modelo-datos', 'arquitectura', 'Modelo de Datos', 'Principales entidades y relaciones identificadas para el sistema.'),
('pools-y-lanes', 'funcionalidades', 'Pool y Lanes (Swimlanes)', 'Organización de los procesos en pools y en lanes según responsabilidades.'),
('comunicacion-entre-procesos', 'funcionalidades', 'Comunicación y Mensajería entre Procesos', 'Envío y recepción de mensajes entre procesos, notificaciones y correlación.'),
('plan-de-entregas', 'resumen', 'Plan de Entregas', 'El proyecto se desarrolla de forma incremental, en tres entregas.');

-- Secciones de la página: Descripción General
INSERT INTO sections (id, wiki_page_id, title, content, posicion) VALUES
(1, 'descripcion-general', '¿Qué es?',
 'Es un sistema web orientado a la gestión y modelado de procesos organizacionales. '
 || 'Permite que diferentes empresas utilicen la plataforma manteniendo sus espacios de trabajo y su información separados.', 0),
(2, 'descripcion-general', 'Objetivo',
 'El objetivo principal es facilitar la creación, administración, consulta y seguimiento de procesos organizacionales mediante una plataforma centralizada.', 1),
(3, 'descripcion-general', 'Alcance',
 'El sistema contempla la gestión de empresas, usuarios, roles, procesos, actividades, decisiones e historial de cambios.', 2),
(4, 'descripcion-general', 'Beneficios',
 'La plataforma busca mejorar la organización de los procesos, facilitar la trazabilidad de los cambios y establecer diferentes niveles de acceso según el rol de cada usuario.', 3),
(5, 'descripcion-general', 'Registro de empresas',
 'Cada organización puede registrarse en la plataforma y disponer de un espacio de trabajo independiente para administrar sus propios procesos, usuarios y roles.', 4),
(6, 'descripcion-general', 'Seguridad y control de acceso',
 'El acceso está protegido mediante autenticación y un control de acceso por empresa y por rol: administrador, editor y usuario de solo lectura.', 5),
(7, 'descripcion-general', 'Nombre oficial y alcance del proyecto',
 'El proyecto se conoce oficialmente como ''Editor de Procesos'' (visor y editor de procesos empresariales). '
 || 'Está orientado únicamente a la visualización y edición de procesos, no a su ejecución: los usuarios pueden consultar, crear, modificar y organizar procesos, '
 || 'pero el sistema no dispara flujos automáticos ni tareas de procesamiento. '
 || 'Su enfoque es académico, orientado a evaluar arquitectura web, separación de responsabilidades, seguridad básica y buenas prácticas.', 6);

-- Secciones de la página: Gestión de Procesos
INSERT INTO sections (id, wiki_page_id, title, content, posicion) VALUES
(8, 'gestion-procesos', 'Creación de procesos',
 'Los usuarios autorizados pueden crear nuevos procesos indicando información como nombre, descripción, categoría y estado.', 0),
(9, 'gestion-procesos', 'Datos del proceso',
 'Cada proceso registra nombre, descripción, categoría y estado. Los estados contemplados inicialmente son borrador y publicado.', 1),
(10, 'gestion-procesos', 'Edición de procesos',
 'Los usuarios con permisos suficientes pueden modificar la información de un proceso existente.', 2),
(11, 'gestion-procesos', 'Historial y trazabilidad',
 'El sistema mantiene un historial de cambios para conocer las modificaciones realizadas sobre cada proceso.', 3),
(12, 'gestion-procesos', 'Búsqueda y filtros',
 'La plataforma permite realizar búsquedas y aplicar filtros para facilitar la consulta de procesos.', 4),
(13, 'gestion-procesos', 'Eliminación lógica',
 'Los procesos no necesariamente se eliminan físicamente. Se puede utilizar un estado inactivo para conservar la información histórica.', 5),
(14, 'gestion-procesos', 'Control de acceso por rol',
 'La creación, edición y publicación de procesos depende del rol del usuario: el editor puede modificar, el usuario de solo lectura únicamente consulta, y el administrador administra la empresa.', 6),
(15, 'gestion-procesos', 'Pertenencia de los procesos',
 'Cada proceso pertenece a una empresa específica y no a un usuario en particular, lo que garantiza la consistencia y el control de la evolución de los procesos dentro de la organización.', 7);

-- Secciones de la página: Modelado de Procesos
INSERT INTO sections (id, wiki_page_id, title, content, posicion) VALUES
(16, 'modelado-procesos', 'Actividades',
 'Representan las tareas que deben ejecutarse dentro de un proceso y pueden estar asociadas a roles funcionales.', 0),
(17, 'modelado-procesos', 'Arcos',
 'Representan las conexiones y la secuencia del flujo entre diferentes elementos del proceso.', 1),
(18, 'modelado-procesos', 'Gateways',
 'Representan puntos de decisión dentro del proceso y permiten establecer diferentes caminos de ejecución.', 2),
(19, 'modelado-procesos', 'Tipos de decisiones',
 'El modelo contempla ramificaciones exclusivas, paralelas o inclusivas dependiendo de la lógica requerida por el proceso.', 3),
(20, 'modelado-procesos', 'Gestión de roles',
 'Los roles funcionales pueden ser creados, consultados, modificados y eliminados cuando no estén siendo utilizados.', 4),
(21, 'modelado-procesos', 'Validación del modelo',
 'El sistema debe verificar que las relaciones entre actividades, arcos y gateways sean coherentes antes de publicar un proceso.', 5),
(22, 'modelado-procesos', 'Pools y lanes',
 'El modelo puede organizarse en pools, que representan cada proceso o participante, y dividirse en lanes según responsabilidades funcionales de la empresa.', 6),
(23, 'modelado-procesos', 'Mensajes entre procesos',
 'El sistema contempla el envío y la recepción de mensajes entre procesos, notificaciones a sistemas externos y la correlación de los mensajes con las instancias de proceso.', 7);

-- Secciones de la página: Multiempresa y Seguridad
INSERT INTO sections (id, wiki_page_id, title, content, posicion) VALUES
(24, 'multiempresa-seguridad', 'Registro independiente',
 'Cada organización puede registrarse en el sistema y disponer de su propio espacio de trabajo.', 0),
(25, 'multiempresa-seguridad', 'Roles de usuario',
 'El sistema contempla diferentes roles de acceso: administrador, editor y usuario de solo lectura.', 1),
(26, 'multiempresa-seguridad', 'Administrador',
 'Tiene permisos para administrar la configuración de la empresa, usuarios, roles y procesos.', 2),
(27, 'multiempresa-seguridad', 'Editor',
 'Puede crear y modificar información relacionada con los procesos de acuerdo con los permisos establecidos.', 3),
(28, 'multiempresa-seguridad', 'Solo lectura',
 'Puede consultar la información disponible, pero no realizar modificaciones.', 4),
(29, 'multiempresa-seguridad', 'Separación de información',
 'Los procesos y demás recursos pertenecen a una empresa específica, evitando que la información de diferentes organizaciones se mezcle.', 5),
(30, 'multiempresa-seguridad', 'Autenticación y acceso',
 'El acceso a la información debe estar condicionado por la identidad del usuario y los permisos asociados a su rol y empresa.', 6),
(31, 'multiempresa-seguridad', 'Registro de usuarios en la empresa',
 'Cada empresa administra sus propios usuarios. El administrador puede registrar, consultar y activar o desactivar usuarios dentro de su organización.', 7),
(32, 'multiempresa-seguridad', 'Control de acceso por organización',
 'Los recursos se limitan al espacio de trabajo de la empresa a la que pertenece el usuario, evitando que la información de distintas organizaciones se mezcle.', 8);

-- Secciones de la página: Arquitectura Propuesta
INSERT INTO sections (id, wiki_page_id, title, content, posicion) VALUES
(33, 'arquitectura-propuesta', 'Backend',
 'El backend se plantea utilizando Spring Boot y una arquitectura basada en el patrón MVC.', 0),
(34, 'arquitectura-propuesta', 'Modelo',
 'El modelo representa las entidades y los datos principales del sistema, como empresas, usuarios, procesos y roles.', 1),
(35, 'arquitectura-propuesta', 'Servicios',
 'La capa de servicios concentra operaciones relacionadas con la gestión y procesamiento de los datos.', 2),
(36, 'arquitectura-propuesta', 'Controladores',
 'Los controladores reciben las solicitudes HTTP, preparan la información necesaria y la envían a las vistas.', 3),
(37, 'arquitectura-propuesta', 'Vistas',
 'Las vistas se construyen utilizando Thymeleaf para generar contenido HTML dinámico a partir de la información proporcionada por el backend.', 4),
(38, 'arquitectura-propuesta', 'Persistencia',
 'En esta primera etapa se utilizan datos simulados almacenados en memoria. Posteriormente se contempla la integración con JPA/Hibernate y una base de datos.', 5);

-- Secciones de la página: Modelo de Datos
INSERT INTO sections (id, wiki_page_id, title, content, posicion) VALUES
(39, 'modelo-datos', 'Entidades principales',
 'Las principales entidades identificadas son Empresa, Usuario, Proceso, HistorialCambio, RolFuncional, Actividad, Arco y Gateway.', 0),
(40, 'modelo-datos', 'Empresa',
 'Representa una organización registrada en la plataforma y funciona como límite para la separación de la información.', 1),
(41, 'modelo-datos', 'Usuario',
 'Representa a una persona que tiene acceso al sistema y pertenece a una empresa determinada.', 2),
(42, 'modelo-datos', 'Proceso',
 'Representa un proceso organizacional y pertenece a una empresa específica.', 3),
(43, 'modelo-datos', 'Historial de cambios',
 'Registra las modificaciones realizadas sobre los procesos para mantener trazabilidad.', 4),
(44, 'modelo-datos', 'Rol funcional',
 'Representa los roles que pueden participar en las actividades de los procesos.', 5),
(45, 'modelo-datos', 'Actividad',
 'Representa una tarea o acción que forma parte del flujo de un proceso.', 6),
(46, 'modelo-datos', 'Arco',
 'Representa la conexión entre diferentes elementos del proceso.', 7),
(47, 'modelo-datos', 'Gateway',
 'Representa un punto de decisión o control dentro del flujo.', 8),
(48, 'modelo-datos', 'Relaciones clave',
 'Un Usuario pertenece a una Empresa; un Proceso pertenece a una Empresa; una Actividad está asociada a un RolFuncional; y un Arco conecta dos nodos, que pueden ser Actividades o Gateways.', 9),
(49, 'modelo-datos', 'Elementos complementarios',
 'El modelo se amplía con el pool y sus lanes, los mensajes para la comunicación entre procesos y hacia sistemas externos, y los identificadores de correlación que vinculan cada mensaje con la instancia de proceso correcta.', 10);

-- Secciones de la página: Pool y Lanes (Swimlanes)
INSERT INTO sections (id, wiki_page_id, title, content, posicion) VALUES
(50, 'pools-y-lanes', 'Configuración del pool',
 'Cada empresa puede configurar los pools de sus procesos. El pool delimita el contenido de cada proceso y define su alcance dentro del espacio de trabajo.', 0),
(51, 'pools-y-lanes', 'Diferencia entre pool y lane',
 'El pool representa el proceso o participante completo, mientras que el lane (swimlane) divide el pool en franjas que agrupan las actividades por responsabilidad funcional.', 1),
(52, 'pools-y-lanes', 'Compartir procesos entre pools',
 'Un pool puede compartirse entre procesos respetando el alcance y los límites: se define claramente qué actividades y mensajes pertenecen a cada proceso.', 2),
(53, 'pools-y-lanes', 'Roles y permisos en el pool',
 'A cada pool se asocian los roles y permisos de la empresa, de modo que solo los usuarios con el rol adecuado puedan participar o modificar su contenido.', 3);

-- Secciones de la página: Comunicación y Mensajería entre Procesos
INSERT INTO sections (id, wiki_page_id, title, content, posicion) VALUES
(54, 'comunicacion-entre-procesos', 'Enviar mensaje (Message Throw)',
 'Un proceso puede lanzar un mensaje hacia otro proceso o hacia un sistema externo para indicar un evento o solicitar información.', 0),
(55, 'comunicacion-entre-procesos', 'Notificaciones a sistemas externos',
 'El envío de mensajes permite notificar a sistemas externos sobre cambios o estados relevantes, por ejemplo al terminar un proceso.', 1),
(56, 'comunicacion-entre-procesos', 'Recibir mensaje (Message Catch)',
 'Un proceso puede quedar a la espera de un mensaje y activarse cuando lo recibe, permitiendo que los procesos se sincronicen entre sí.', 2),
(57, 'comunicacion-entre-procesos', 'Correlación de mensajes',
 'Cada mensaje incluye un identificador de correlación que permite asociarlo con la instancia de proceso correcta y continuar el flujo en el punto esperado.', 3);

-- Secciones de la página: Plan de Entregas
INSERT INTO sections (id, wiki_page_id, title, content, posicion) VALUES
(58, 'plan-de-entregas', 'Entrega 1 · Aplicación web con Spring Boot, Thymeleaf y JPA (14/09/2026)',
 'Diseño e implementación de una aplicación web server-side estructurada, aplicando el patrón MVC y el modelo en capas: modelo de datos con JPA, '
 || 'lógica de negocio en servicios, vistas dinámicas con Thymeleaf para gestionar empresas, usuarios y procesos, repositorios con JPQL y named queries, '
 || 'controladores, validación y manejo de excepciones.', 0),
(59, 'plan-de-entregas', 'Entrega 2 · API REST y frontend con Angular (21/10/2026)',
 'Exposición de la lógica del sistema mediante servicios REST y desarrollo de una interfaz en Angular que los consume: componentes reutilizables, '
 || 'consumo de servicios con HttpClient y RxJS, formularios con paginación, despliegue empaquetado en contenedores Docker y documentación de la API con Swagger/OpenAPI.', 1),
(60, 'plan-de-entregas', 'Entrega final · Seguridad, pruebas y despliegue (25/11/2026)',
 'Autenticación y autorización con Spring Security garantizando el aislamiento de información entre empresas, funcionamiento completo e integrado del sistema, '
 || 'pruebas de integración y pruebas automatizadas de extremo a extremo (E2E), despliegue final y presentación del proyecto.', 2);

-- Equipo
INSERT INTO miembros (nombre, rol, correo, imagen) VALUES
('Elieen Rodriguez', 'Líder de proyecto', '', 'Eileen.jpg'),
('Brandon Garcia', 'Desarrollador', '', 'brandon.jpg'),
('Carol Robayo', 'Desarrolladora', '', 'carol.jpg'),
('Juliana Aguirre', 'Desarrolladora', '', 'juliana.jpg'),
('Juan Santamaria', 'Desarrollador', '', 'juan.jpg');
