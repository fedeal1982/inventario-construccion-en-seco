package com.construccion.observer;

import com.construccion.entity.Producto;
import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<AlertaObserver> observadores = new ArrayList<>();

    public void registrarObservador(AlertaObserver observador) {
        observadores.add(observador);
        System.out.println("Observer registrado: " + observador.getClass().getSimpleName());
    }

    public void notificarObservadores(String mensaje, Producto producto) {
        for (AlertaObserver obs : observadores) {
            obs.notificar(mensaje, producto);
        }
    }

    public void verificarStock(Producto producto) {
        if (producto.getStock() == 0) {
            notificarObservadores("❌ STOCK AGOTADO", producto);
        } else if (producto.necesitaReposicion()) {
            notificarObservadores("⚠️ STOCK BAJO", producto);
        }
    }
}