package com.tallerwiki.thymeleaf.model;

public class Miembro {

    private String nombre;
    private String rol;
    private String correo;
    private String imagen;

    public Miembro() {
    }

    public Miembro(String nombre, String rol, String correo, String imagen) {
        this.nombre = nombre;
        this.rol = rol;
        this.correo = correo;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
