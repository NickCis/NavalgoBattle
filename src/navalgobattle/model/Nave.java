package navalgobattle.modelo

import java.util.ArrayList

import navalgobattle.NavalgoBattle
import navalgobattle.Disparo
/**
 * Nave.
 * 
 * 
 * @author todos vamos a meter mano
 */
public class Nave {
	private int tam;
	private int direccion;
	private ArrayList<Posicion> posVidas;
	private NavalgoBattle juego;

	public Nave(int xMax, int yMax){
	}

	/** Mueve la nave.
	 * 1) Efectua movimiento de la nave, teniendo en cuenta si rebota o no contra los costados
	 * 2) Actualiza las posiciones de su arrayList.
	 * @return boolean, verdadero si la nave se movio y tiene vida, falso si la nave esta muerta.
	 */
	public boolean mover(){
	}

	/** Esta viva.
	 * @return boolean devuelve si la nave esta viva.
	 */
	public boolean estaViva(){
	}

	/** Danar a una nave.
	 * @param Disparo disparo, instancia de disparo que quiere danar a la nave.
	 */
	public void danar(Disparo disparo){
	}
}

private class Posicion {
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
	public int setXY(int x, int y){
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
	}
}
