package com.construccion.observer;

import com.construccion.entity.Producto;

public class AlertaEmail implements AlertaObserver {
    @Override
    public void notificar(String mensaje, Producto producto) {
        if (mensaje.contains("AGOTADO") || mensaje.contains("BAJO")) {
            System.out.println("📧 ENVIANDO EMAIL:");
            System.out.println("   Para: gerencia@construccion.com");
            System.out.println("   Asunto: " + mensaje + " - " + producto.getNombre());
            System.out.println("   Stock actual: " + producto.getStock());
            System.out.println("   Stock mínimo: " + producto.getStockMinimo());
            System.out.println("   ---");
        }
    }
}