package navalgobattle.controller;

import java.awt.Color;

import java.util.ArrayList;

import fiuba.algo3.titiritero.modelo.GameLoop;
import fiuba.algo3.titiritero.modelo.ObjetoVivo;
import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

import fiuba.algo3.titiritero.dibujables.Cuadrado;

//import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;

import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;

/** Clase Nave del controller.
 * Esta se encarga de agregar los cuadrados al gameLoop y de actualizar sus posiciones.
 */
public class Nave implements ObjetoVivo{
	protected navalgobattle.model.Nave nave;
	protected GameLoop gameLoop;
	protected Posicion posicion;
	protected int ladoX;
	protected int ladoY;
	protected navalgobattle.model.naves.TipoNave tipo;

	/** Constructor.
	 * @param navalgobattle.model.Nave nave: Nave del modelo el cual es representada por esta nave del controller.
	 * @param GameLoop gameLoop: gameLoop.
	 * @param int ladoX: ancho en pixeles de la posicion (un cuadrado).
	 * @param int ladoY: alto en pixeles de la posicion (un cuadrado).
	 */
	public Nave(navalgobattle.model.Nave nave, GameLoop gameLoop, int ladoX, int ladoY, navalgobattle.model.naves.TipoNave tipo) {
		this.nave = nave;
		this.gameLoop = gameLoop;
		this.ladoX = ladoX;
		this.ladoY = ladoY;
		this.tipo = tipo;
		this.agregarEnJuego();
	}

	/** Agrega a la nave al juego.
	 * Crea los cuadraditos que son cada parte de la nave.
	 */
	protected void agregarEnJuego(){
		int numeroNave = 0;
		final Nave controllerNave = this;
		Color miColor = Color.GREEN;
		switch(this.tipo){
			case NONE:
				miColor = Color.GREEN;
				break;
			case LANCHA:
				miColor = Color.BLUE;
				break;
			case DESTRUCTOR:
				miColor = Color.RED;
				break;
			case PORTAAVIONES:
				miColor = Color.YELLOW;
				break;
			case ROMPEHIELOS:
				miColor = Color.CYAN;
				break;
			case BUQUE:
				miColor = Color.WHITE;
				break;
		}
		final Color color = miColor;
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
					return color;
				}
			});
		}
	}

	@Override
	public void vivir() {
		if(!this.nave.estaViva())
			this.gameLoop.remover(this);
	}

	/** Devuelve la coordenada de X, dependiendo que aprte de la nave sea.
	 * @param int numeroNave: numero el cual identifica qe parte de la nave es.
	 */
	public int getX(int numeroNave) {
		int num = 0;
		for(Posicion pos: this.nave.getPosiciones()){
			if(numeroNave == num++)
				return pos.getX() *this.ladoX;
		}
		return -1000;
	}

	/** Devuelve la coordenada de Y, dependiendo que aprte de la nave sea.
	 * @param int numeroNave: numero el cual identifica qe parte de la nave es.
	 */
	public int getY(int numeroNave) {
		int num = 0;
		for(Posicion pos: this.nave.getPosiciones()){
			if(numeroNave == num++)
				return pos.getY() *this.ladoY;
		}
		return -1000;
	}
}
