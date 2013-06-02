package navalgobattle.model;
import excepciones.PosicionInvalidaException;
/**
 * Nave.
 * 
 * 
 * @author Miguel Angel
 */
public abstract class Nave {
	
	protected Posicion posicion;
	protected float direccion;
	protected int tam;
	protected Tablero tablero;


	public Nave(int xMax, int yMax){
	}

	/** Mueve la nave.
	 * 1) Efectua movimiento de la nave, teniendo en cuenta si rebota o no contra los costados
	 * 2) Actualiza las posiciones de su arrayList.
	 */
	public void mover() throws PosicionInvalidaException{
		Posicion posicionNueva = this.posicion.calcularPosicionSiguiente(this.direccion);
		if (this.tablero.posicionValida(posicionNueva)) {
			this.posicion = posicionNueva;
		} else {
			throw new PosicionInvalidaException();
		}
	}

	/** Esta viva.
	 * @return boolean devuelve si la nave esta viva.
	 */
	//public boolean estaViva(){
	//}

	/** Danar a una nave.
	 * @param Disparo disparo, instancia de disparo que quiere danar a la nave.
	 */
	public void danar(Disparo disparo){
	}
}



