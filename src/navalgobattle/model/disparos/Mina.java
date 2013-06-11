package navalgobattle.model.disparos;

import java.util.ArrayList;
import navalgobattle.model.Disparo;
import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Posicion;
import navalgobattle.model.Nave;
import navalgobattle.model.exceptions.ExceptionDisparo;
/** Clase Mina.
 * Extiende de Disparo. Este tipo de disparos son los que se hacen con retardo, es decir, despues de que se muevan las naves.
 */
public class Mina extends Disparo {
	public Mina(NavalgoBattle nb, Posicion pos){
		super(nb, pos);
	}

	public boolean disparar() throws ExceptionDisparo{
		ArrayList<Nave> naves = juego.naveEnPosicion(posicion);
		if (naves.size() > 0){
			for(Nave nave: naves){
				nave.danar(this,posicion);
			}
		}
		return true;
	}

}

