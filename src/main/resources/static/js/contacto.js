const form = document.getElementById("formContacto");
const nombre = document.getElementById("nombre");
const correo = document.getElementById("correo");
const telefono = document.getElementById("telefono");
const asunto = document.getElementById("asunto");
const mensaje = document.getElementById("mensaje");

const errorNombre = document.getElementById("errorNombre");
const errorCorreo = document.getElementById("errorCorreo");
const errorTelefono = document.getElementById("errorTelefono");
const errorAsunto = document.getElementById("errorAsunto");
const errorMensaje = document.getElementById("errorMensaje");
const contadorMensaje = document.getElementById("contadorMensaje");
const mensajeExito = document.getElementById("mensajeExito");

const MENSAJE_MIN = 20;
const MENSAJE_MAX = 400;

mensaje.addEventListener("input", function () {
    const restantes = MENSAJE_MIN - mensaje.value.trim().length;
    if (restantes > 0) {
        contadorMensaje.textContent = "Faltan " + restantes + " caracteres";
    } else {
        const disponibles = MENSAJE_MAX - mensaje.value.length;
        contadorMensaje.textContent = disponibles + " caracteres disponibles";
    }
});

function validarNombre() {
    const valor = nombre.value.trim();
    if (valor.length === 0) {
        return mostrarError(nombre, errorNombre, "El nombre es obligatorio.");
    }
    if (valor.length < 3) {
        return mostrarError(nombre, errorNombre, "El nombre debe tener minimo 3 caracteres.");
    }
    return limpiarError(nombre, errorNombre);
}

function validarCorreo() {
    const valor = correo.value.trim();
    const arroba = valor.indexOf("@");
    if (valor.length === 0) {
        return mostrarError(correo, errorCorreo, "El correo es obligatorio.");
    }
    if (arroba === -1) {
        return mostrarError(correo, errorCorreo, "El correo debe contener arroba.");
    }
    const dominio = valor.substring(arroba + 1);
    if (dominio.indexOf(".") <= 0) {
        return mostrarError(correo, errorCorreo, "El correo debe tener un punto despues de la arroba.");
    }
    return limpiarError(correo, errorCorreo);
}

function validarTelefono() {
    const valor = telefono.value.trim();
    const soloNumeros = /^[0-9]+$/;
    if (valor.length === 0) {
        return mostrarError(telefono, errorTelefono, "El telefono es obligatorio.");
    }
    if (!soloNumeros.test(valor)) {
        return mostrarError(telefono, errorTelefono, "El telefono solo puede contener numeros.");
    }
    if (valor.length < 7 || valor.length > 15) {
        return mostrarError(telefono, errorTelefono, "El telefono debe tener entre 7 y 15 digitos.");
    }
    return limpiarError(telefono, errorTelefono);
}

function validarAsunto() {
    if (asunto.value === "") {
        return mostrarError(asunto, errorAsunto, "Debes seleccionar un asunto.");
    }
    return limpiarError(asunto, errorAsunto);
}

function validarMensaje() {
    const valor = mensaje.value.trim();
    if (valor.length === 0) {
        return mostrarError(mensaje, errorMensaje, "El mensaje es obligatorio.");
    }
    if (valor.length < MENSAJE_MIN) {
        return mostrarError(mensaje, errorMensaje, "Faltan " + (MENSAJE_MIN - valor.length) + " caracteres para el minimo.");
    }
    if (valor.length > MENSAJE_MAX) {
        return mostrarError(mensaje, errorMensaje, "El mensaje no puede superar los " + MENSAJE_MAX + " caracteres.");
    }
    return limpiarError(mensaje, errorMensaje);
}

function mostrarError(campo, spanError, texto) {
    campo.classList.add("input-error");
    spanError.textContent = texto;
    return false;
}

function limpiarError(campo, spanError) {
    campo.classList.remove("input-error");
    spanError.textContent = "";
    return true;
}

nombre.addEventListener("blur", validarNombre);
correo.addEventListener("blur", validarCorreo);
telefono.addEventListener("blur", validarTelefono);
asunto.addEventListener("change", validarAsunto);
mensaje.addEventListener("blur", validarMensaje);

form.addEventListener("submit", function (e) {
    e.preventDefault();

    const nombreOk = validarNombre();
    const correoOk = validarCorreo();
    const telefonoOk = validarTelefono();
    const asuntoOk = validarAsunto();
    const mensajeOk = validarMensaje();

    if (nombreOk && correoOk && telefonoOk && asuntoOk && mensajeOk) {
        mensajeExito.style.display = "block";
        form.reset();
        contadorMensaje.textContent = "Faltan " + MENSAJE_MIN + " caracteres";
    } else {
        mensajeExito.style.display = "none";
    }
});
