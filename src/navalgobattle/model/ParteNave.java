package navalgobattle.model;

import navalgobattle.model.Posicion;

public class ParteNave {
	protected Posicion posicion;
	private int vida;

	public ParteNave(Posicion posicion, int vida){
		this.setPosicion(posicion);
		this.setVida(vida);
	}
	public Posicion getPosicion(){
		return this.posicion;
	}
	public void setPosicion(Posicion posicion){
		this.posicion = posicion;
	}

	public int getVida(){
		return this.vida;
	}

	public int setVida(int vida){
		this.vida = vida;
		return this.vida;
	}

	/** puedoMover, dice si el movimiento es la direccion especificada es valida.
	 * Ahora la logica esta la esta haciendo la nave. Pasarla aca.
	 * @param Posicion posicionMax: maxima posicion, utilizar el metodo isMenor, para reconocer si la posicion nueva de la parte de la nave es menor (es valida) que la maxima.
	 * @param int direccion: Direccion en la que se efectua el movimiento, son flags, ver Nave para mayor explicacion.
	 * @return boolean: es valido el movimiento?
	 */
	public boolean puedoMover(Posicion posicionMax, int direccion){
		return true;
	}


	/** mover, mueve la parte de nave a la siguiente posicion especificada.
	 * @param int direccion: Direccion en la que se efectua el movimiento, son flags, ver Nave para mayor explicacion.
	 */
	public void mover(int direccion){
	}

}
