package navalgobattle.model;

import java.util.ArrayList;
import java.util.Iterator;
import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Nave;
import navalgobattle.model.Posicion;
import navalgobattle.model.exceptions.ExceptionDisparo;
/**
 * Disparo. Clase abstracta.
 * 
 * @author todos vamos a meter mano
 */
public abstract class Disparo {
	protected Posicion posicion;
	protected int costo;
	protected NavalgoBattle juego;

	public Disparo(NavalgoBattle nb, Posicion posicion){
		this.posicion = posicion;
		this.juego = nb;  
	}

	/** Efectua un disparo.
	 * 1) Controla si efectivamente tiene que disparar.
	 * 2) Si tiene qe disparar, pide al juego las naves en las posiciones que tiene que atacar (NavalgoBattle.naveEnPosicion).
	 * 3) Llama a metodo danar de la nave, pasandose a si mismo.
	 * 4) Si es que disparo, devuelve true. Si no disparo, devuelve false.
	 * @return boolean Se efectuo el disparo?.
	 */
	public abstract boolean disparar() throws ExceptionDisparo;

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

