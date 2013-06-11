package navalgobattle.model;

/** Borrar este meotodos de vida, se pasan a otro lado*/
public class Posicion {
	private int x;
	private int y;
	/** Crea una posicion.
	 * @param int x: coordenada en x.
	 * @param int y: coordenada en y.
	 */
	public Posicion(int x, int y){
		this.setXY(x, y);
	}
	public Posicion(Posicion pos){
		this.setXY(pos);
	}

	public int getX(){
		return this.x;
	}

	public void setXY(Posicion posicion){
		this.x = posicion.getX();
		this.y = posicion.getY();
	}

	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getY(){
		return this.y;
	}

	/**La posicion cambia su valor a la siguiente.
	 * @param int direccion: direccion de la siguuiente posicion.
	 */
	public void setSiguiente(int direccion){
		this.setXY(this.getSiguiente(direccion));
	}

	/** Devuelve la posicion siguiente en la direccion pasada.
	 * @param int direccion: direccion de la siguiente posicion.
	 */
	public Posicion getSiguiente(int direccion){
		int x = this.getX();
		int y = this.getY();
		if((direccion & 1) != 0)
			x += ( ((direccion & 2) != 0)? -1: 1 );
		if((direccion & 4) != 0)
			y += ( ((direccion & 8) != 0)? -1: 1 );
		return new Posicion(x, y);
	}

	/** isMenor. Dice si el objeto que se le pasa de parametro es menor que este.
	 * Menor siginifica que las coordenadas x e y, del objeto que se le pasa sean (ambas) menores o iguales a las de si mismo.
	 * @param Posicion posicion.
	 * @return boolean es menor?
	 */
	public boolean isMenor(Posicion posicion){
		if(posicion.getX() <= this.getX() && posicion.getY() <= this.getY())
			return true;
		return false;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj.getClass() != getClass()) return false;

		Posicion pos = (Posicion)obj;
		return pos.getX() == this.getX() && pos.getY() == this.getY();
	}
}
