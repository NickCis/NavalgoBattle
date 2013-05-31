package navalgobattle.model;

import navalgobattle.NavalgoBattle;
/**
 * Disparo. Clase abstracta.
 */
public abstract class Disparo {
	private int x;
	private int y;
	private int costo;
	private NavalgoBattle juego;

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
	}
}


/** Clase Convencional.
 * Extiende de Disparo. Este tipo de disparos son los que se hacen instantaneamente, es decir, antes de que se muevan las naves.
 */
public class Convencional extends Disparo {
}

/** Clase Mina.
 * Extiende de Disparo. Este tipo de disparos son los que se hacen con retardo, es decir, despues de que se muevan las naves.
 */
public class Mina extends Disparo {
}
