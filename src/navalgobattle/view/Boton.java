package navalgobattle.view;

import javax.swing.JButton;

import navalgobattle.view.Ventana;

public abstract class Boton {
	protected int width;
	protected int height;
	protected JButton component;

	public Boton(){}

	public abstract void action(Ventana ventana);

	public int getWidth(){
		return width;
	}

	public int getHeight(){
		return height;
	}

	public JButton getComponent(){
		return component;
	}
}
