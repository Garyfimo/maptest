package com.platanitos.mapstest;

import java.util.ArrayList;

/**
 * Created by Garyfimo on 6/12/15.
 */
public class MarkerStore {

    private String tienda;
    private String direccion;
    ArrayList<Producto> productos;
    private Double mLatitude;
    private Double mLongitude;

    public MarkerStore() {
    }

    public MarkerStore(String tienda, String direccion, Double mLatitude, Double mLongitude) {
        this.tienda = tienda;
        this.direccion = direccion;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    public MarkerStore(String tienda, String direccion, ArrayList<Producto> productos, Double mLatitude, Double mLongitude) {
        this.tienda = tienda;
        this.direccion = direccion;
        this.productos = productos;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }
}
