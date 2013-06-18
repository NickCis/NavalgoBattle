package navalgobattle;

import java.awt.EventQueue;

import navalgobattle.util.config.Config;
import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;

import navalgobattle.view.ventanas.VentanaPrincipal;

import navalgobattle.model.Posicion;

/**
 * Main del juego
 */

public class Main {
	public static void main(String[] args) {
		Config.initialice();
		//TODO: Pasar la configuracion por defecto a otro lado o levantarla de archivo.
		Config.setObject("maxPos", new Posicion(10, 10));

		Logger.initialice();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
