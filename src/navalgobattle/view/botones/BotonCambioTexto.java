package navalgobattle.view.botones;

import javax.swing.JButton;

import navalgobattle.view.Boton;
import navalgobattle.view.Ventana;
import navalgobattle.view.ventanas.VentanaJuego;

public class BotonCambioTexto extends Boton {
	protected String texto;

	public BotonCambioTexto(String texto){
		this.texto = texto;
		this.component = new JButton(texto);
		this.width = 100;
		this.height = 25;
	}

	public void action(Ventana ventana){
		VentanaJuego juego = (VentanaJuego) ventana;
		juego.setPuntos(this.texto);
	}
}
