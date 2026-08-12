# Wiki — Sistema de Gestión de Procesos Multiempresa

## 1. Descripción del proyecto

Este proyecto corresponde a una **Wiki web server-side desarrollada con Spring Boot y Thymeleaf**.

La Wiki funciona como un portal de documentación técnica del proyecto **Sistema de Gestión de Procesos Multiempresa**. Su objetivo es presentar de manera organizada la idea del sistema, sus funcionalidades, arquitectura, modelo de datos y equipo de desarrollo.

La aplicación sigue una arquitectura MVC básica:

- **Modelo:** representa los datos de la Wiki.
- **Servicio:** administra y entrega los datos que necesita la aplicación.
- **Controladores:** reciben las solicitudes HTTP y preparan la información para las vistas.
- **Vistas Thymeleaf:** renderizan dinámicamente la información en HTML.
- **Recursos estáticos:** contienen CSS y JavaScript.

Actualmente los datos de la Wiki se encuentran **simulados en memoria**, por lo que no se utiliza todavía una base de datos.

---

## 2. Tecnologías utilizadas

- Java
- Spring Boot
- Spring MVC
- Thymeleaf
- Maven
- HTML5
- CSS3
- JavaScript
- Docker (requisito de despliegue del taller)

---

## 3. Estructura general del proyecto

```text
thymeleaf/
│
├── .mvn/
│   └── wrapper/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── tallerwiki/
│   │   │           └── thymeleaf/
│   │   │               ├── controller/
│   │   │               ├── model/
│   │   │               ├── service/
│   │   │               ├── ServletInitializer.java
│   │   │               └── ThymeleafApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── style.css
│   │       │   └── js/
│   │       │       └── contacto.js
│   │       │
│   │       ├── templates/
│   │       │   ├── fragments/
│   │       │   │   ├── header.html
│   │       │   │   ├── menu.html
│   │       │   │   └── footer.html
│   │       │   │
│   │       │   ├── index.html
│   │       │   ├── wiki-lista.html
│   │       │   ├── wiki-detalle.html
│   │       │   ├── categoria-detalle.html
│   │       │   ├── equipo.html
│   │       │   └── contacto.html
│   │       │
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│
├── .gitignore
├── .gitattributes
├── mvnw
├── mvnw.cmd
└── pom.xml
```

---

# 4. Arquitectura MVC

La aplicación está organizada siguiendo el patrón **Modelo–Vista–Controlador (MVC)**.

```text
Navegador
    │
    │ HTTP Request
    ▼
Controller
    │
    │ solicita datos
    ▼
WikiDataService
    │
    │ obtiene datos
    ▼
Modelos
    │
    │ datos preparados
    ▼
Controller
    │
    │ Model
    ▼
Thymeleaf View
    │
    │ HTML renderizado
    ▼
Navegador
```

### Flujo general

1. El usuario entra a una URL.
2. Spring identifica el controlador correspondiente.
3. El controlador solicita información al `WikiDataService`.
4. El servicio obtiene los datos desde las listas en memoria.
5. El controlador agrega los datos al objeto `Model`.
6. Thymeleaf recibe el `Model`.
7. La plantilla HTML utiliza expresiones `th:*` para mostrar los datos.
8. Spring devuelve el HTML renderizado al navegador.

---

# 5. Paquete `model`

Ubicación:

```text
src/main/java/com/tallerwiki/thymeleaf/model/
```

Este paquete contiene las clases que representan los datos que utiliza la Wiki. Actualmente se manejan cuatro modelos: `Category`, `Miembro`, `Section` y `WikiPage`.

---

## `Category.java`

Representa una categoría de documentación de la Wiki.

Atributos:

```text
id
name
description
```

### Responsabilidad

Permite agrupar las páginas de la Wiki por una categoría y proporcionar una descripción general de cada grupo de contenido.

Actualmente las categorías se cargan desde `WikiDataService`.

---

## `Miembro.java`

Representa a un integrante del equipo de desarrollo.

Atributos:

```text
nombre
rol
correo
```

### Responsabilidad

Permite almacenar y presentar la información básica de los integrantes del equipo.

El atributo `correo` está disponible para que posteriormente pueda mostrarse en la vista `equipo.html`.

Actualmente los integrantes se cargan desde `WikiDataService`.

---

## `Section.java`

Representa una sección individual dentro de una página de la Wiki.

Atributos:

```text
title
content
```

### Responsabilidad

Permite dividir el contenido de una `WikiPage` en diferentes secciones.

Por ejemplo, una página puede contener:

```text
Página
 ├── ¿Qué es?
 ├── Objetivo
 └── Características
```

Cada una de esas partes puede representarse mediante un objeto `Section`.

---

