package navalgobattle.model.disparos;

import java.util.ArrayList;
import navalgobattle.model.Disparo;
import navalgobattle.model.Nave;
/** Clase Convencional.
 * Extiende de Disparo. Este tipo de disparos son los que se hacen instantaneamente, es decir, antes de que se muevan las naves.
 */
public class Convencional extends Disparo {
	public Convencional(int x, int y){
		super(x, y);
	}
        /*
         * @return boolean Se efectuo el disparo?.
	*/
        public boolean disparar(){
                
                ArrayList<Nave> naves = juego.naveEnPosicion(x,y);
                if (naves.size() > 0){
                    for(Nave nave: naves){

                         nave.danar(this,x,y);

                    }
                }
             return true;
	}
        
}
