package navalgobattle.model;
/**
 * Jugador.
 * 
 * Esta clase lo unico que hace es guardar los puntos. Tiene sentido su existencia?
 */
public class Jugador {
	private int puntos;


	public Jugador(){
		this.puntos = 0;
	}

	public Jugador(int puntos){
		this.puntos = puntos;
	}
	public void addPuntos(int puntos){
		this.puntos += puntos;
	}

	public int getPuntos(){
		return this.puntos;
	}
}
