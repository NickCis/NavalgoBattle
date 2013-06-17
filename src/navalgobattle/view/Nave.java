package navalgobattle.view;

import java.awt.Color;

import fiuba.algo3.titiritero.modelo.ObjetoVivo;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.dibujables.Cuadrado;

public class Nave extends Cuadrado{
	protected navalgobattle.controller.Nave nave;
	
	public Nave(navalgobattle.controller.Nave nave){
		//super(30, 10, nave);
		super(nave.getWidth(), nave.getHeight(), nave);
		this.nave = nave;
		this.setColor(Color.GREEN);
	}

	public navalgobattle.controller.Nave getVivo(){
		return this.nave;
	}
}
