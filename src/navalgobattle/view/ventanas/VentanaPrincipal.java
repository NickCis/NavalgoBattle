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
import javax.swing.JFrame;
import javax.swing.JPanel;

import navalgobattle.view.Ventana;
import navalgobattle.view.ventanas.VentanaJuego;

import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;

public class VentanaPrincipal extends Ventana{
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	public VentanaPrincipal() {
		super();
	}

	protected void initialize() throws IOException {
		super.initialize();
		Logger.log(LogLevel.DEBUG, "Creando ventana principal");

		JButton btnIniciar = new JButton("Nuevo Juego");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new VentanaJuego();
				}catch(Exception exp){}
			}
		});
		btnIniciar.setBounds(42, 16, 77, 25);
		this.frame.getContentPane().add(btnIniciar);
		
		/*
		JButton btnDetener = new JButton("Otra cosa");
		btnDetener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDetener.setBounds(325, 16, 92, 25);
		this.frame.getContentPane().add(btnDetener);

		btnDetener.setFocusable(false);*/
		btnIniciar.setFocusable(false);

		this.frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Logger.log(LogLevel.INFO, "Key pressed");
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				Logger.log(LogLevel.INFO, "Ping");
			}
		});
	}
}
