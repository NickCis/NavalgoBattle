package navalgobattle.model;

/** Borrar este meotodos de vida, se pasan a otro lado*/
public class Posicion {
	private int x;
	private int y;
	private int vida;
	/** Crea una posicion.
	 * @param int x: coordenada en x.
	 * @param int y: coordenada en y.
	 */
	public Posicion(int x, int y, int vida){
		this.x = x;
		this.y = y;
		this.vida = vida;
	}

	public int getX(){
		return this.x;
	}
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getY(){
		return this.y;
	}

	public int getVida(){
		return this.vida;
        }

	public int setVida(int vida){
		this.vida = vida;
		return this.vida;
	}

	/** isMenor. Dice si el objeto que se le pasa de parametro es menor que este.
	 * Menor siginifica que las coordenadas x e y, del objeto que se le pasa sean (ambas) menores o iguales a las de si mismo.
	 * @param Posicion posicion.
	 * @return boolean es menor?
	 */
	public boolean isMenor(Posicion posicion){
		return true;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(obj.getClass() != getClass()) return false;

		Posicion pos = (Posicion)obj;
		return pos.getX() == this.getX() && pos.getY() == this.getY();
	}
}
