package navalgobattle.model;

// Packages del sistema
import java.util.ArrayList;

// Packages nuestros
import navalgobattle.model.Disparo;
import navalgobattle.model.Convencional;
import navalgobattle.model.Mina;
import navalgobattle.model.Nave;
import navalgobattle.model.Jugador;
/**
 * Clase principal del juego.
 * 
 * 
 * @author todos vamos a meter mano
 */

public class NavalgoBattle {

	private Jugador jugador;
	private int turno;
	private ArrayList<Nave> navesList;
	private ArrayList<Mina> minasList;
	private ArrayList<Convencional> convencionalList;

	public NavalgoBattle(){
	}

	/** Devuelve las nave en la posicion x y.
	 * Como puede haber mas de una nave devuelve una lista. Si no hay, devuelve lista vacia.
	 * @param int x
	 * @param int y
	 * @return ArrayList<nave> Naves en la posicion.
	 */
	public ArrayList<Nave> naveEnPosicion(int x, int y){
		ArrayList<Nave> naves = new ArrayList<Nave>();
		return naves;
	}

	/** Metodo usado para agregar un disparo.
	 * Este metodo deberia ser llamado por el controller, cuando el view le dice que se hizo un disparo.
	 * Debe guardar el disparo en la lista correspondiente, restarle puntos al jugador.
	 * @param Disparo disparo: disparo a realizarse
	 */
	public void disparar(Convencional disparo){
	}
	public void disparar(Mina disparo){
	}

	/** Metodo usado para agregar una nave.
	 * Debe guardar la nave en la lista.
	 * @param Nave nave: nave a agregarse
	 */
	public void addNave(Nave nave){
	}

	/** Metodo que se llama para pasar de turno.
	 *  1) Itera en los disparos convencionales.
	 *  2) Mueve naves.
	 *  3) Itera en las minas.
	 *  4) Limpia lista de naves.
	 *  4) Resta puntos de turno al jugador.
	 */
	public void siguienteTurno(){
	}
}
