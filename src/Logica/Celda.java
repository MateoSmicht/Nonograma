package Logica;

public class Celda {
	
	public enum Estado{
		blanco,negro,x
	}

	//Estados de la celda:
	
	//Coordenadas en la grilla
	public final int fila;
	public final int columna;
	private Estado estado;


	//Creamos celda en una posicion con un constructor
	public Celda(int fila,int columna) {
		if(fila < 0 || columna < 0) {
			throw new IllegalArgumentException("Fila y columna debe ser >= 0");
		}
		
		this.fila = fila;
		this.columna = columna;
		this.estado = Estado.blanco;
	}

	//Operaciones dentro de la celda que cambia el estado interno por eso void
	public void pintarNegro() {
		this.estado = Estado.negro;
	}
	public void marcarX() {
		this.estado = Estado.x;
	}
	public void limpiar() {
		this.estado = Estado.blanco;
	}
	
	public boolean sonIguales(Celda celda2) {
		if(this.columna != celda2.getColumna() || this.fila != celda2.getFila()) {
			return false;
		}
		if(this.estado != celda2.getEstado()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
	    String textoEstado;
	    switch (estado) {
	        case negro:
	            textoEstado = "Negro";
	            break;
	        case x:
	            textoEstado = "x";
	            break;
	        default:
	            textoEstado = "Blanco";
	            break;
	    }
	    return String.format("Celda(%d,%d)=%s", fila, columna, textoEstado);
	}

	
	public Estado getEstado() {
		return estado;
	}
	
	//Obtengo informacion
	public int getFila() {
		return fila;
	}
	public int getColumna() {
		return columna;
	}

}
