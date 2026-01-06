package Gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;   
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Logica.Celda;
import Logica.ControladorNonograma;
import Logica.Grilla;



import java.awt.Font;

/**
 * Clase que representa la interfaz gráfica principal del juego Nonograma.
 * Extiende JFrame para crear la ventana principal de la aplicación.
 */
public class PantallaJuego extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;          // Panel principal que contiene todos los componentes
	private JButton[][] matrizBotones;   
	private final int filas;             
	private final int columnas;          
	private final ControladorNonograma controlador; // Controlador que maneja la lógica del juego
	private  List<JLabel> etiquetasPistasColumnas = new ArrayList<>(); 
	private  List<JLabel> etiquetasPistasFilas = new ArrayList<>(); 


	// Constantes de diseño para la interfaz
	private static final int MARGEN_IZQ_PISTAS = 250;   
	private static final int MARGEN_ARRIBA_PISTAS = 150; 
	private static final int TAMANIO_CELDA = 40;         

	//private int tiempoTranscurrido;
	private JLabel lblTiempo;
	private JPanel panelJuego;
	private JPanel panelGrilla;
	private JPanel panelBtnFin;

	public PantallaJuego(ControladorNonograma controlador, int tiempoLimite) {
		this.controlador = controlador;
		this.filas = controlador.getFilas();
		this.columnas = controlador.getColumnas();
		this.matrizBotones = new JButton[filas][columnas];
		setResizable(false);

		// Inicialización de la interfaz grafica
		inicializarPanelesPrincipales();		
		inicializarComponentesJuego(); 
	}


	private void inicializarComponentesJuego() {
		configurarBtnFin();
		mostrarPistasFilas();
		mostrarPistasColumnas();
		llenarMatrizDeBotones();
		agregarBotonesExtraJuego();
	}

	private void inicializarPanelesPrincipales() {
		configurarVentana();             
		configurarPanelesContenedores();		
		agregarComponentesComunes();

	}


	//Creacion de componentes del juego

	private JButton botonComprobarSolucion() {
		JButton btnComprobar = new JButton("Comprobar");
		btnComprobar.setFont(new Font("Stencil", Font.PLAIN, 30));
		btnComprobar.setBounds(212, 540, 380, 57);
		return btnComprobar;
	}

	private JButton crearBtnVolverAJugar() {
		JButton btnVolverAJugar = new JButton("Volver a jugar");
		btnVolverAJugar.setBounds(212, 540, 380, 57);
		btnVolverAJugar.setFont(new Font("Stencil", Font.PLAIN, 30));
		panelBtnFin.add(btnVolverAJugar);
		return btnVolverAJugar;
	}


	private JButton btnPistaInteligente() {
		// Botón para obtener una pista inteligente
		JButton btnPista = new JButton("Pista Inteligente");
		btnPista.setFont(new Font("Stencil", Font.PLAIN, 12));
		btnPista.setBounds(212, 600, 167, 57);
		panelJuego.add(btnPista);
		return btnPista;
	}

	private JButton crearBtnMenu() {
		JButton btnMenu = new JButton("Menú");
		btnMenu.setBounds(212, 600, 167, 57);
		btnMenu.setFont(new Font("Stencil", Font.PLAIN, 30));
		panelBtnFin.add(btnMenu);
		return btnMenu;
	}

	private JButton crearBtnSalir() {
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(425, 600, 167, 57);
		btnSalir.setFont(new Font("Stencil", Font.PLAIN, 30));
		panelBtnFin.add(btnSalir);
		return btnSalir;
	}


	private JButton crearBotonCelda(int fila, int columna) {
		JButton boton = new JButton();
		boton.setBackground(Color.WHITE);
		boton.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		int posX = MARGEN_IZQ_PISTAS + columna * TAMANIO_CELDA;
		int posY = MARGEN_ARRIBA_PISTAS + fila * TAMANIO_CELDA;
		boton.setBounds(posX, posY, TAMANIO_CELDA, TAMANIO_CELDA);

		return boton;
	}

	private void llenarMatrizDeBotones() {
		for (int fila = 0; fila < filas; fila++) {
			for (int columna = 0; columna < columnas; columna++) {
				JButton boton = crearBotonCelda(fila, columna);
				configurarEventosRaton(boton, fila, columna);
				matrizBotones[fila][columna] = boton;
				panelGrilla.add(boton);
			}
		}
	}

	private void mostrarPistasFilas() {
		etiquetasPistasFilas.clear(); 
		List<int[]> pistasFilas = controlador.getPistasFilas();
		for (int i = 0; i < pistasFilas.size(); i++) {
			int[] valores = pistasFilas.get(i);
			for (int k = 0; k < valores.length; k++) {
				JLabel lbl = new JLabel(String.valueOf(valores[k]), JLabel.CENTER);
				lbl.setBounds((MARGEN_IZQ_PISTAS - (valores.length - k) * TAMANIO_CELDA),
						MARGEN_ARRIBA_PISTAS + i * TAMANIO_CELDA,
						TAMANIO_CELDA, TAMANIO_CELDA);
				lbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				lbl.setForeground(Color.WHITE);
				panelGrilla.add(lbl);
				etiquetasPistasFilas.add(lbl);
			}
		}
	}

	private void mostrarPistasColumnas() {
		etiquetasPistasColumnas.clear();
		List<int[]> pistasColumnas = controlador.getPistasColumnas();
		for (int j = 0; j < pistasColumnas.size(); j++) {
			int[] valores = pistasColumnas.get(j);
			for (int k = 0; k < valores.length; k++) {
				JLabel lbl = new JLabel(String.valueOf(valores[k]), JLabel.CENTER);
				lbl.setBounds(MARGEN_IZQ_PISTAS + j * TAMANIO_CELDA,
						(MARGEN_ARRIBA_PISTAS - valores.length * TAMANIO_CELDA) + k * TAMANIO_CELDA,
						TAMANIO_CELDA, TAMANIO_CELDA);
				lbl.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				lbl.setForeground(Color.WHITE);
				panelGrilla.add(lbl);
				etiquetasPistasColumnas.add(lbl);
			}
		}
	}


	private void configurarEventosRaton(JButton boton, int fila, int columna) {
		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evento) {
				if (SwingUtilities.isLeftMouseButton(evento)) {
					manejarClicIzquierdo(boton, fila, columna);
				} else if (SwingUtilities.isRightMouseButton(evento)) {
					manejarClicDerecho(boton, fila, columna);
				}
			}
		});
	}


	private void manejarClicIzquierdo(JButton boton, int fila, int columna) {
		controlador.usuarioHizoClicIzquierdo(fila, columna);

	}

	private void manejarClicDerecho(JButton boton, int fila, int columna) {
		controlador.usuarioHizoClicDerecho(fila, columna);
	}

	//Agregar botones extra al panel de juego

	public void agregarBotonesExtraJuego() {
		//Boton comprobar
		JButton btnComprobar = botonComprobarSolucion();
		panelJuego.add(btnComprobar);
		btnComprobar.addActionListener(e -> {controlador.usuarioComproboSolucion();
		});

		//Boton pista inteligente
		JButton btnPista = btnPistaInteligente();
		btnPista.addActionListener(e -> {
			controlador.obtienePistaInteligente();
		});

		// Btn para mostrar la solución completa
		JButton btnSolucion = new JButton("Mostrar Solución");
		btnSolucion.setFont(new Font("Stencil", Font.PLAIN, 10));
		btnSolucion.setBounds(425, 600, 167, 57);
		panelJuego.add(btnSolucion);

		btnSolucion.addActionListener(e -> {
			// La Vista solo avisa
			controlador.usuarioPidioSolucion();
		});}


	private void configurarBtnFin() {
		//Boton menu
		JButton btnMenu = crearBtnMenu();
		btnMenu.addActionListener(e -> {
			controlador.usuarioQuiereVolverAlMenu();
		});
		//Boton salir
		JButton btnSalir = crearBtnSalir();	
		btnSalir.addActionListener(e -> {
			System.exit(0);  
		});

		JButton btnVolverAJugar = crearBtnVolverAJugar();
		btnVolverAJugar.addActionListener(e -> {
			// La Vista solo notifica
			controlador.usuarioQuiereVolverAJugar();
		});
	}


	//Metodos llamados por el controlador

	public void actualizarBoton(int fila, int columna, Color color, String texto) {
		JButton boton = matrizBotones[fila][columna]; 
		actualizarAparienciaBoton(boton, color, texto);
	}

	public void destacarPista(int fila, int col) {

		JButton boton = matrizBotones[fila][col];
		final Color original = boton.getBackground();
		final Color destacado = Color.YELLOW;
		final int[] contador = {0};

		Timer timerDestello = new Timer(300, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contador[0] >= 6) {
					boton.setBackground(original); 
					((Timer) e.getSource()).stop();
				} else {
					boton.setBackground(boton.getBackground() == original ? destacado : original);
					contador[0]++;
				}
			}
		});
		timerDestello.start();

	}


	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}



	public void reiniciarVista() {
		panelGrilla.removeAll();
		limpiarPistasFilas();
		limpiarPistasColumnas();


		matrizBotones = new JButton[filas][columnas];
		llenarMatrizDeBotones();
		mostrarPistasFilas();
		mostrarPistasColumnas();


		panelBtnFin.setVisible(false);
		panelGrilla.setVisible(true);
		panelJuego.setVisible(true);
		lblTiempo.setVisible(true);

		panelGrilla.revalidate();
		panelGrilla.repaint();
	}


	public void actualizarLabelTiempo(int tiempoRestante) {
		if (lblTiempo != null) { 
			lblTiempo.setText("Tiempo: " + tiempoRestante + "s");
		}
	}


	public void cerrarVista() {
		dispose();
	}


	public void ganaste() {
		mostrarPantallaFinal(
				"GANASTE",
				"¡Felicitaciones! Has completado el Nonograma",
				"./ganaste.gif",
				true 
				);
	}

	public void perdiste() {
		mostrarSolucion();  
		mostrarPantallaFinal("PERDISTE :(", "             La solución era la siguiente:", null, false);
	}
	
	public void mostrarSolucion() {
		limpiarPistasFilas();
		limpiarPistasColumnas();
		controlador.usuarioPidioSolucion();;

	}

	public void mostrarPantallaFinal(String titulo, String mensaje, String gifPath, boolean ocultarGrilla) {
		gestionarVisibilidadPanelesFin(ocultarGrilla);
		agregarTextosPantallaFinal(titulo, mensaje);
		agregarGifsPantallaFinal(gifPath);
		configurarBtnFin();

	}

	private void actualizarAparienciaBoton(JButton boton, Color colorFondo, String texto) {
		boton.setBackground(colorFondo);
		boton.setText(texto);
	}

	public void limpiarPistasColumnas() {
		for (JLabel lbl : etiquetasPistasColumnas) {
			panelGrilla.remove(lbl);
		}
		etiquetasPistasColumnas.clear();
		panelGrilla.repaint(); 
	}

	public void limpiarPistasFilas() {
		for (JLabel lbl : etiquetasPistasFilas) {
			panelGrilla.remove(lbl);
		}
		etiquetasPistasFilas.clear();
		panelGrilla.repaint(); 
	}

	//Metodos privados de la clase

	private void agregarGifsPantallaFinal(String gifPath) {
		// Gif 
		if (gifPath != null) {
			ImageIcon gif = new ImageIcon(getClass().getResource(gifPath));
			JLabel lblGif = new JLabel(gif);
			lblGif.setBounds(0, 170, 300, 300);
			panelBtnFin.add(lblGif);

			ImageIcon gif2 = new ImageIcon(getClass().getResource(gifPath));
			JLabel lblGif2 = new JLabel(gif2);
			lblGif2.setBounds(300, 0, 901, 710);
			panelBtnFin.add(lblGif2);
		}
	}


	private void agregarTextosPantallaFinal(String titulo, String mensaje) {
		// Titulo 
		JLabel lblTitulo = new JLabel(titulo);
		lblTitulo.setBounds(300, 19, 400, 51);
		lblTitulo.setFont(new Font("Stencil", Font.PLAIN, 50));
		lblTitulo.setForeground(Color.WHITE);
		panelBtnFin.add(lblTitulo);

		// Mensaje 
		JLabel lblMensaje = new JLabel(mensaje);
		lblMensaje.setBounds(70, 70, 800, 30);
		lblMensaje.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblMensaje.setForeground(Color.WHITE);
		panelBtnFin.add(lblMensaje);

		// Tiempo restante
		int tiempoRestante = controlador.getTiempoRestante();
		JLabel lblTiempoFinal = new JLabel("Tiempo restante: " + tiempoRestante + " segundos");
		lblTiempoFinal.setBounds(250, 120, 400, 30);
		lblTiempoFinal.setFont(new Font("Stencil", Font.PLAIN, 20));
		lblTiempoFinal.setForeground(Color.WHITE);
		panelBtnFin.add(lblTiempoFinal);
	}



	private void gestionarVisibilidadPanelesFin(boolean ocultarGrilla) {
		lblTiempo.setVisible(false);

		// Limpiar contenido previo del panel de fin
		panelBtnFin.removeAll();
		panelBtnFin.revalidate();
		panelBtnFin.repaint();

		if (ocultarGrilla) {
			panelGrilla.setVisible(false);
		}
		panelJuego.setVisible(false);
		panelBtnFin.setVisible(true);

	}

	private void agregarComponentesComunes() {
		lblTiempo = new JLabel("Tiempo:");
		lblTiempo.setFont(new Font("Stencil", Font.PLAIN, 30));
		lblTiempo.setForeground(Color.WHITE);
		lblTiempo.setBounds(306, 10, 229, 47);
		lblTiempo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		contentPane.add(lblTiempo);

		//Imagen de Fondo
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(PantallaInicio.class.getResource("./tokio.png")));
		lblFondo.setBounds(0, -15, 896, 697);
		getContentPane().add(lblFondo);

		ImageIcon icono = new ImageIcon(getClass().getResource("/Gui/icono.png"));
		setIconImage(icono.getImage());
	}


	private void configurarPanelesContenedores() {
		panelGrilla = new JPanel();
		panelGrilla.setBounds(62, 68, 598, 430);
		contentPane.add(panelGrilla);
		panelGrilla.setLayout(null);
		panelGrilla.setVisible(true);
		panelGrilla.setOpaque(false);



		panelJuego = new JPanel();
		panelJuego.setBounds(0, 0, 901, 710);
		contentPane.add(panelJuego);
		panelJuego.setLayout(null);
		panelJuego.setVisible(true);
		panelJuego.setOpaque(false);

		panelBtnFin = new JPanel();
		panelBtnFin.setBounds(0, 0, 901, 710);
		contentPane.add(panelBtnFin);
		panelBtnFin.setVisible(false);
		panelBtnFin.setOpaque(false);
		panelBtnFin.setLayout(null);
	}


	private void configurarVentana() {
		setTitle("Nonograma - Cabral,Fauda,Smitch,Valdiviezo");  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
		setBounds(100, 100, 900, 700); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); 
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setVisible(true);
	}

}

