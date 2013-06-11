package navalgobattle.model.disparos;

import java.util.ArrayList;
import navalgobattle.model.Disparo;
import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Posicion;
import navalgobattle.model.Nave;
/** Clase Convencional.
 * Extiende de Disparo. Este tipo de disparos son los que se hacen instantaneamente, es decir, antes de que se muevan las naves.
 */
public class Convencional extends Disparo {
	public Convencional(NavalgoBattle nb, Posicion posicion){
		super(nb, posicion);
	}
	/*
	 * @return boolean Se efectuo el disparo?.
	 */
	public boolean disparar(){
		ArrayList<Nave> naves = juego.naveEnPosicion(posicion);
		if (naves.size() > 0){
			for(Nave nave: naves){
				nave.danar(this,posicion);
			}
		}
		return true;
	}

}
