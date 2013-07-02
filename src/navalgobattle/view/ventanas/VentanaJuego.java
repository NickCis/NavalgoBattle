package navalgobattle.view.ventanas;

import java.util.ArrayList;

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

import fiuba.algo3.titiritero.modelo.GameLoop;
import fiuba.algo3.titiritero.modelo.ObjetoVivo;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

import fiuba.algo3.titiritero.dibujables.Circulo;
import fiuba.algo3.titiritero.dibujables.Figura;
import fiuba.algo3.titiritero.dibujables.Cuadrado;
import fiuba.algo3.titiritero.dibujables.Imagen;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;

//import navalgobattle.view.Nave;
import navalgobattle.view.Ventana;
import navalgobattle.view.Botonera;
import navalgobattle.view.botones.BotonCambioTexto;
import navalgobattle.view.botones.BotonSeleccionarDisparo;

import navalgobattle.controller.Juego;
import navalgobattle.controller.event.EventJuegoTerminado;
import navalgobattle.controller.event.EventJuegoSiguienteTurno;

import navalgobattle.model.disparos.TipoDisparo;

import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;

/** Ventana de juego.
 */
public class VentanaJuego extends Ventana{

	protected GameLoop gameLoop;
	protected Juego juego;
	protected JLabel labelTurno;
	protected JLabel labelPuntos;
	protected JLabel labelDisparo;
	protected TipoDisparo disparo; 

	/**
	 * Create the application.
	 */
	public VentanaJuego() {
		super();
	}


	public int getWidth(){
		return 600;
	}
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */

	protected void initialize() throws IOException {
		super.initialize();
		//FIXME: Cabeceada para tener el this dentro de los eventos
		final VentanaJuego that = this;

		final JPanel panel = new SuperficiePanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(35, 45, 400, 200);
		this.add(panel);

		this.labelTurno = new JLabel("Turno: ");
		this.labelTurno.setBounds(0, 0, 200, 50);
		this.labelPuntos = new JLabel("Puntos: ");
		this.labelPuntos.setBounds(200, 0, 400, 50);

		this.labelDisparo = new JLabel("Disparo: ");
		this.labelDisparo.setBounds(0, 240, 500, 100);

		this.add(this.labelTurno);
		this.add(this.labelPuntos);
		this.add(this.labelDisparo);

		Botonera botonera = new Botonera(this, 0, 250, 10, 0);

		BotonSeleccionarDisparo convencional = new BotonSeleccionarDisparo(TipoDisparo.CONVENCIONAL, "Convencional");
		botonera.add(convencional);
		BotonSeleccionarDisparo minaSimple = new BotonSeleccionarDisparo(TipoDisparo.MINA_SIMPLE, "Mina simple");
		botonera.add(minaSimple);
		BotonSeleccionarDisparo minaDoble = new BotonSeleccionarDisparo(TipoDisparo.MINA_DOBLE, "Mina doble");
		botonera.add(minaDoble);
		BotonSeleccionarDisparo minaTriple = new BotonSeleccionarDisparo(TipoDisparo.MINA_TRIPLE, "Mina Triple");
		botonera.add(minaTriple);
		BotonSeleccionarDisparo minaContacto = new BotonSeleccionarDisparo(TipoDisparo.MINA_CONTACTO, "Mina Contacto");
		botonera.add(minaContacto);

		this.gameLoop = new GameLoop((SuperficieDeDibujo) panel);

		//Ancho y alto del panel
		this.juego = new Juego(400, 200, this.gameLoop);
		this.juego.agregarNavesRandom();

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				that.juego.disparar(that.disparo, arg0.getX(), arg0.getY());
			}
		});

		//FIXME: No me deja autoiniciar el gameloop, es algo relacionado con que SuperficiePanel no esta lista en esta instancia.
		//TODO: se deberia llamar al detener en algun lado
		//that.gameLoop.iniciarEjecucion();
		JButton btnIniciar = new JButton("Iniciar Juego");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				that.gameLoop.iniciarEjecucion();
				that.setTurno();
				that.setPuntos();
			}
		});
		btnIniciar.setBounds(325, 16, 92, 25);
		this.add(btnIniciar);


		////Agregamos un ObjetoVivo para que se actualice la informacion en pantalla
		//this.gameLoop.agregar(new ObjetoVivo(){
		//	@Override
		//	public void vivir(){
		//		Logger.log(LogLevel.DEBUG, "Relodeo muchoo =)");
		//		that.setTurno();
		//		that.setPuntos();
		//	}
		//});
		this.juego.addJuegoSiguienteTurnoListener(new EventJuegoSiguienteTurno(){
			public void siguienteTurno(){
				Logger.log(LogLevel.DEBUG, "Se llama evento de siguiente turno");
				that.setTurno();
				that.setPuntos();
			}
		});
		this.juego.addJuegoTerminadoListener(new EventJuegoTerminado(){
			public void juegoTermino(final boolean gano, final int puntos){
				Logger.log(LogLevel.DEBUG, "**Fin de juego. Gano: "+gano+" Puntos:"+puntos);
				//TODO: hacer clase Alert y pasarlo ahi, en vez de hacer esto feo.
				new Ventana(){
					public void initialize() throws IOException{
						super.initialize();
						JLabel texto = new JLabel("**Fin de juego. Gano: "+gano+" Puntos:"+puntos);
						JButton cerrar = new JButton("Cerrar");
						final Ventana alert = this;
						cerrar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								that.close();
								alert.close();
							}
							public int getWidth(){
								return 400;
							}
							public int getHeight(){
								return 200;
							}
						});
						cerrar.setBounds(200, 150, 92, 25);
						this.add(cerrar);
						texto.setBounds(20, 10, 350, 150);
						this.add(texto);
					}
				};
			}
		});
	}

	public void setTurno(){
		this.setTurno(this.juego.getTurno());
	}
	public void setTurno(int turno){
		this.labelTurno.setText("Turno :"+turno);
	}
	public void setPuntos(){
		this.setPuntos(this.juego.getPuntos());
	}
	public void setPuntos(int puntos){
		this.setPuntos(""+puntos);
	}
	public void setPuntos(String txt){
		this.labelPuntos.setText("Puntos :"+txt);
	}

	public void setDisparo(TipoDisparo disparo, String txt){
		this.disparo = disparo;
		this.labelDisparo.setText("Disparo :"+txt);
	}
}
