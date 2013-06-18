package navalgobattle.controller;

import java.awt.Color;

import java.util.ArrayList;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;
import fiuba.algo3.titiritero.modelo.ObjetoVivo;
import fiuba.algo3.titiritero.modelo.GameLoop;
import fiuba.algo3.titiritero.dibujables.Cuadrado;

//import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;

import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;

//public class Nave implements ObjetoVivo, ObjetoPosicionable{
public class Nave implements ObjetoVivo{
	protected navalgobattle.model.Nave nave;
	protected GameLoop gameLoop;
	protected Posicion posicion;
	protected int ladoX;
	protected int ladoY;

	public Nave(navalgobattle.model.Nave nave, GameLoop gameLoop, int ladoX, int ladoY) {
		Logger.log(LogLevel.ERROR, "Creando nave controller");
		this.nave = nave;
		this.gameLoop = gameLoop;
		this.ladoX = ladoX;
		this.ladoY = ladoY;
		this.agregarEnJuego();
		//this.updatePosicion();
	}

	protected void agregarEnJuego(){
		Logger.log(LogLevel.INFO, "Agregar en juego");
		int numeroNave = 0;
		final Nave controllerNave = this;
		for(Posicion pos: this.nave.getPosiciones()){
			final Integer estaNave = new Integer(numeroNave++);
			this.gameLoop.agregar(new Cuadrado(this.ladoX, this.ladoY, (ObjetoPosicionable) new ObjetoPosicionable(){
				public int getX(){
					return controllerNave.getX(estaNave);
				}
				public int getY(){
					return controllerNave.getY(estaNave);
				}
			}){
				public Color getColor(){
					return Color.GREEN;
				}
			});
		}
		this.gameLoop.agregar(this);
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
			Logger.log(LogLevel.INFO, "Mori");
			//TODO: Habria que sacar el ObjetoVivo, hay otra manera que guardando una referencia interna al GameLoop?
			//FIXME: fijarse como sacar el dibujo, hay otra manera que guardando una referencia a el y gameloop?
			this.posicion = new Posicion(-1000, -1000);
		}
	}

	@Override
	public void vivir() {
		Logger.log(LogLevel.INFO, "Estoy viviendo");
		//this.updatePosicion();
	}

	public int getX(int numeroNave) {
		Logger.log(LogLevel.INFO, "xnumeroNave"+numeroNave);
		int num = 0;
		for(Posicion pos: this.nave.getPosiciones()){
			if(numeroNave == num++)
				return pos.getX() *this.ladoX;
		}
		return -1000;
	}

	public int getY(int numeroNave) {
		Logger.log(LogLevel.INFO, "numeroNave"+numeroNave);
		int num = 0;
		for(Posicion pos: this.nave.getPosiciones()){
			if(numeroNave == num++)
				return pos.getY() *this.ladoY;
		}
		return -1000;
	}
}
