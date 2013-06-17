package navalgobattle.controller;

import java.util.ArrayList;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.ObjetoVivo;

//import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;

public class Nave implements ObjetoVivo, ObjetoPosicionable{
	protected navalgobattle.model.Nave nave;
	protected Posicion posicion;
	protected int ladoX;
	protected int ladoY;

	public Nave(navalgobattle.model.Nave nave, int ladoX, int ladoY) {
		this.nave = nave;
		this.ladoX = ladoX;
		this.ladoY = ladoY;
		this.updatePosicion();
	}

	public int getWidth(){
		return this.ladoX * ( ((this.nave.getDireccion() & 1) != 0 )? this.nave.getSize() : 1);

	}

	public int getHeight(){
		return this.ladoY * ( (( this.nave.getDireccion() & 4) != 0)? this.nave.getSize() : 1);
	}

	protected void updatePosicion(){
		this.posicion = (Posicion) null;
		for(Posicion pos: this.nave.getPosiciones()){
			if(this.posicion == null)
				this.posicion = pos;

			if(this.posicion.isMenor(pos))
				this.posicion = pos;
		}
		if(this.posicion == null){
			System.out.println("Mori");
			//TODO: Habria que sacar el ObjetoVivo, hay otra manera que guardando una referencia interna al GameLoop?
			//FIXME: fijarse como sacar el dibujo, hay otra manera que guardando una referencia a el y gameloop?
			this.posicion = new Posicion(-1000, -1000);
		}
	}

	@Override
	public void vivir() {
		this.updatePosicion();
	}

	public int getX() {
		return this.posicion.getX() *this.ladoX;
	}

	public int getY() {
		return this.posicion.getY() *this.ladoY;
	}
}
