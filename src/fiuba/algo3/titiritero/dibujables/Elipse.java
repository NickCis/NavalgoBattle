package fiuba.algo3.titiritero.dibujables;

import java.awt.Graphics;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class Elipse extends Figura {

	private int radioX;
	private int radioY;
	
	public Elipse(int radioX, int radioY, ObjetoPosicionable objetoPosicionable) {
		super(objetoPosicionable);
		this.radioX = radioX;
		this.radioY = radioY;
	}

	public int getRadioX() {
		return this.radioX;
	}

	public int getRadioY() {
		return this.radioY;
	}
	
	@Override
	public void dibujar(SuperficieDeDibujo superfice) {
		Graphics grafico = ((SuperficiePanel)superfice).getBuffer();
		grafico.setColor(this.getColor());
		grafico.fillOval(getPosicionable().getX() , getPosicionable().getY(), this.radioX, this.radioY);
	}
}
