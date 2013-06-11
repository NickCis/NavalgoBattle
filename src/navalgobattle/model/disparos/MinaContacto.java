package navalgobattle.model.disparos;

import java.util.ArrayList;
import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Posicion;
import navalgobattle.model.Disparo;
import navalgobattle.model.Nave;
import navalgobattle.model.disparos.Mina;
import navalgobattle.model.exceptions.ExceptionDisparo;

/** Clase MinaContacto.
 * Extiende de Mina. Este tipo de disparo es el que se efectua cuando la nave se mueve a la posicion de la mina, 
 * (se tocan en la misma posicion).
 */         
public class MinaContacto extends Mina {

	public MinaContacto(NavalgoBattle nb, Posicion pos){
		super(nb, pos);
	}
	/*
	 * @return boolean Se efectuo el disparo?.
	 */

	public boolean disparar() throws ExceptionDisparo{
		ArrayList<Nave> naves = juego.naveEnPosicion(this.posicion);
		if (naves.size() > 0){
			for(Nave nave: naves){
				nave.danar(this,this.posicion);
			}
			return true;
		}
		return false;
	}

}
