package punto;


public class Punto {
	private Double x;
	private Double y;

	
	public Punto(Double x,Double y) {
		super();
		this.x=x;
		this.y=y;
	}

	/**
	 * Devuelve el vector direccion entre 2 puntos.
	 * 
	 * @param punto
	 * @return Vector
	 */
	public Vector obtenerRectaEntrePuntos(Punto punto){
		double X=(punto.getX() - this.x);
		double Y=(punto.getY() - this.y);
		Vector vector = new Vector(X,Y);
		return vector;
	}
	
	public Punto mas(Vector otroPunto){
		Double x = otroPunto.getX() + this.getX();
		Double y = otroPunto.getY() + this.getY();
		return new Punto(x,y);
	}
	
	
	
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}

}
