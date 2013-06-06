
package navalgobattle.model;

import java.util.ArrayList;
import java.util.Iterator;

import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Disparo;
import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;

public class Posicion {
	private int x;
	private int y;
	private int vida;
	public Posicion(int x, int y, int vida){
		this.x = x;
		this.y = y;
		this.vida = vida;
	}
	public int getX(){
		return this.x;
	}
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getY(){
		return this.y;
	}

	public int getVida(){
		return this.vida;
        }

	public int setVida(int vida){
		this.vida = vida;
		return this.vida;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj.getClass() != getClass()) return false;

		Posicion pos = (Posicion)obj;
		return pos.getX() == this.getX() && pos.getY() == this.getY();
	}
}
