package navalgobattle;

import java.awt.EventQueue;

import java.util.Hashtable;
import navalgobattle.model.naves.TipoNave;


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
		Config.setObject("maxPos", new Posicion(10, 10));
		Config.setObject("puntosPorDefecto", new Integer(10000));
		Hashtable<TipoNave, Integer> navesConfig = new Hashtable<TipoNave, Integer>();
		navesConfig.put(TipoNave.LANCHA, 2);
		navesConfig.put(TipoNave.DESTRUCTOR, 2);
		navesConfig.put(TipoNave.BUQUE, 1);
		navesConfig.put(TipoNave.PORTAAVIONES, 1);
		navesConfig.put(TipoNave.ROMPEHIELOS, 1);
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
