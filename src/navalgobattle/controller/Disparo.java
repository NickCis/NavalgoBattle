package navalgobattle.controller;

import java.lang.Math;

import java.awt.Color;

import java.util.ArrayList;

import fiuba.algo3.titiritero.modelo.GameLoop;
import fiuba.algo3.titiritero.modelo.ObjetoVivo;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

import fiuba.algo3.titiritero.dibujables.Elipse;

//import navalgobattle.model.Disparo;
import navalgobattle.model.Posicion;
import navalgobattle.model.disparos.TipoDisparo;

import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;

/** Clase Disparo del controller.
 * 
 */
public class Disparo implements ObjetoVivo{
	protected navalgobattle.model.Disparo disparo;
	protected GameLoop gameLoop;
	protected Posicion posicion;
	protected int ladoX;
	protected int ladoY;
	protected TipoDisparo tipo;

	/** Constructor.
	 * @param navalgobattle.model.Disparo disparo: disparo del modelo el cual es representada por esta nave del controller.
	 * @param GameLoop gameLoop: gameLoop.
	 * @param int ladoX: ancho en pixeles de la posicion (un cuadrado).
	 * @param int ladoY: alto en pixeles de la posicion (un cuadrado).
	 */
	public Disparo(navalgobattle.model.Disparo disparo, GameLoop gameLoop, int ladoX, int ladoY, TipoDisparo tipo) {
		this.disparo = disparo;
		this.gameLoop = gameLoop;
		this.ladoX = ladoX;
		this.ladoY = ladoY;
		this.tipo = tipo;
		this.agregarEnJuego();
	}

	/** Agrega al disparo al juego.
	 */
	protected void agregarEnJuego(){
		final Disparo controllerDisparo = this;
		Color color = Color.BLUE;
		switch(this.tipo){
			case CONVENCIONAL:
				color = Color.BLUE;
				break;
			case MINA_SIMPLE:
				color = Color.GREEN;
				break;
			case MINA_DOBLE:
				color = Color.RED;
				break;
			case MINA_TRIPLE:
				color = Color.YELLOW;
				break;
			case MINA_CONTACTO:
				color = Color.CYAN;
				break;
		}

		final Color tColor = color;
		this.gameLoop.agregar(new Elipse(this.ladoX, this.ladoY, (ObjetoPosicionable) new ObjetoPosicionable(){
			public int getX(){
				return controllerDisparo.getX();
			}
			public int getY(){
				return controllerDisparo.getY();
			}
		}){
			public Color getColor(){
				return tColor;
			}
		});
	}

	@Override
	public void vivir() {
		if(this.disparo.disparo())
			this.gameLoop.remover(this);
	}

	/** Devuelve la coordenada de X;
	 */
	public int getX() {
		if(this.disparo.disparo())
			return -1000;
		return (int) Math.round(this.disparo.getPosicion().getX() * this.ladoX);// + this.ladoX/2);
	}

	/** Devuelve la coordenada de Y;
	 */
	public int getY() {
		if(this.disparo.disparo())
			return -1000;
		return (int) Math.round(this.disparo.getPosicion().getY() * this.ladoY);// + this.ladoY/2);
	}
}
