package Gui;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Logica.ControladorNonograma;
import Logica.Nonograma;
import Logica.Dificultad;

public class PantallaInicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private ImageIcon gif;
    private JComboBox<Dificultad> comboDificultad;
        
    public PantallaInicio() {
        configurarVentana();
        agregarTitulo();
        agregarSelectorDificultad();
        agregarBotonJugar();
        agregarBotonAyuda();
        agregarDecoracion();
    }

    ////////////////////////////////////////////////////
   	/////////Configuracion inicial////////
   	/////////////////////////////////////////////////////
   	
    private void configurarVentana() {
		setTitle("Nonograma - Cabral, Smicht, Fuda, Valdiviezo");  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 700);
        setResizable(false);
        getContentPane().setLayout(null);
        ImageIcon icono = new ImageIcon(getClass().getResource("/Gui/icono.png"));
	    setIconImage(icono.getImage());
    }

   	
    private void agregarTitulo() {
        JLabel lblInicio = new JLabel("¡Bienvenido!");
        lblInicio.setFont(new Font("Stencil", Font.PLAIN, 70));
        lblInicio.setBounds(180, 23, 547, 68);
        lblInicio.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblInicio);

        JLabel lblTitulo = new JLabel("Nonograma");
        lblTitulo.setFont(new Font("Stencil", Font.PLAIN, 40));
        lblTitulo.setBounds(262, 88, 310, 57);
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblTitulo);
    }

    private void agregarSelectorDificultad() {
        JLabel lblDificultad = new JLabel("Elija dificultad:");
        lblDificultad.setFont(new Font("Stencil", Font.PLAIN, 20));
        lblDificultad.setForeground(new java.awt.Color(255, 255, 255));
        lblDificultad.setBounds(477, 515, 200, 30);
        getContentPane().add(lblDificultad);

        comboDificultad = new JComboBox<>(Dificultad.values());
        comboDificultad.setFont(new Font("Stencil", Font.PLAIN, 20));
        comboDificultad.setBounds(477, 555, 250, 40);
        getContentPane().add(comboDificultad);
    }

    
    private void agregarBotonJugar() {
        JButton btnJugar = new JButton("Jugar");
        btnJugar.setFont(new Font("Stencil", Font.PLAIN, 30));
        btnJugar.setBounds(34, 510, 170, 100);
        getContentPane().add(btnJugar);

        btnJugar.addActionListener(e -> iniciarJuego());
    }

    private void iniciarJuego() {
        Dificultad dificultad = (Dificultad) comboDificultad.getSelectedItem();

        Nonograma nonograma = new Nonograma(dificultad.getFilas(), dificultad.getColumnas());
        ControladorNonograma controlador = new ControladorNonograma(nonograma, 200);

        PantallaJuego juego = new PantallaJuego(controlador, 240);
        controlador.setVista(juego);
        juego.setVisible(true);
        dispose();
        controlador.iniciarJuego();
    }
  
    
    private void agregarBotonAyuda() {
        JButton btnAyuda = new JButton("<html>¿Cómo<br>jugar?</html>");
        btnAyuda.setFont(new Font("Stencil", Font.PLAIN, 30));
        btnAyuda.setBounds(251, 510, 170, 100);
        getContentPane().add(btnAyuda);

        btnAyuda.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, 
                "El Nonograma se juega de la siguiente manera:\n"
                + "1. El objetivo es llenar una cuadrícula con celdas negras y blancas según las pistas.\n"
                + "2. Las pistas indican la cantidad de celdas negras consecutivas.\n"
                + "3. Usa el clic izquierdo para pintar una celda de negro.\n"
                + "4. Usa el clic derecho para marcar con X.\n",
                "Ayuda", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    ////////////////////////////////////////////////////
   	/////////Decoracion////////
   	/////////////////////////////////////////////////////
   
    private void agregarDecoracion() {
        // GIF decorativo
        gif = new ImageIcon(getClass().getResource("/Gui/imagen.gif"));
        JLabel lblGif = new JLabel("");
        lblGif.setBounds(278, 178, 248, 260);
        lblGif.setIcon(gif);
        getContentPane().add(lblGif);

        // Fondo
        JLabel lblFondo = new JLabel("");
        lblFondo.setIcon(new ImageIcon(PantallaInicio.class.getResource("./tokio.png")));
        lblFondo.setBounds(0, -15, 896, 697);
        getContentPane().add(lblFondo);
    }
}
