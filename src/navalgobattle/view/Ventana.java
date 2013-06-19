package navalgobattle.view;

import java.io.IOException;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;

public abstract class Ventana {
	protected JFrame frame;
	/**
	 * Create the application.
	 */
	public Ventana() {
		try {
			initialize();
			this.frame.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */

	protected void initialize() throws IOException {
		this.close();
		this.frame = new JFrame();
		this.frame.setForeground(new Color(0, 0, 0));
		this.frame.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		this.frame.setFocusable(true);
	}

	public int getX(){
		return 100;
	}
	public int getY(){
		return 100;
	}
	public int getWidth(){
		return 450;
	}
	public int getHeight(){
		return 300;
	}

	public void setVisible(boolean bol){
		this.frame.setVisible(bol);
	}

	public void close(){
		if(this.frame != null){
			this.frame.setVisible(false);
			this.frame.dispose();
		}
	}

	public void add(Component c){
		this.frame.getContentPane().add(c);
	}

}
