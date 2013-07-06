package navalgobattle;

import java.awt.EventQueue;

import java.util.Hashtable;
import java.lang.reflect.Constructor;


import navalgobattle.util.config.Config;
import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;

import navalgobattle.view.ventanas.VentanaPrincipal;

import navalgobattle.model.Posicion;

/**
 * Main del juego
 */

public class Main {
	public static void main(String[] args) throws Exception {
		Config.initialice();
		//TODO: Pasar la configuracion por defecto a otro lado o levantarla de archivo.
		Config.setObject("maxPos", new Posicion(10, 10));
		Config.setObject("puntosPorDefecto", new Integer(10000));
		Hashtable<Constructor, Integer> navesConfig = new Hashtable<Constructor, Integer>();
		//navesConfig.put(navalgobattle.model.naves.Lancha.class.getDeclaredConstructor(Posicion.class, Posicion.class, int.class), 2);
		navesConfig.put(navalgobattle.model.naves.Destructor.class.getDeclaredConstructor(Posicion.class, Posicion.class, int.class), 2);
		//navesConfig.put(navalgobattle.model.naves.Buque.class.getDeclaredConstructor(Posicion.class, Posicion.class, int.class), 1);
		//navesConfig.put(navalgobattle.model.naves.PortaAviones.class.getDeclaredConstructor(Posicion.class, Posicion.class, int.class), 1);
		//navesConfig.put(navalgobattle.model.naves.RompeHielos.class.getDeclaredConstructor(Posicion.class, Posicion.class, int.class), 1);
		Config.setObject("navesDefault", navesConfig);

		//=======

		Logger.initialice();
		Logger.setLogLevel(LogLevel.DEBUG);
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
