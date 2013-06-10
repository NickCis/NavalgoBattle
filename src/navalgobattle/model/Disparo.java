package navalgobattle.model;

import java.util.ArrayList;
import java.util.Iterator;
import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Nave;
import navalgobattle.model.exceptions.ExceptionDisparo;
/**
 * Disparo. Clase abstracta.
 * 
 * @author todos vamos a meter mano
 */
public abstract class Disparo {
	protected int x;
	protected int y;
	protected int costo;
	protected NavalgoBattle juego;

	public Disparo(int x, int y){
            this.x = x;
            this.y = y;
            this.juego = new NavalgoBattle();  
	}
	/** Efectua un disparo.
	 * 1) Controla si efectivamente tiene que disparar.
	 * 2) Si tiene qe disparar, pide al juego las naves en las posiciones que tiene que atacar (NavalgoBattle.naveEnPosicion).
	 * 3) Llama a metodo danar de la nave, pasandose a si mismo.
	 * 4) Si es que disparo, devuelve true. Si no disparo, devuelve false.
	 * @return boolean Se efectuo el disparo?.
	 */
	public boolean disparar() throws ExceptionDisparo{
            
		return true;
	}

	/** Devuelve el costo.
	 */
	public int getCosto(){
		return costo;
	}
        /*
         * Setea el costo.
         */
        public void setCosto(int c){
		costo = c;
	}

}

