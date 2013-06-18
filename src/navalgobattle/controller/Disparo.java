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

	/** Constructor.
	 * @param navalgobattle.model.Disparo disparo: disparo del modelo el cual es representada por esta nave del controller.
	 * @param GameLoop gameLoop: gameLoop.
	 * @param int ladoX: ancho en pixeles de la posicion (un cuadrado).
	 * @param int ladoY: alto en pixeles de la posicion (un cuadrado).
	 */
	public Disparo(navalgobattle.model.Disparo disparo, GameLoop gameLoop, int ladoX, int ladoY) {
		this.disparo = disparo;
		this.gameLoop = gameLoop;
		this.ladoX = ladoX;
		this.ladoY = ladoY;
		this.agregarEnJuego();
	}

	/** Agrega al disparo al juego.
	 */
	protected void agregarEnJuego(){
		final Disparo controllerDisparo = this;
		//XXX: Se usan clases Anonimas, se podria hacer de una manera mas linda. Ademas se hardcodea el color.
		this.gameLoop.agregar(new Elipse(this.ladoX, this.ladoY, (ObjetoPosicionable) new ObjetoPosicionable(){
			public int getX(){
				return controllerDisparo.getX();
			}
			public int getY(){
				return controllerDisparo.getY();
			}
		}){
			public Color getColor(){
				return Color.RED;
			}
		});
		//XXX: Actualmente el objeto vivo no hace nada, se puede implementar para hacer una animacion o movimiento fluido
		//this.gameLoop.agregar(this);
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
			//FIXME: buscar otra fomra de hacerlo desaparecer,,
			return -1000;
		return (int) Math.round(this.disparo.getPosicion().getX() * this.ladoX);// + this.ladoX/2);
	}

	/** Devuelve la coordenada de Y;
	 */
	public int getY() {
		if(this.disparo.disparo())
			//FIXME: buscar otra fomra de hacerlo desaparecer,,
			return -1000;
		return (int) Math.round(this.disparo.getPosicion().getY() * this.ladoY);// + this.ladoY/2);
	}
}
