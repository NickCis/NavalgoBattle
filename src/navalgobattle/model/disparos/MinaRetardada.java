package navalgobattle.model.disparos;

import java.util.ArrayList;
import navalgobattle.model.disparos.Mina;
import navalgobattle.model.Nave;
import navalgobattle.model.exceptions.ExceptionDisparo;
import navalgobattle.model.Disparo;
import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Posicion;


/** Clase MinaRetardada.
 * Extiende de Mina. Este tipo de disparo es el que se efectua cuando la nave se mueve a la posicion de la mina, 
 * (se tocan en la misma posicion).
 */         
public class MinaRetardada extends Mina {
	protected int retardo;
	protected int radio;

	public MinaRetardada(NavalgoBattle nb, Posicion pos){
		super(nb, pos);
	}
	/*
	 * @return boolean Se efectuo el disparo?.
	 * 
	 */
	public boolean disparar() throws ExceptionDisparo{

		int momentoDeDisparo = retardo - 1; // Va descontando 1 del retardo hasta llegar a 0
		this.setRetardo(momentoDeDisparo); // Guarda el nuevo retardo ya descontado
		if (momentoDeDisparo == 0){ 
			ArrayList<Nave> naves = juego.naveEnPosicion(this.posicion);
			if (naves.size() > 0){ //Si encontro alguna nave la da~na
				for(Nave nave: naves){

					nave.danar(this,this.posicion);

				}  
			}
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
