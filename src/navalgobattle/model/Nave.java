package navalgobattle.model;

import java.util.ArrayList;
import java.util.Iterator;

import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Posicion;
import navalgobattle.model.ParteNave;
import navalgobattle.model.Disparo;
import navalgobattle.model.exceptions.ExceptionNave;
import navalgobattle.model.exceptions.PosicionInvalida;
/**
 * Nave.
 * 
 * 
 * @author todos vamos a meter mano
 */
public class Nave {
	protected int direccion; //-> Guarda en que direccion se mueve
	protected ArrayList<ParteNave> partes; //-> Lista de partes de nave
	protected Posicion maxPos;

	public Nave(Posicion maxPos, Posicion pos, int direccion) throws ExceptionNave{
		this.maxPos = maxPos;
		this.partes = new ArrayList<ParteNave>();
		this.setDireccion(direccion);
		this.setPosicion(pos);
	}

	/** Setea la posicion.
	 * @param int x: posicion x
	 * @param int y: posicion y
	 */

	public void setPosicion(Posicion posicion) throws PosicionInvalida{
		int size = this.size();
		Posicion newPos = new Posicion(posicion);
		while((size--) > 0){
			if(! this.maxPos.isMenor(newPos))
				throw new PosicionInvalida(newPos);

			ParteNave parte = new ParteNave(newPos, this.vidaPorPos());
			this.partes.add(parte);

			newPos = newPos.getSiguiente(this.direccion);
		}
	}

	protected int vidaPorPos(){
		return 1;
	}
	protected int size(){
		return 1;
	}

	public int getSize(){
		return this.partes.size();
	}

	/** Setea la direccion a cual se movera.
	 * @param int dir, se va a usar como flags se usaran los ultimos 4 bytes, 
	 * 1 1 1 1
	 * | | | |_ Indicara si se mueve en x ( & 1)
	 * | | |___ Indica signo de x, 1 menos, 0 mas ( & 2 )
	 * | |_____ Indicia si se mueve en y ( & 4)
	 * |_______ Indica signo de y, 1 menos 0 mas. ( & 8 )
	 */
	public void setDireccion(int dir){
		this.direccion = dir;
	}
	/** Mueve la nave.
	 * 1) Efectua movimiento de la nave, teniendo en cuenta si rebota o no contra los costados
	 * 2) Actualiza las posiciones de su arrayList.
	 */
	
	public void mover(){
		if(!this.puedoMover())
			this.espejarDireccion();

		for(ParteNave parte : this.partes){
			parte.mover(this.direccion);
		}
	}

	public boolean estoyEnPosicion(Posicion posicion){
		for(ParteNave parte : this.partes){
			if(parte.estoyEnPosicion(posicion))
				return true;
		}

		return false;
	}

	/** Dice si se puede mover a la siguiente posicion.
	 * @return verdadero si puede, falso si no.
	 */
	protected boolean puedoMover(){
		for(ParteNave parte : this.partes){
			if(!parte.puedoMover(this.maxPos, this.direccion))
				return false;
		}
		return true;
	}

	/** Espeja direccion de movimiento.
	 * Basicamente lo que hace es alternar los dos flags de signo de la direccion
	 */
	protected void espejarDireccion(){
		this.direccion ^= 10;
	}

	/** Esta viva.
	 * @return boolean devuelve si la nave esta viva.
	 */
	public boolean estaViva(){
		boolean estaViva = false;
		for(ParteNave parte : this.partes){
			if(parte.getVida() > 0)
				estaViva = true;
		}

		return estaViva;
	}

	/** Danar a una nave.
	 * @param Disparo disparo, instancia de disparo que quiere danar a la nave.
	 */
	public void danar(Disparo disparo, Posicion posicion){
		Iterator<ParteNave> it = this.partes.iterator();
		while (it.hasNext()){
			ParteNave parte = it.next();
			if(parte.estoyEnPosicion(posicion)){
				int vida = parte.getVida();
				vida -= 1;
				if(vida == 0)
					it.remove();
				else
					parte.setVida(vida);
			}
		}
	}
}
