package navalgobattle.view.botones;

import javax.swing.JButton;

import navalgobattle.controller.TipoDisparo;

import navalgobattle.view.Boton;
import navalgobattle.view.Ventana;
import navalgobattle.view.ventanas.VentanaJuego;

public class BotonSeleccionarDisparo extends Boton {
	protected String texto;
	protected TipoDisparo disparo;

	public BotonSeleccionarDisparo(TipoDisparo disparo, String texto){
		this.disparo = disparo;
		this.texto = texto;
		this.component = new JButton(texto);
		this.width = 100;
		this.height = 25;
	}

	public void action(Ventana ventana){
		VentanaJuego juego = (VentanaJuego) ventana;
		juego.setDisparo(this.disparo, this.texto);
	}
}