## `WikiPage.java`

Representa una página completa de la Wiki.

Atributos:

```text
id
categoryId
title
summary
sections
```

Donde:

- `id`: identificador único de la página.
- `categoryId`: identificador de la categoría a la que pertenece.
- `title`: título de la página.
- `summary`: resumen o descripción corta.
- `sections`: lista de objetos `Section` que contiene el contenido de la página.

`sections` se inicializa como una lista:

```java
private List<Section> sections = new ArrayList<>();
```

Esto permite que una página tenga múltiples secciones.

---

## Relación entre los modelos

La estructura actual puede entenderse de la siguiente manera:

```text
Category
   │
   │ categoryId
   ▼
WikiPage
   │
   │ contiene
   ▼
Section
```

Por separado:

```text
Miembro
   │
   ├── nombre
   ├── rol
   └── correo
```

### Relación `Category` → `WikiPage`

Una `WikiPage` guarda el identificador de su categoría mediante:

```java
private String categoryId;
```

El servicio utiliza ese identificador para encontrar las páginas pertenecientes a una categoría.

### Relación `WikiPage` → `Section`

Una `WikiPage` contiene una lista de secciones:

```java
private List<Section> sections;
```

Por lo tanto, una página puede tener múltiples secciones.

### `Miembro`

`Miembro` no depende directamente de `Category`, `WikiPage` o `Section`.

Es utilizado para representar la información del equipo y se entrega a `EquipoController` para ser renderizado en `equipo.html`.

---

## Constructores y getters/setters

Los cuatro modelos cuentan con:

- constructor vacío
- constructor con los atributos principales
- getters
- setters

Esto permite crear los objetos manualmente desde `WikiDataService` y acceder a sus atributos desde Thymeleaf mediante las propiedades correspondientes.

Ejemplo:

```java
new Category(
    "resumen",
    "Resumen del Proyecto",
    "Idea general del sistema que se va a desarrollar."
);
```

Y una página puede construirse con:

```java
new WikiPage(
    "descripcion-general",
    "resumen",
    "Descripción General",
    "Sistema de Gestión de Procesos multiempresa.",
    List.of(...)
);
```

---

## Uso desde Thymeleaf

Los atributos de estos modelos pueden ser utilizados directamente desde las vistas.

### Category

```html
<h2 th:text="${categoria.name}"></h2>
<p th:text="${categoria.description}"></p>
```

### WikiPage

```html
<h1 th:text="${pagina.title}"></h1>
<p th:text="${pagina.summary}"></p>
```

### Section

```html
<div th:each="seccion : ${pagina.sections}">
    <h2 th:text="${seccion.title}"></h2>
    <p th:text="${seccion.content}"></p>
</div>
```

### Miembro

```html
<div th:each="miembro : ${equipo}">
    <h2 th:text="${miembro.nombre}"></h2>
    <p th:text="${miembro.rol}"></p>
    <p th:text="${miembro.correo}"></p>
</div>
```

Estas propiedades corresponden a los getters de los modelos.

---

# 6. Paquete `service`

Ubicación:

```text
src/main/java/com/tallerwiki/thymeleaf/service/
```

## `WikiDataService.java`

Es el servicio principal encargado de administrar los datos de la Wiki.

Actualmente trabaja con:

```java
List<Category> categorias
List<WikiPage> paginas
List<Miembro> equipo
```

Los datos se cargan cuando se crea el servicio.

### Responsabilidades

El servicio proporciona métodos para:

- obtener todas las categorías
- buscar una categoría por ID
- obtener todas las páginas
- buscar una página por ID
- obtener páginas pertenecientes a una categoría
- obtener los integrantes del equipo

### Importante

Los controladores **no deben contener directamente los datos de la Wiki**.

La idea es mantener la responsabilidad de acceso a los datos dentro de `WikiDataService`.

Esto permitirá posteriormente reemplazar las listas en memoria por una base de datos sin tener que modificar toda la lógica de las vistas.

---

# 7. Paquete `controller`

Ubicación:

```text
src/main/java/com/tallerwiki/thymeleaf/controller/
```

Los controladores reciben las solicitudes HTTP y conectan el servicio con las vistas Thymeleaf.

---

## `HomeController.java`

Ruta principal:

```text
/
```

Vista:

```text
index.html
```

Prepara información como:

- título
- descripción
- categorías
- cantidad total de páginas

Ejemplo conceptual:

```java
model.addAttribute("titulo", ...);
model.addAttribute("descripcion", ...);
model.addAttribute("categorias", ...);
model.addAttribute("totalPaginas", ...);
```

---

## `WikiController.java`

Controla las páginas principales de la Wiki.

### Listado

Ruta:

```text
/wiki
```

Vista:

