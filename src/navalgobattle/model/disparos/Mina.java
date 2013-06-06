package navalgobattle.model.disparos;

import navalgobattle.model.Disparo;
/** Clase Mina.
 * Extiende de Disparo. Este tipo de disparos son los que se hacen con retardo, es decir, despues de que se muevan las naves.
 */
public class Mina extends Disparo {
	public Mina(int x, int y){
		super(x, y);
	}
}
