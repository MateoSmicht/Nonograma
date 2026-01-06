package Logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorSolucion {


    public static Grilla generarSolucion(int filas, int columnas) {
        Grilla grilla = new Grilla(filas, columnas);
        Random rand = new Random();

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (rand.nextBoolean()) {
                    grilla.pintarNegro(f, c);
                } else {
                    grilla.limpiar(f, c);
                }
            }
        }

        return grilla;
    }


    public static List<int[]> calcularPistasFilas(Grilla grilla) {
        List<int[]> pistasFilas = new ArrayList<>();

        for (int f = 0; f < grilla.getFilas(); f++) {
            List<Integer> pista = new ArrayList<>();
            int contador = 0;

            for (int c = 0; c < grilla.getColumnas(); c++) {
                Celda.Estado estado = grilla.getEstado(f, c);

                if (estado == Celda.Estado.negro) {
                    contador++;
                } else {
                    if (contador > 0) {
                        pista.add(contador);
                        contador = 0;
                    }
                }
            }

            // Si la fila termina con un grupo de negros, agregarlo
            if (contador > 0) pista.add(contador);

            // Si no hay grupos negros, devolver [0]
            if (pista.isEmpty()) pista.add(0);

            pistasFilas.add(pista.stream().mapToInt(Integer::intValue).toArray());
        }

        return pistasFilas;
    }


    public static List<int[]> calcularPistasColumnas(Grilla grilla) {
        List<int[]> pistasColumnas = new ArrayList<>();

        for (int c = 0; c < grilla.getColumnas(); c++) {
            List<Integer> pista = new ArrayList<>();
            int contador = 0;

            for (int f = 0; f < grilla.getFilas(); f++) {
                Celda.Estado estado = grilla.getEstado(f, c);

                // Solo las celdas negras cuentan para las pistas
                if (estado == Celda.Estado.negro) {
                    contador++;
                } else {
                    if (contador > 0) {
                        pista.add(contador);
                        contador = 0;
                    }
                }
            }

            // Si la columna termina con un grupo de negros, agregarlo
            if (contador > 0) pista.add(contador);

            // Si no hay grupos negros, devolver [0]
            if (pista.isEmpty()) pista.add(0);

            pistasColumnas.add(pista.stream().mapToInt(Integer::intValue).toArray());
        }

        return pistasColumnas;
    }
}
