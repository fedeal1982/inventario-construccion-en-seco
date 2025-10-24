package com.construccion.observer;

import com.construccion.entity.Producto;

public class AlertaConsola implements AlertaObserver {
    @Override
    public void notificar(String mensaje, Producto producto) {
        System.out.printf("[CONSOLA] %s: %s - Stock: %d%n",
                mensaje, producto.getNombre(), producto.getStock());
    }
}