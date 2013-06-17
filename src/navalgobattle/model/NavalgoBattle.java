package navalgobattle.model;

// Packages del sistema
import java.util.ArrayList;
import java.util.Iterator;

// Packages nuestros
import navalgobattle.model.Disparo;
import navalgobattle.model.Posicion;
import navalgobattle.model.disparos.Convencional;
import navalgobattle.model.disparos.Mina;
import navalgobattle.model.Nave;
import navalgobattle.model.Jugador;
/**
 * Clase principal del juego.
 * 
 * 
 * @author todos vamos a meter mano
 */

public class NavalgoBattle {

	protected Jugador jugador;
	protected int turno;
	protected int puntosPorTurno;
	protected Posicion maxPos;
	protected ArrayList<Nave> navesList;
	protected ArrayList<Mina> minasList;
	protected ArrayList<Convencional> convencionalList;

	protected NavalgoBattle(){
		this.navesList = new ArrayList<Nave>();
		this.minasList = new ArrayList<Mina>();
		this.convencionalList = new ArrayList<Convencional>();
		this.turno = 0;
		this.puntosPorTurno = -10;
	}

	public NavalgoBattle(Posicion maxPos, Jugador jugador){
		this();
		this.setJugador(jugador);
		this.setMaximaPosicion(maxPos);
	}

	public void setPuntosPorTurno(int puntosPorTurno){
		this.puntosPorTurno = puntosPorTurno;
	}
	public void setJugador(Jugador jugador){
		this.jugador = jugador;
	}

	public void setMaximaPosicion(Posicion maxPos){
		this.maxPos = maxPos;
	}

	public Posicion getMaximaPosicion(){
		return this.maxPos;
	}

	/** Devuelve las nave en la posicion x y.
	 * Como puede haber mas de una nave devuelve una lista. Si no hay, devuelve lista vacia.
	 * @param int x
	 * @param int y
	 * @return ArrayList<Nave> Naves en la posicion.
	 */
	public ArrayList<Nave> naveEnPosicion(Posicion posicion){
		ArrayList<Nave> naves = new ArrayList<Nave>();
		for(Nave nave: this.navesList){
			if(nave.estoyEnPosicion(posicion))
				naves.add(nave);
		}

		return naves;
	}

	/** Metodo usado para agregar un disparo.
	 * Este metodo deberia ser llamado por el controller, cuando el view le dice que se hizo un disparo.
	 * Debe guardar el disparo en la lista correspondiente, restarle puntos al jugador.
	 * @param Disparo disparo: disparo a realizarse
	 */
	public void disparar(Disparo disparo){
		this.jugador.addPuntos(- (disparo.getCosto() ) );
	}
	public void disparar(Convencional disparo){
		this.disparar( (Disparo) disparo);
		this.convencionalList.add(disparo);
	}
	public void disparar(Mina disparo){
		this.disparar( (Disparo) disparo);
		this.minasList.add(disparo);
	}

	/** Metodo usado para agregar una nave.
	 * Debe guardar la nave en la lista.
	 * @param Nave nave: nave a agregarse
	 */
	public void addNave(Nave nave){
		this.navesList.add(nave);
	}

	/** Metodo que se llama para pasar de turno.
	 *  1) Itera en los disparos convencionales.
	 *  2) Mueve naves.
	 *  3) Itera en las minas.
	 *  4) Limpia lista de naves.
	 *  4) Resta puntos de turno al jugador.
	 */
	public void siguienteTurno() throws Exception{
		this.iterarDisparos(this.convencionalList);

		for(Nave nave: this.navesList)
			nave.mover();

		this.iterarDisparos(this.minasList);

		//Remuevo naves que no esten vivas
		Iterator<Nave> it = this.navesList.iterator();
		while (it.hasNext()){
			Nave n = it.next();
			if(! n.estaViva())
				it.remove();
		}

		this.turno++;
		this.jugador.addPuntos(this.puntosPorTurno);
	}

	protected void iterarDisparos(ArrayList list) throws Exception{
		Iterator<Disparo> it = list.iterator();
		while (it.hasNext()){
			Disparo c = it.next();
			if(c.disparar())
				it.remove();
		}
	}

}
