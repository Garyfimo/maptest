package com.platanitos.mapstest;

/**
 * Created by Garyfimo on 6/12/15.
 */
public class Producto {

    Double precio;
    int imagen;
    String nombre;
    String categoria;

    public Producto() {

    }

    public Producto(Double precio, int imagen, String nombre, String categoria) {
        this.precio = precio;
        this.imagen = imagen;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
