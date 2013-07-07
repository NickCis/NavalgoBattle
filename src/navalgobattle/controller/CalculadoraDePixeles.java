package navalgobattle.controller;

import java.lang.Math;

import navalgobattle.model.Posicion;
import navalgobattle.model.NavalgoBattle;


/** Calculadora de pixeles.
 * Sirve para hacer la conversion de pixeles a posicion
 */
public class CalculadoraDePixeles {
	protected NavalgoBattle juego;
	protected int width;
	protected int height;

	public CalculadoraDePixeles(NavalgoBattle juego, int width, int height){
		this.juego = juego;
		this.width = width;
		this.height = height;
	}

	/** Devuelve el ancho en pixel de un cuadrado (una posicion).
	 */
	public int squareWidth(){
		return (int) Math.round(this.width / (this.juego.getMaximaPosicion().getX()+1));
	}
	/** Devuelve el alto en pixel de un cuadrado (una posicion).
	 */
	public int squareHeight(){
		return (int) Math.round(this.height / (this.juego.getMaximaPosicion().getY()+1));
	}
	/** Convierte pixeles del eje x en posiciones.
	 */
	public int x2pos(int x){
		return (int) Math.round(x * (this.juego.getMaximaPosicion().getX()+1)/ this.width);
	}
	/** Convierte posiciones en pixeles del eje x (devuelve el pixel en el medio del cuadrado).
	 */
	public int pos2x(int xPos){
		return (int) Math.round(xPos * this.width / (this.juego.getMaximaPosicion().getX()+1) +  this.squareWidth()/2);
	}

	/** Convierte pixeles del eje y en posiciones.
	 */
	public int y2pos(int y){
		return (int) Math.round(y * (this.juego.getMaximaPosicion().getY()+1)/ this.height);
	}

	/** Convierte posiciones en pixeles del eje y (devuelve el pixel en el medio del cuadrado).
	 */
	public int pos2y(int yPos){
		return (int) Math.round(yPos * this.height/ (this.juego.getMaximaPosicion().getY()+1)+this.squareHeight()/2);
	}
}
