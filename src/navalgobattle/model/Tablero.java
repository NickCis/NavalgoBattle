package navalgobattle.model;


/**
 * Clase que representa al tablero
 * 
 * @author Miguel Angel
 *
 */
public class Tablero {

private Posicion posicionMaxima;
	
	public Tablero(Posicion posicionMaxima) {
		super();
		this.posicionMaxima = posicionMaxima;
	}

	public boolean posicionValida(Posicion posicion) {
		return posicion.getX() >= 0 && posicion.getX() <= posicionMaxima.getX()
				&& posicion.getY() >= 0 && posicion.getY() <= posicionMaxima.getY();
	}
}
