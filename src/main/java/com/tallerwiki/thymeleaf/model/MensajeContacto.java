package com.tallerwiki.thymeleaf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes_contacto")
public class MensajeContacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mismas reglas que contacto.js: el servidor no confía solo en la validación del navegador.
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El correo es obligatorio.")
    @Size(max = 100, message = "El correo no puede superar los 100 caracteres.")
    @Pattern(regexp = "^[^@\\s]+@[^@\\s.]+(\\.[^@\\s.]+)+$",
             message = "El correo debe tener una arroba y un punto después de ella.")
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @NotBlank(message = "El telefono es obligatorio.")
    @Pattern(regexp = "^\\d{7,15}$", message = "El telefono debe tener solo numeros, entre 7 y 15 digitos.")
    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @NotBlank(message = "Debes seleccionar un asunto.")
    @Pattern(regexp = "^(soporte|informacion|sugerencia|otro)$", message = "El asunto seleccionado no es valido.")
    @Column(name = "asunto", nullable = false, length = 50)
    private String asunto;

    @NotBlank(message = "El mensaje es obligatorio.")
    @Size(min = 20, max = 400, message = "El mensaje debe tener entre 20 y 400 caracteres.")
    @Column(name = "mensaje", nullable = false, length = 500)
    private String mensaje;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    public MensajeContacto() {
    }

    @PrePersist
    public void prePersist() {
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}