```text
wiki-lista.html
```

Envía:

```text
paginas
```

### Detalle

Ruta:

```text
/wiki/{id}
```

Vista:

```text
wiki-detalle.html
```

Envía:

```text
pagina
```

Si la página no existe, redirige a:

```text
/wiki
```

---

## `CategoryController.java`

Controla el detalle de una categoría.

Ruta:

```text
/categoria/{id}
```

Vista:

```text
categoria-detalle.html
```

Envía:

```text
categoria
paginas
```

Las páginas enviadas corresponden únicamente a la categoría seleccionada.

---

## `EquipoController.java`

Controla la página del equipo.

Ruta:

```text
/equipo
```

Vista:

```text
equipo.html
```

Envía:

```text
equipo
```

La vista puede recorrer la lista mediante `th:each`.

---

## `ContactController.java`

Controla la página de contacto.

Ruta:

```text
/contacto
```

Vista:

```text
contacto.html
```

Actualmente el controlador únicamente presenta la vista.

La validación del formulario se realiza en JavaScript.

---

# 8. Vistas Thymeleaf

Ubicación:

```text
src/main/resources/templates/
```

Las vistas son archivos HTML procesados por Thymeleaf.

---

## `index.html`

Es la página principal.

Debe presentar:

- nombre del proyecto
- descripción general
- resumen de la Wiki
- categorías disponibles
- cantidad de páginas
- navegación hacia las demás secciones

Utiliza información enviada por `HomeController`.

---

## `wiki-lista.html`

Presenta el listado de páginas disponibles.

Debe utilizar Thymeleaf para recorrer dinámicamente las páginas.

Ejemplo:

```html
<div th:each="pagina : ${paginas}">
    <h2 th:text="${pagina.titulo}"></h2>
</div>
```

Cada página puede enlazar hacia:

```text
/wiki/{id}
```

---

## `wiki-detalle.html`

Presenta una página específica de la Wiki.

Recibe:

```text
pagina
```

Debe mostrar dinámicamente:

- título
- descripción
- secciones
- contenido

Las secciones pueden recorrerse con `th:each`.

---

## `categoria-detalle.html`

Presenta una categoría y las páginas asociadas a ella.

Recibe:

```text
categoria
paginas
```

Debe permitir navegar desde una categoría hacia sus páginas.

---

## `equipo.html`

Presenta los integrantes del equipo.

Recibe:

```text
equipo
```

La lista debe renderizarse dinámicamente utilizando Thymeleaf.

Ejemplo:

```html
<div th:each="miembro : ${equipo}">
    <h2 th:text="${miembro.nombre}"></h2>
    <p th:text="${miembro.rol}"></p>
</div>
```

---

## `contacto.html`

Contiene el formulario de Contáctenos.

Debe tener como mínimo:

1. Nombre completo
2. Correo electrónico
3. Teléfono
4. Asunto o motivo de contacto
5. Mensaje

Las validaciones se implementan mediante JavaScript.

---

# 9. Fragmentos Thymeleaf

Ubicación:

```text
src/main/resources/templates/fragments/
```

Los fragmentos permiten reutilizar partes comunes de las páginas.

Actualmente existen:

```text
header.html
menu.html
footer.html
```

## `header.html`

Contiene elementos comunes del encabezado.

Puede incluir:

- título
- descripción
- estructura del encabezado

---

## `menu.html`

Contiene la navegación principal.

Debe permitir acceder a:

```text
/
 /wiki
 /equipo
 /contacto
```

---

## `footer.html`

Contiene el pie de página común de la aplicación.

---

## Uso de fragmentos

Las páginas deben reutilizar estos fragmentos mediante Thymeleaf, por ejemplo:

```html
<div th:replace="~{fragments/header :: header}"></div>
```

El nombre exacto del fragmento debe coincidir con el `th:fragment` definido dentro del archivo.

---

# 10. Recursos estáticos

Ubicación:

```text
src/main/resources/static/
```

Los recursos estáticos no son procesados como vistas Thymeleaf.

---

## CSS

Ubicación:

```text
static/css/style.css
```

Responsable de los estilos visuales de la Wiki.

Aquí debe mantenerse el diseño general de:

- encabezado
- navegación
- contenido
- tarjetas
- formularios
- botones
- mensajes
- footer

---

## JavaScript

Ubicación:

```text
static/js/contacto.js
```

Se utiliza principalmente para las validaciones del formulario de contacto.

Las validaciones requeridas son:

### Nombre completo

- obligatorio
- mínimo 3 caracteres
- no puede contener únicamente espacios

### Correo

- obligatorio
- debe contener `@`
- debe existir un punto después de `@`

### Teléfono

- obligatorio
- solamente números
- mínimo 7 dígitos
- máximo 15 dígitos

