package navalgobattle.model;

import navalgobattle.model.NavalgoBattle;
/**
 * Disparo. Clase abstracta.
 */
public abstract class Disparo {
	protected int x;
	protected int y;
	protected int costo;
	protected NavalgoBattle juego;

	public Disparo(int x, int y){
	}
	/** Efectua un disparo.
	 * 1) Controla si efectivamente tiene que disparar.
	 * 2) Si tiene qe disparar, pide al juego las naves en las posiciones que tiene que atacar (NavalgoBattle.naveEnPosicion).
	 * 3) Llama a metodo danar de la nave, pasandose a si mismo.
	 * 4) Si es que disparo, devuelve true. Si no disparo, devuelve false.
	 * @return boolean Se efectuo el disparo?.
	 */
	public boolean disparar(){
		return true;
	}

	/** Devuelve el costo.
	 */
	public int getCosto(){
		return costo;
	}
}
