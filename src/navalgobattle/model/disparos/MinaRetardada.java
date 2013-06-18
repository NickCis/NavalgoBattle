package navalgobattle.model.disparos;

import java.util.ArrayList;
import navalgobattle.model.disparos.Mina;
import navalgobattle.model.Nave;
import navalgobattle.model.exceptions.ExceptionDisparo;
import navalgobattle.model.Disparo;
import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Posicion;


import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;


/** Clase MinaRetardada.
 * Extiende de Mina. Este tipo de disparo es el que se efectua cuando la nave se mueve a la posicion de la mina, 
 * (se tocan en la misma posicion).
 */         
public class MinaRetardada extends Mina {
	protected int retardo;
	protected int radio;

	public MinaRetardada(NavalgoBattle nb, Posicion pos){
		super(nb, pos);
		this.disparo = false;
	}
	/*
	 * @return boolean Se efectuo el disparo?.
	 * 
	 */
	public boolean disparar() throws ExceptionDisparo{
		Logger.log(LogLevel.DEBUG, "Mina Retardad["+this.getPosicion().getX()+","+this.getPosicion().getY()+"]: turnos restantes: " + retardo);
		if (this.retardo-- <= 0){
			ArrayList<Nave> naves = juego.naveEnPosicion(this.posicion);
			if (naves.size() > 0){ //Si encontro alguna nave la da~na
				for(Nave nave: naves){
					nave.danar(this,this.posicion);
				}
			}
			Logger.log(LogLevel.DEBUG, "-->Mina Retardad: BUM! retardo: " + retardo);
			this.disparo = true;
			return true;
		}
		return false;
	}

	public void setRetardo(int turnos){
		retardo = turnos;
	}
	public int getRetardo(){
		return retardo;
	}
	public int getRadio(){
		return radio;
	}
	/** Setea la Radio
	 * @param int r
	 */
	public void setRadio(int r) throws ExceptionDisparo{
		radio = r;
	}
	/** Setea la Costo
	 * @param int c
	 */
	public void setCosto(int c){
		costo = c;
	}

}
