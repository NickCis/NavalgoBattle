package navalgobattle;

import java.awt.EventQueue;

import navalgobattle.view.ventanas.VentanaPrincipal;

/**
 * Main del juego
 */

public class Main {
	public static void main(String[] args) {
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
