package com.construccion.observer;

import com.construccion.entity.Producto;

public interface AlertaObserver {
    void notificar(String mensaje, Producto producto);
}