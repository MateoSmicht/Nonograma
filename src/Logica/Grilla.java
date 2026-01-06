package Logica;

public class Grilla {
	
	private final int num_filas;
	private final int columnas;
	
	//Matriz de celdas
	private final Celda [][] celdas;
	
	//Constructor
	public Grilla(int filas,int columnas) {
		if(filas <= 0 || columnas <= 0) {
			 throw new IllegalArgumentException("filas y columnas deben ser >= 1");
		}
		this.num_filas = filas;
		this.columnas = columnas;
		this.celdas = new Celda[filas][columnas];
		
		//Inicializamos todas las celdas en blanco
		for(int f = 0 ; f < filas ; f++) {
			for(int c = 0 ; c < columnas ; c++) {
				celdas[f][c] = new Celda(f,c);
			}
		}
	}
	public void reiniciar() {
	    for (int f = 0; f < num_filas; f++) {
	        for (int c = 0; c < columnas; c++) {
	            celdas[f][c].limpiar();
	        }
	    }
	}
	
	private void validadPosicion(int fila, int columna) {
		// TODO Auto-generated method stub
		if(fila < 0 || fila >= num_filas) {
			throw new IllegalArgumentException("fila fuera de rango: " + fila);
		}
		if (columna < 0 || columna >= columnas) {
            throw new IllegalArgumentException("columna fuera de rango: " + columna);
        }
	}
	
	public Celda.Estado getEstado(int fila, int columna) {
	    validadPosicion(fila, columna);
	    return celdas[fila][columna].getEstado();
	}
	
	//Operaciones del juego
	public void pintarNegro(int fila,int columna) {
		validadPosicion(fila,columna);
		celdas[fila][columna].pintarNegro();
	}
	
	public void marcarX(int fila,int columna) {
		validadPosicion(fila,columna);
		celdas[fila][columna].marcarX();
	}
	
	public void limpiar(int fila,int columna) {
		validadPosicion(fila,columna);
		celdas[fila][columna].limpiar();
	}
	
    Celda getCeldaPackagePrivate(int fila, int columna) {
        validadPosicion(fila, columna);
        return celdas[fila][columna];
    }
	
	//Getters para obtener tamaños de f y c
	public int getFilas() {
		return num_filas;
	}
	public int getColumnas() {
		return columnas;
	}
	
}
