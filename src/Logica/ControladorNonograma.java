package Logica;

import java.awt.Color;
import java.util.List;
import javax.swing.Timer; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import Gui.PantallaInicio;
import Gui.PantallaJuego;

public class ControladorNonograma {
	private final Nonograma _nonograma;
	private PantallaJuego _vista;
	private Timer _timer;
	private int _tiempoTranscurrido; 
	private int _tiempoLimite;


	public ControladorNonograma(Nonograma nonograma, int tiempoLimite) {
		this._nonograma = nonograma;
		this._tiempoLimite = tiempoLimite;
		this._tiempoTranscurrido = 0;
		this._timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarTickDelTimer();
            }});
		
	}
	
    public Celda.Estado consultarEstado(int fila, int columna) {
        return _nonograma.getEstado(fila, columna);
    }
    
    public List<int[]> getPistasFilas() {
        return _nonograma.getPistasFilas();
    }
    
    public List<int[]> getPistasColumnas() {
        return _nonograma.getPistasColumnas();
    }
    
    public int getFilas() {
        return _nonograma.getFilas();
    }

    public int getColumnas() {
        return _nonograma.getColumnas();
    }
    
	public int getTiempoRestante() {
	    return _tiempoLimite - _tiempoTranscurrido;
	}
	
	public void setVista(PantallaJuego vista) {
		this._vista = vista;
	}
	

    public void usuarioHizoClicIzquierdo(int fila, int columna) {
        Celda.Estado estadoActual = _nonograma.getEstado(fila, columna);

        if (estadoActual == Celda.Estado.blanco) {
            _nonograma.marcarCasillaConNegro(fila, columna);
            if (_vista != null) _vista.actualizarBoton(fila, columna, Color.BLACK, "");
        } else {
            // Desmarcar (vuelve a blanco)
            _nonograma.desmarcarCasilla(fila, columna);
            if (_vista != null) _vista.actualizarBoton(fila, columna, Color.WHITE, "");
        }
    }

    public void usuarioHizoClicDerecho(int fila, int columna) {
        Celda.Estado estadoActual = _nonograma.getEstado(fila, columna);

        if (estadoActual == Celda.Estado.x) {
            _nonograma.desmarcarCasilla(fila, columna);
            if (_vista != null) _vista.actualizarBoton(fila, columna, Color.WHITE, "");
        } else {
            _nonograma.marcarCasillaConX(fila, columna);
            if (_vista != null) _vista.actualizarBoton(fila, columna, Color.RED, "X");
        }
    }

    public void usuarioComproboSolucion() {
        if (_nonograma.esSolucionCorrecta()) {
            if (_vista != null) _vista.ganaste();
        } else {
            if (_vista != null) _vista.perdiste();
        }
    }
	
    public void usuarioQuiereVolverAJugar() {
        detenerTimer();
        _nonograma.reiniciarJuego();
        if (_vista != null) _vista.reiniciarVista();
        iniciarJuego();
    }

    public void obtienePistaInteligente() {
        Pos pos = _nonograma.obtenerPistaInteligente();
        if (pos != null) {
            int fila = pos.fila();
            int col = pos.columna();
            if (_vista != null) {
                _vista.actualizarBoton(fila, col, Color.BLACK, "");
                _vista.destacarPista(fila, col);
            }
        } else {
            if (_vista != null) _vista.mostrarMensaje("No quedan pistas disponibles.");
        }
    }
	
	private void manejarTickDelTimer() {
        _tiempoTranscurrido++;
        int tiempoRestante = _tiempoLimite - _tiempoTranscurrido;

        if (tiempoRestante > 0) {
            _vista.actualizarLabelTiempo(tiempoRestante);
        } else {
            _timer.stop();
            _vista.mostrarSolucion(); 
            _vista.perdiste();
        }
	}


	public void iniciarJuego() {
        _tiempoTranscurrido = 0;
        _vista.actualizarLabelTiempo(_tiempoLimite);
        _timer.start();
    }
	public void detenerTimer() {
        if (_timer != null) {
            _timer.stop();
        }
    }

    public void usuarioPidioSolucion() {
        detenerTimer();
        mostrarSolucionEnVista();
        if (_vista != null) {
            _vista.mostrarPantallaFinal("PERDISTE", "Te rendiste. Esta era la solución:", null, false);
        }
    }

    // Muestra la solución pintando los botones de la vista según la solución
    private void mostrarSolucionEnVista() {
        if (_vista == null) return;

        int filas = _nonograma.getFilas();
        int columnas = _nonograma.getColumnas();

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                Celda.Estado estSol = _nonograma.getEstadoSolucion(f, c);
                if (estSol == Celda.Estado.negro) {
                    _vista.actualizarBoton(f, c, Color.BLACK, "");
                } else if (estSol == Celda.Estado.x) {
                    _vista.actualizarBoton(f, c, Color.RED, "X");
                } else {
                    _vista.actualizarBoton(f, c, Color.WHITE, "");
                }
            }
        }
    }

    public void usuarioQuiereVolverAlMenu() {
        detenerTimer();
        if (_vista != null) _vista.cerrarVista();
        new PantallaInicio().setVisible(true);
    }


}

