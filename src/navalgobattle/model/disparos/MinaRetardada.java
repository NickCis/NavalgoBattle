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
		this.radio = 0;
	}
	/*
	 * @return boolean Se efectuo el disparo?.
	 * 
	 */
	public boolean disparar() throws ExceptionDisparo{
		Logger.log(LogLevel.DEBUG, "Mina Retardad["+this.getPosicion().getX()+","+this.getPosicion().getY()+"]: turnos restantes: " + retardo);
		if (this.retardo-- <= 0){
			ArrayList<Posicion> posiciones = radioPosicion(posicion, this.radio);
			ArrayList<Nave> naves;
			if (posiciones.size() > 0){
				for(Posicion pos: posiciones){
					naves = juego.naveEnPosicion(pos);
					if (naves.size() > 0){ //Si encontro alguna nave la da~na
						for(Nave nave: naves){
							nave.danar(this,pos);
						}
					}
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
	
	/** Devuelve las posiciones que se encuentran a un radio de distancia de la mina.
	 * Como hay mas de una posicion devuelve una lista.
	 * Si radio = 0 (Mina submarina puntual con retardo) entonces en el array solo habra una posicion que es la de la mina
	 * Si radio = 1 (Mina submarina doble con retardo) tendre la posicion actual + las posiciones adyacentes en un radio de 1 casilla
	 * Si radio = 2 (Mina submarina triple con retardo) tendre la posicion actual + las posiciones adyacentes en un radio de 2 casillas
	 * @param int radio
	 * @param Posicion posicion: posicion de la mina y a partir de la cual se calcula las posiciones a un radio de distancia
	 * @return ArrayList<Posicion> posiciones a  un radio de distancia .
	 */
	protected ArrayList<Posicion> radioPosicion(Posicion posicion, int radio){
		ArrayList<Posicion> posiciones = new ArrayList<Posicion>();
		if(radio == 1)
			posiciones = posicionesRadioUno(posicion);	
		if(radio == 2)
			posiciones = posicionesRadioDos(posicion);
		posiciones.add(posicion);
		return posiciones;
	} 
	
	/** posicionValida, dice si la posicion es valida.
	 * @param Posicion posicion
	 * @return boolean: es valida la posicion?
	 */
	private boolean posicionValida(Posicion posicion){
		if(juego.getMaximaPosicion().isMenor(posicion) && posicion.isMenor(new Posicion(0,0)))
			return true;

		return false;
	}
	private ArrayList<Posicion> posicionesRadioUno(Posicion posicion){
		ArrayList<Posicion> posiciones = new ArrayList<Posicion>();
		Posicion pos = posicion.getSiguiente(1);
		if(posicionValida(pos))
			posiciones.add(pos);
		pos = posicion.getSiguiente(5);
		if(posicionValida(pos))
			posiciones.add(pos);
		pos = posicion.getSiguiente(4);
		if(posicionValida(pos))
			posiciones.add(pos);
		pos = posicion.getSiguiente(7);
		if(posicionValida(pos))
			posiciones.add(pos);
		pos = posicion.getSiguiente(11);
		if(posicionValida(pos))
			posiciones.add(pos);
		pos = posicion.getSiguiente(15);
		if(posicionValida(pos))
			posiciones.add(pos);
		pos = posicion.getSiguiente(14);
		if(posicionValida(pos))
			posiciones.add(pos);
		pos = posicion.getSiguiente(13);
		if(posicionValida(pos))
			posiciones.add(pos);
		return posiciones;
	}
	
	private ArrayList<Posicion> posicionesRadioDos(Posicion posicion){
		ArrayList<Posicion> posiciones = new ArrayList<Posicion>();
		Posicion pos;
		Posicion posActual;
		posiciones = posicionesRadioUno(posicion);
		pos = (posicion.getSiguiente(1)).getSiguiente(1);//casillero extremo derecho de distancia dos con la mina
		posActual = pos; //posActual tiene una referencia a la posicion extrema derecha de distancia dos con la mina
		if(posicionValida(pos)){
			posiciones.add(pos);
			pos = pos.getSiguiente(4);
			if(posicionValida(pos)){
				posiciones.add(pos);
				pos = pos.getSiguiente(4);
				if(posicionValida(pos))
					posiciones.add(pos);
			}
			pos = posActual.getSiguiente(14);
			if(posicionValida(pos)){
				posiciones.add(pos);
				pos= pos.getSiguiente(14);
				if(posicionValida(pos))
					posiciones.add(pos);
			}
		}
		pos = (posicion.getSiguiente(11)).getSiguiente(11);//casillero extremo izquierdo de distancia dos con la mina
		posActual = pos; //posActual tiene una referencia a la posicion extrema izquierda de distancia dos con la mina
		if(posicionValida(pos)){
			posiciones.add(pos);
			pos = pos.getSiguiente(4);
			if(posicionValida(pos)){
				posiciones.add(pos);
				pos = pos.getSiguiente(4);
				if(posicionValida(pos))
					posiciones.add(pos);
			}
			pos = posActual.getSiguiente(14);
			if(posicionValida(pos)){
				posiciones.add(pos);
				pos= pos.getSiguiente(14);
				if(posicionValida(pos))
					posiciones.add(pos);
			}
		}
		pos = (posicion.getSiguiente(4)).getSiguiente(4);//casillero extremo superior de distancia dos con la mina
		posActual = pos;
		pos = pos.getSiguiente(1);
		if(posicionValida(pos))
			posiciones.add(pos);
		pos = posActual.getSiguiente(11);
		if(posicionValida(pos))
			posiciones.add(pos);
		pos = (posicion.getSiguiente(14)).getSiguiente(14);//casillero extremo inferior de distancia dos con la mina
		posActual = pos;
		pos = pos.getSiguiente(1);
		if(posicionValida(pos))
			posiciones.add(pos);
		pos = posActual.getSiguiente(11);
		if(posicionValida(pos))
			posiciones.add(pos);
		return posiciones;
	}
	public int getRadio(){
		return radio;
	}
	/** Setea la Radio
	 * @param int r
	 */
	public void setRadio(int r){
		radio = r;
	}
	/** Setea la Costo
	 * @param int c
	 */
	public void setCosto(int c){
		costo = c;
	}

}
