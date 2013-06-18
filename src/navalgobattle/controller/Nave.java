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

	/** Constructor.
	 * @param navalgobattle.model.Nave nave: Nave del modelo el cual es representada por esta nave del controller.
	 * @param GameLoop gameLoop: gameLoop.
	 * @param int ladoX: ancho en pixeles de la posicion (un cuadrado).
	 * @param int ladoY: alto en pixeles de la posicion (un cuadrado).
	 */
	public Nave(navalgobattle.model.Nave nave, GameLoop gameLoop, int ladoX, int ladoY) {
		this.nave = nave;
		this.gameLoop = gameLoop;
		this.ladoX = ladoX;
		this.ladoY = ladoY;
		this.agregarEnJuego();
	}

	/** Agrega a la nave al juego.
	 * Crea los cuadraditos que son cada parte de la nave.
	 */
	protected void agregarEnJuego(){
		int numeroNave = 0;
		final Nave controllerNave = this;
		for(Posicion pos: this.nave.getPosiciones()){
			final Integer estaNave = new Integer(numeroNave++);
			//XXX: Aca se agregan todos los cuadraditos usando clases Anonimas, se podria hacer de una manera mas linda. Ademas se hardcodea el color.
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
		//XXX: Actualmente el objeto vivo no hace nada, se puede implementar para hacer una animacion o movimiento fluido
		//this.gameLoop.agregar(this);
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
		//FIXME: buscar otra fomra de hacerlo desaparecer,,
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