### Asunto

- obligatorio
- no puede mantenerse una opción como "Seleccione una opción"

### Mensaje

- obligatorio
- mínimo 20 caracteres
- máximo 400 caracteres
- debe indicar cuántos caracteres faltan cuando todavía no alcanza el mínimo

El formulario no debe continuar si existe algún error de validación.

---

# 11. Datos actuales de la Wiki

Los datos se encuentran dentro de:

```text
WikiDataService.java
```

## Categorías

### Resumen del Proyecto

Explica la idea general del sistema: registro de empresas con espacio independiente, usuarios con roles
(administrador, editor y solo lectura) y control de acceso por empresa.

### Funcionalidades

Explica las funcionalidades principales:

- gestión de procesos (CRUD, historial, filtros y eliminación lógica)
- modelado de procesos (actividades, arcos y gateways)
- pools y lanes (swimlanes)
- comunicación y mensajería entre procesos
- multiempresa
- seguridad

### Arquitectura y Datos

Explica:

- arquitectura propuesta
- backend
- persistencia
- vistas
- modelo de datos
- relaciones entre entidades

---

# 12. Páginas actuales

Actualmente se encuentran las siguientes páginas:

```text
Descripción General
Gestión de Procesos
Modelado de Procesos
Multiempresa y Seguridad
Arquitectura Propuesta
Modelo de Datos
Pool y Lanes (Swimlanes)
Comunicación y Mensajería entre Procesos
```

Cada página pertenece a una categoría.

---

# 13. Relación entre clases

La relación conceptual actual es:

```text
Category
   │
   │ 1
   │
   ├─────────── *
   │
WikiPage
   │
   │ 1
   │
   ├─────────── *
   │
Section
```

Y para el equipo:

```text
WikiDataService
      │
      └── List<Miembro>
                │
                ├── Elieen
                ├── Brandon
                ├── Carol
                ├── Juliana
                └── Juan
```

---

# 14. Rutas actuales

| Ruta | Controlador | Vista |
|---|---|---|
| `/` | `HomeController` | `index.html` |
| `/wiki` | `WikiController` | `wiki-lista.html` |
| `/wiki/{id}` | `WikiController` | `wiki-detalle.html` |
| `/categoria/{id}` | `CategoryController` | `categoria-detalle.html` |
| `/equipo` | `EquipoController` | `equipo.html` |
| `/contacto` | `ContactController` | `contacto.html` |

---

# 15. Flujo de datos

Ejemplo: usuario entra a `/wiki`.

```text
Navegador
    │
    ▼
GET /wiki
    │
    ▼
WikiController
    │
    ▼
WikiDataService
    │
    ▼
obtenerPaginas()
    │
    ▼
Model
    │
    ▼
wiki-lista.html
    │
    ▼
Thymeleaf
    │
    ▼
HTML final
```

Ejemplo: usuario entra a una página específica:

```text
GET /wiki/descripcion-general
            │
            ▼
      WikiController
            │
            ▼
obtenerPaginaPorId("descripcion-general")
            │
            ▼
          Model
            │
            ▼
    wiki-detalle.html
```

---

# 16. Reglas para trabajar en el proyecto

## Controladores

Los controladores deben encargarse principalmente de:

- recibir solicitudes
- solicitar datos al servicio
- agregar atributos al `Model`
- seleccionar la vista o redirección

Evitar colocar lógica de negocio compleja dentro de los controladores.

---

## Servicios

Los servicios deben encargarse de:

- obtener información
- procesar información relacionada con los datos
- centralizar el acceso a los datos

Las vistas no deben acceder directamente al servicio.

---

## Modelos

Los modelos representan los datos.

No deben contener lógica relacionada con HTML o Thymeleaf.

---

## Vistas

Las vistas deben encargarse de:

- presentar información
- utilizar expresiones Thymeleaf
- recorrer listas
- mostrar u ocultar contenido
- utilizar fragmentos reutilizables

No colocar lógica de negocio compleja dentro de las vistas.

---

# 17. Convenciones para Thymeleaf

Al trabajar con datos dinámicos se recomienda utilizar:

```html
th:text
th:each
th:if
th:unless
th:href
th:replace
th:fragment
```

Ejemplo:

```html
<h1 th:text="${pagina.titulo}">
    Título de la página
</h1>
```

Para recorrer:

```html
<div th:each="pagina : ${paginas}">
    <h2 th:text="${pagina.titulo}"></h2>
</div>
```

Para enlaces dinámicos:

```html
<a th:href="@{/wiki/{id}(id=${pagina.id})}">
    Ver página
</a>
```

---

# 18. Despliegue docker
docker build -t "nombre_del_proyecto" .


