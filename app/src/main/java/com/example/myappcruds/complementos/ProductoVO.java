package com.example.myappcruds.complementos;

public class ProductoVO {

    private int id_producto;
    private String nombre_Producto;
    private String descripcion_producto;
    private int valor_producto;
    private String estado_producto;

    public ProductoVO() {
    }

    public ProductoVO(int id_producto, String nombre_Producto, String descripcion_producto, int valor_producto, String estado_producto) {
        this.id_producto = id_producto;
        this.nombre_Producto = nombre_Producto;
        this.descripcion_producto = descripcion_producto;
        this.valor_producto = valor_producto;
        this.estado_producto = estado_producto;
    }

    public int getId() {
        return id_producto;
    }

    public void setId(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_Prducto() {
        return nombre_Producto;
    }

    public void setNombre_Prducto(String nombre_Prducto) {
        this.nombre_Producto = nombre_Prducto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public int getValor_producto() {
        return valor_producto;
    }

    public void setValor_producto(int valor_producto) {
        this.valor_producto = valor_producto;
    }

    public String getEstado_producto() {
        return estado_producto;
    }

    public void setEstado_producto(String estado_producto) {
        this.estado_producto = estado_producto;
    }
}
