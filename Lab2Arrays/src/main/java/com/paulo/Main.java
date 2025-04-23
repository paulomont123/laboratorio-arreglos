package com.paulo;

import java.io.IOException;
import java.util.*;

/*
    Carne: 0900-24-19021
    Nombre: Paulo Andre Monterroso Navas
    Curso: Programacion I
 */

public class Main {

    // Para ejecutar un ejercicio, debe llamar al metodo dentro del metodo main

    public static void main(String[] args) {
        ejercicio_2();
    }

    public static void ejercicio_1() {
        int[] array = new int[10];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < array.length; i++) {
            try {
                System.out.println("Ingrese el valor " + (i + 1) + ":");
                array[i] = sc.nextInt();
            } catch (Exception e) {
                System.out.println("No se permiten valores que no sean numeros enteros");
                return;
            }
        }
        Arrays.sort(array);
        int option;

        while (true) {
            System.out.println("Seleccione una opcion: ");
            System.out.println("1. Mostrar datos del array");
            System.out.println("2. Calcular la suma de los elementos del array");
            System.out.println("3. Buscar valor maximo y valor minimo del array");
            System.out.println("4. Mostar el array en un orden inverso");
            System.out.println("5. Salir");

            try {
                option = sc.nextInt();

                System.out.println();
                switch (option) {
                    case 1: {
                        Arrays.stream(array).forEach(System.out::println);
                        break;
                    }
                    case 2: {
                        int sum = Arrays.stream(array).reduce(0, Integer::sum);
                        System.out.println("La suma de los elementos del array es: " + sum);
                        break;
                    }
                    case 3: {
                        int max = Arrays.stream(array).max().getAsInt();
                        int min = Arrays.stream(array).min().getAsInt();

                        System.out.println("El valor maximo es: " + max);
                        System.out.println("El valor minimo es: " + min);
                        break;
                    }
                    case 4: {
                        Integer[] toReverseArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
                        Arrays.sort(toReverseArray, Collections.reverseOrder());
                        System.out.println("Arreglo ordenado inversamente: ");
                        Arrays.stream(toReverseArray).forEach(System.out::println);
                        break;
                    }
                    case 5: {
                        sc.close();
                        return;
                    }
                    default: {
                        System.out.println("Opcion no valida.");
                        break;
                    }
                }
                if (option <= 5) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("\nPresione enter para regresar al menu de opciones");
                        System.in.read();
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (Exception e) {
                System.out.println("No es una entrada correcta para la opcion.");
                return;
            }
        }
    }

    public static void ejercicio_2() {
        ArrayList<String> productsList = new ArrayList<>();

        productsList.add("Computadora");
        productsList.add("Monitor");
        productsList.add("Tablet");
        productsList.add("Mouse");
        productsList.add("Mousepad");
        productsList.add("Audifonos");
        productsList.add("Bocina");

        ArrayList<String> cartList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int option;
        while (true) {
            System.out.println("Seleccione una opcion: ");
            System.out.println("1. Agregar un producto");
            System.out.println("2. Mostrar lista de compras");
            System.out.println("3. Eliminar un producto de la lista");
            System.out.println("4. Buscar un producto en la lista");
            System.out.println("5. Ordenar la lista de productos alfabeticamente");
            System.out.println("6. Salir");

            try {
                option = sc.nextInt();
                System.out.println();

                switch (option) {
                    case 1: {
                        System.out.println("Productos disponibles:");
                        for (String product : productsList) {
                            System.out.println(product);
                        }
                        System.out.println("\nIngrese el nombre del producto: ");
                        String nombre = sc.next();
                        Optional<String> producto = productsList.stream().filter(p -> p.equalsIgnoreCase(nombre)).findFirst();
                        if (producto.isPresent()) {
                            Optional<String> productoInCart = cartList.stream().filter(c -> c.equalsIgnoreCase(nombre)).findFirst();
                            if (productoInCart.isEmpty()) {
                                cartList.add(nombre);
                                System.out.println("Producto " + nombre + " agregado al carrito");
                            } else {
                                System.out.println("El producto ya existe en la carrito.");
                            }
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("Carrito de compras:\n");
                        if (!cartList.isEmpty()) {
                            cartList.forEach(System.out::println);
                        } else {
                            System.out.println("El carrito esta vacio...");
                        }
                        break;
                    }
                    case 3: {
                        if (!cartList.isEmpty()) {
                            System.out.println("Carrito de compras:\n");
                            cartList.forEach(System.out::println);
                            System.out.println("\nIngrese el nombre del producto que desea eliminar: ");
                            String nombre = sc.next();
                            Optional<String> existe = cartList.stream().filter(p -> p.equalsIgnoreCase(nombre)).findFirst();
                            if (existe.isPresent()) {
                                cartList.remove(existe.get());
                                System.out.println("El producto " + nombre + " fue eliminado del carrito");
                            } else {
                                System.out.println("El producto no existe en la carrito.");
                            }
                        } else {
                            System.out.println("El carrito esta vacio...");
                        }
                        break;
                    }
                    case 4: {
                        if (!cartList.isEmpty()) {
                            System.out.println("Ingrese el nombre del producto que desea buscar: ");
                            String nombre = sc.next();
                            Optional<String> existe = cartList.stream().filter(p -> p.equalsIgnoreCase(nombre)).findFirst();
                            if (existe.isPresent()) {
                                System.out.println("El producto si se encuentra en el carrito");
                            } else {
                                System.out.println("El producto no existe en la carrito.");
                            }
                        } else {
                            System.out.println("El carrito esta vacio...");
                        }
                        break;
                    }
                    case 5: {
                        if (!cartList.isEmpty()) {
                            System.out.println("El arreglo antes:\n");
                            cartList.forEach(System.out::println);
                            Thread.sleep(2000);
                            System.out.println("\nEl arreglo despues:\n");
                            Collections.sort(cartList);
                            cartList.forEach(System.out::println);
                        } else {
                            System.out.println("El carrito esta vacio...");
                        }
                        break;
                    }
                    case 6: {
                        sc.close();
                        return;
                    }
                    default: {
                        System.out.println("Opcion no valida.");
                        break;
                    }
                }

                try {
                    Thread.sleep(1000);
                    System.out.println("\nPresione enter para regresar al menu de opciones");
                    System.in.read();
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                System.out.println("No es una entrada correcta para la opcion.");
                return;
            }
        }
    }

}