package navalgobattle.model.disparos;

import navalgobattle.model.Disparo;

/** Clase Convencional.
 * Extiende de Disparo. Este tipo de disparos son los que se hacen instantaneamente, es decir, antes de que se muevan las naves.
 */
public class Convencional extends Disparo {
	public Convencional(int x, int y){
		super(x, y);
	}
}
