package Logica;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import Logica.Celda.Estado;

public class Nonograma {

    private  Grilla grilla;               
    private  Grilla solucion;             
    private  List<int[]> pistasFilas;     
    private  List<int[]> pistasColumnas;  
    private boolean pistaYaUsada =false;


    public Nonograma(int filas, int columnas) {
        this.grilla = new Grilla(filas, columnas);
        this.solucion = GeneradorSolucion.generarSolucion(filas, columnas);
        this.pistasFilas = GeneradorSolucion.calcularPistasFilas(solucion);
        this.pistasColumnas = GeneradorSolucion.calcularPistasColumnas(solucion);
    }
    
    public Celda.Estado getEstado(int fila, int columna) {
        return grilla.getEstado(fila, columna);
    }
    
	public Estado getEstadoSolucion(int fila, int columna) {
        return solucion.getEstado(fila, columna);
	}

    public int getFilas() { 
    	return grilla.getFilas(); 
    }
    
    public int getColumnas() {
    	return grilla.getColumnas(); 
    }

    public List<int[]> getPistasFilas() {
        List<int[]> copia = pistasFilas.stream()
                .map(arr -> Arrays.copyOf(arr, arr.length))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(copia);
    }
    public List<int[]> getPistasColumnas() {
        List<int[]> copia = pistasColumnas.stream()
                .map(arr -> Arrays.copyOf(arr, arr.length))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(copia);
    }
    
    // Marca en la grilla del jugador
    public void marcarCasillaConNegro(int fila, int columna) {
        grilla.pintarNegro(fila, columna);
    }
    public void marcarCasillaConX(int fila, int columna) {
        grilla.marcarX(fila, columna);
    }
    public void desmarcarCasilla(int fila, int columna) {
        grilla.limpiar(fila, columna);
    }

    public boolean esSolucionCorrecta() {
        List<int[]> pistasFilasUsuario = GeneradorSolucion.calcularPistasFilas(grilla);
        List<int[]> pistasColumnasUsuario = GeneradorSolucion.calcularPistasColumnas(grilla);

        if (!compararListasDePistas(pistasFilas, pistasFilasUsuario)) {
            return false;
        }

        return compararListasDePistas(pistasColumnas, pistasColumnasUsuario);
    }


    private boolean compararListasDePistas(List<int[]> pistas1, List<int[]> pistas2) {
        if (pistas1.size() != pistas2.size()) return false;

        for (int i = 0; i < pistas1.size(); i++) {
            int[] p1 = pistas1.get(i);
            int[] p2 = pistas2.get(i);
            if (p1.length != p2.length) return false;
            for (int j = 0; j < p1.length; j++) {
                if (p1[j] != p2[j]) return false;
            }
        }
        return true;
    }
   
    public Pos obtenerPistaInteligente() {
        if (pistaYaUsada) return null;

        List<Pos> candidatas = new ArrayList<>();
        for (int f = 0; f < grilla.getFilas(); f++) {
            for (int c = 0; c < grilla.getColumnas(); c++) {
                Celda.Estado estadoJugador = grilla.getEstado(f,c);
                Celda.Estado estadoSolucion = solucion.getEstado(f,c);
                if ((estadoJugador == Celda.Estado.blanco || estadoJugador==Celda.Estado.x)
                        && estadoSolucion == Celda.Estado.negro) {
                    candidatas.add(new Pos(f,c));
                }
            }
        }
        if (candidatas.isEmpty()) return null;
        Pos seleccionada = candidatas.get(new Random().nextInt(candidatas.size()));
        grilla.pintarNegro(seleccionada.fila(), seleccionada.columna());
        pistaYaUsada = true;
        return seleccionada;
    }
    
    //Reinicia la grilla
    public void reiniciarJuego() {
        grilla.reiniciar(); // limpia el tablero
        this.solucion = GeneradorSolucion.generarSolucion(grilla.getFilas(), grilla.getColumnas());
        this.pistasFilas = GeneradorSolucion.calcularPistasFilas(solucion);
        this.pistasColumnas = GeneradorSolucion.calcularPistasColumnas(solucion);
        this.pistaYaUsada=false;

        
    } 
 
}
