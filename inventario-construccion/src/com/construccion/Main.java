package com.construccion;

import com.construccion.entity.Producto;
import com.construccion.service.InventarioService;
import java.util.Scanner;

public class Main {
    private static InventarioService inventarioService;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("🏗️  SISTEMA DE INVENTARIO - CONSTRUCCIÓN EN SECO");
        System.out.println("==============================================");

        // Inicializar servicio
        inventarioService = new InventarioService();

        // Mostrar menú
        menuPrincipal();

        scanner.close();
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. 📋 Listar productos");
            System.out.println("2. ➕ Agregar producto");
            System.out.println("3. 📥 Entrada de stock");
            System.out.println("4. 📤 Salida de stock");
            System.out.println("5. ⚠️  Ver stock bajo");
            System.out.println("6. 🗂️  Productos por categoría");
            System.out.println("7. ❌ Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerNumero();

            switch (opcion) {
                case 1 -> listarProductos();
                case 2 -> agregarProducto();
                case 3 -> entradaStock();
                case 4 -> salidaStock();
                case 5 -> verStockBajo();
                case 6 -> porCategoria();
                case 7 -> {
                    System.out.println("¡Hasta pronto! 👷");
                    return;
                }
                default -> System.out.println("❌ Opción inválida");
            }
        }
    }

    private static void listarProductos() {
        System.out.println("\n--- PRODUCTOS EN STOCK ---");
        var productos = inventarioService.todosProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    private static void agregarProducto() {
        System.out.println("\n--- NUEVO PRODUCTO ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        System.out.print("Stock: ");
        int stock = leerNumero();
        System.out.print("Stock mínimo: ");
        int stockMinimo = leerNumero();
        System.out.print("Precio: ");
        double precio = leerDouble();

        Producto producto = new Producto(null, nombre, categoria, stock, stockMinimo, precio);
        inventarioService.agregarProducto(producto);
    }

    private static void entradaStock() {
        System.out.println("\n--- ENTRADA DE STOCK ---");
        listarProductos();
        System.out.print("ID Producto: ");
        Long id = scanner.nextLong();
        System.out.print("Cantidad: ");
        int cantidad = leerNumero();

        inventarioService.entradaStock(id, cantidad);
    }

    private static void salidaStock() {
        System.out.println("\n--- SALIDA DE STOCK ---");
        listarProductos();
        System.out.print("ID Producto: ");
        Long id = scanner.nextLong();
        System.out.print("Cantidad: ");
        int cantidad = leerNumero();

        inventarioService.salidaStock(id, cantidad);
    }

    private static void verStockBajo() {
        System.out.println("\n--- PRODUCTOS CON STOCK BAJO ---");
        var productosBajos = inventarioService.stockBajo();
        if (productosBajos.isEmpty()) {
            System.out.println("✅ No hay productos con stock bajo");
        } else {
            productosBajos.forEach(p ->
                    System.out.println("⚠️  " + p));
        }
    }

    private static void porCategoria() {
        System.out.println("\n--- BUSCAR POR CATEGORÍA ---");
        System.out.println("Categorías: PLACAS, PERFILES, FIJACION, TERMINACION, AISLACION");
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine().toUpperCase();

        var productos = inventarioService.porCategoria(categoria);
        if (productos.isEmpty()) {
            System.out.println("❌ No hay productos en esa categoría");
        } else {
            productos.forEach(System.out::println);
        }
    }

    private static int leerNumero() {
        while (true) {
            try {
                int num = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                return num;
            } catch (Exception e) {
                System.out.print("Ingrese un número válido: ");
                scanner.nextLine(); // limpiar buffer
            }
        }
    }

    private static double leerDouble() {
        while (true) {
            try {
                double num = scanner.nextDouble();
                scanner.nextLine(); // limpiar buffer
                return num;
            } catch (Exception e) {
                System.out.print("Ingrese un número válido: ");
                scanner.nextLine(); // limpiar buffer
            }
        }
    }
}