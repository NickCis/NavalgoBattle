package navalgobattle.model.disparos;

import java.util.ArrayList;
import navalgobattle.model.Disparo;
import navalgobattle.model.Nave;
import navalgobattle.model.disparos.Mina;
import navalgobattle.model.exceptions.ExceptionDisparo;
/** Clase MinaContacto.
 * Extiende de Mina. Este tipo de disparo es el que se efectua cuando la nave se mueve a la posicion de la mina, 
 * (se tocan en la misma posicion).
 */         
 public class MinaContacto extends Mina {
    
        public MinaContacto(int x, int y){
                super(x,y);
        }
        /*
         * @return boolean Se efectuo el disparo?.
	*/

        public boolean disparar() throws ExceptionDisparo{

		ArrayList<Nave> naves = juego.naveEnPosicion(x,y);
                if (naves.size() > 0){
                    for(Nave nave: naves){

                         nave.danar(this,x,y);

                    }
                    return true;
                }
             return false;
	}

}
