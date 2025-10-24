package com.construccion.entity;

public class Producto {
    private Long id;
    private String nombre;
    private String categoria;
    private int stock;
    private int stockMinimo;
    private double precio;

    // Constructor vacío
    public Producto() {}

    // Constructor con parámetros
    public Producto(Long id, String nombre, String categoria, int stock, int stockMinimo, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.precio = precio;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    // Método de negocio
    public boolean necesitaReposicion() {
        return stock <= stockMinimo;
    }

    @Override
    public String toString() {
        return String.format("%d. %s - Stock: %d (Min: %d) - $%.2f",
                id, nombre, stock, stockMinimo, precio);
    }
}