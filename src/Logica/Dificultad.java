package Logica;

public enum Dificultad {
    FACIL(5, 5),
    MEDIO(6, 6),
    DIFICIL(7, 7);

    private final int filas;
    private final int columnas;

    Dificultad(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }

    public int getFilas() {
    	return filas; 
    }
    
    public int getColumnas() {
    	return columnas; 
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase() + " (" + filas + "x" + columnas + ")";
    }
}


