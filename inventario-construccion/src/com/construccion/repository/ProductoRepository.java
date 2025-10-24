package com.construccion.repository;

import com.construccion.entity.Producto;
import java.util.*;

public class ProductoRepository {
    private Map<Long, Producto> productos = new HashMap<>();
    private Long nextId = 1L;

    public ProductoRepository() {
        // Datos de ejemplo para construcción en seco
        agregarProducto(new Producto(nextId++, "Placa Yeso 12mm 1.20x2.40", "PLACAS", 50, 10, 12500.00));
        agregarProducto(new Producto(nextId++, "Placa Yeso 15mm 1.20x2.40", "PLACAS", 30, 8, 15800.00));
        agregarProducto(new Producto(nextId++, "Perfil U 48mm x 0.6", "PERFILES", 200, 30, 850.00));
        agregarProducto(new Producto(nextId++, "Perfil C 48mm x 0.6", "PERFILES", 180, 25, 920.00));
        agregarProducto(new Producto(nextId++, "Tornillo T1 25mm", "FIJACION", 5000, 500, 45.00));
        agregarProducto(new Producto(nextId++, "Masilla Para Juntas", "TERMINACION", 80, 15, 3200.00));
        agregarProducto(new Producto(nextId++, "Cinta Papel 75m", "TERMINACION", 40, 8, 2800.00));
        agregarProducto(new Producto(nextId++, "Lana Vidrio 50mm", "AISLACION", 30, 5, 15800.00));
    }

    public List<Producto> findAll() {
        return new ArrayList<>(productos.values());
    }

    public Optional<Producto> findById(Long id) {
        return Optional.ofNullable(productos.get(id));
    }

    public Producto save(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(nextId++);
        }
        productos.put(producto.getId(), producto);
        return producto;
    }

    public void agregarProducto(Producto producto) {
        save(producto);
    }

    public List<Producto> findByCategoria(String categoria) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos.values()) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Producto> findProductosStockBajo() {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos.values()) {
            if (p.necesitaReposicion()) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}