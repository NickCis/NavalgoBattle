package navalgobattle.view.ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import modelo.ObjetoMultiforma;
import fiuba.algo3.titiritero.dibujables.Circulo;
import fiuba.algo3.titiritero.dibujables.Figura;
import fiuba.algo3.titiritero.dibujables.Imagen;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.GameLoop;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

import navalgobattle.view.Ventana;
import navalgobattle.view.Botonera;

import navalgobattle.view.botones.BotonCambioTexto;

public class VentanaJuego extends Ventana{

	protected GameLoop gameLoop;
	protected JLabel labelTurno;
	protected JLabel labelPuntos;

	/**
	 * Create the application.
	 */
	public VentanaJuego() {
		super();
	}


	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */

	protected void initialize() throws IOException {
		super.initialize();

		JPanel panel = new SuperficiePanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(42, 53, 375, 187);
		frame.getContentPane().add(panel);

		this.labelTurno = new JLabel("Turno: ");
		this.labelTurno.setBounds(0, 0, 200, 50);
		this.labelPuntos = new JLabel("Puntos: ");
		this.labelPuntos.setBounds(200, 0, 400, 50);

		this.add(this.labelTurno);
		this.add(this.labelPuntos);

		Botonera botonera = new Botonera(this, 0, 250, 10, 0);

		BotonCambioTexto b = new BotonCambioTexto("Pepe");
		botonera.add(b);
		BotonCambioTexto c = new BotonCambioTexto("Mas texto");
		botonera.add(c);

		this.gameLoop = new GameLoop((SuperficieDeDibujo) panel);
		//final ObjetoMultiforma modelo = new ObjetoMultiforma();
		//this.gameLoop.agregar(modelo);
		//Circulo circulo = new VistaObjetoMultiforma(modelo);
		//this.gameLoop.agregar(circulo);

		//ObjetoMultiforma modelo2 = new ObjetoMultiforma();
		//modelo2.mutar();
		//this.gameLoop.agregar(modelo2);
		//Figura cuadrado = new Vista2ObjetoMultiforma(modelo2);
		//this.gameLoop.agregar(cuadrado);

		//ObjetoMultiforma modelo3 = new ObjetoMultiforma();
		//modelo3.inmutar();
		//this.gameLoop.agregar(modelo3);
		//Imagen imagen = new Vista3ObjetoMultiforma(modelo3);
		//this.gameLoop.agregar(imagen);

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//modelo.moverA(arg0.getX(), arg0.getY());
			}
		});

		gameLoop.iniciarEjecucion();
	}

	public void setTurno(int turno){
		this.labelTurno.setText("Turno :"+turno);
	}
	public void setPuntos(int puntos){
		this.setPuntos(""+puntos);
	}
	public void setPuntos(String txt){
		this.labelPuntos.setText("Puntos :"+txt);
	}
}
