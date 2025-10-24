package com.construccion.service;

import com.construccion.entity.Producto;
import com.construccion.observer.*;
import com.construccion.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;

public class InventarioService {
    private ProductoRepository productoRepo = new ProductoRepository();
    private Subject gestorAlertas = new Subject();

    public InventarioService() {
        // Configurar observers
        gestorAlertas.registrarObservador(new AlertaConsola());
        gestorAlertas.registrarObservador(new AlertaEmail());
        System.out.println("✅ Sistema de alertas configurado");

        // Verificar stock inicial
        verificarTodoElStock();
    }

    public List<Producto> todosProductos() {
        return productoRepo.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return productoRepo.findById(id);
    }

    public void agregarProducto(Producto producto) {
        productoRepo.agregarProducto(producto);
        gestorAlertas.verificarStock(producto);
        System.out.println("✅ Producto agregado: " + producto.getNombre());
    }

    public void entradaStock(Long id, int cantidad) {
        Optional<Producto> productoOpt = productoRepo.findById(id);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            producto.setStock(producto.getStock() + cantidad);
            productoRepo.save(producto);
            System.out.println("✅ Entrada registrada: " + producto.getNombre());
        } else {
            System.out.println("❌ Producto no encontrado");
        }
    }

    public void salidaStock(Long id, int cantidad) {
        Optional<Producto> productoOpt = productoRepo.findById(id);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();

            if (producto.getStock() < cantidad) {
                System.out.println("❌ Stock insuficiente en " + producto.getNombre());
                return;
            }

            producto.setStock(producto.getStock() - cantidad);
            productoRepo.save(producto);
            gestorAlertas.verificarStock(producto);
            System.out.println("✅ Salida registrada: " + producto.getNombre());
        } else {
            System.out.println("❌ Producto no encontrado");
        }
    }

    public List<Producto> stockBajo() {
        return productoRepo.findProductosStockBajo();
    }

    public List<Producto> porCategoria(String categoria) {
        return productoRepo.findByCategoria(categoria);
    }

    public void verificarTodoElStock() {
        System.out.println("🔍 Verificando stock inicial...");
        for (Producto producto : productoRepo.findAll()) {
            gestorAlertas.verificarStock(producto);
        }
    }
}