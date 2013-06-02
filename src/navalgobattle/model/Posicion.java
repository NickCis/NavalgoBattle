package navalgobattle.model;

public class Posicion {
	private double x;
	private double y;

	public Posicion() {
	}

	public Posicion(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Posicion calcularPosicionSiguiente(double direccion) {
		Posicion resultado = new Posicion();
		resultado.setX(this.getX() + Math.cos(direccion));
		resultado.setY(this.getY() + Math.sin(direccion));
		return resultado;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		Posicion posicion = (Posicion) obj;
		return posicion.x == this.x && posicion.y == this.y;
	}

	@Override
	public String toString() {
		return x + ", " + y;
	}
}
