package navalgobattle.model;

// Packages del sistema
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.reflect.Constructor;

import java.util.Hashtable;
import java.util.Enumeration;

// Packages nuestros
import navalgobattle.model.Disparo;
import navalgobattle.model.Posicion;
import navalgobattle.model.disparos.Convencional;
import navalgobattle.model.disparos.Mina;
import navalgobattle.model.disparos.MinaRetardada;
import navalgobattle.model.disparos.MinaContacto;
import navalgobattle.model.disparos.TipoDisparo;
import navalgobattle.model.Nave;
import navalgobattle.model.naves.TipoNave;
import navalgobattle.model.Jugador;

import navalgobattle.model.event.EventAgregarNave;

import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;

import navalgobattle.util.config.Config;

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
	protected EventAgregarNave eventAgregarNave = null;

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

	public int getPuntos(){
		return this.jugador.getPuntos();
	}

	/** Termino el juego?.
	 * La razon por que termino si se acabaron los puntos o mataron las naves se puede decidir obteniendo la lista de naves y puntos
	 * @return boolean: devuelve true si el juego ya termino.
	 */
	public boolean terminoJuego(){
		return (this.getPuntos() <= 0 || this.navesList.size() == 0);
	}

	/** Gano el juego?.
	 * La razon por que termino si se acabaron los puntos o mataron las naves se puede decidir obteniendo la lista de naves y puntos
	 * Pre condicion: el juego debio terminar
	 * @return boolean: devuelve true si el juego lo gano.
	 */
	public boolean ganoJuego(){
		return (this.navesList.size() == 0);
	}


	public int getTurno(){
		return this.turno;
	}

	public void setPuntosPorTurno(int puntosPorTurno){
		this.puntosPorTurno = -puntosPorTurno;
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
	 * @param TipoDisparo tipodisparo: disparo a realizarse
	 * @param Posicion posicion: posicion
	 */
	public Disparo doDisparar(TipoDisparo tipo, Posicion posicion){
		Disparo disparo = null;
		switch(tipo){
			case CONVENCIONAL:
				disparo = new Convencional(this, posicion);
				disparo.setCosto(200);
				this.disparar((Convencional) disparo);
				break;
			case MINA_SIMPLE:
				disparo = new MinaRetardada(this, posicion);
				disparo.setCosto(50);
				((MinaRetardada) disparo).setRetardo(3);
				this.disparar((Mina) disparo);
				break;
			case MINA_DOBLE:
				disparo = new MinaRetardada(this, posicion);
				disparo.setCosto(100);
				((MinaRetardada) disparo).setRetardo(3);
				((MinaRetardada) disparo).setRadio(1);
				this.disparar((MinaRetardada) disparo);
				break;
			case MINA_TRIPLE:
				disparo = new MinaRetardada(this, posicion);
				disparo.setCosto(125);
				((MinaRetardada) disparo).setRetardo(3);
				((MinaRetardada) disparo).setRadio(2);
				this.disparar((MinaRetardada) disparo);
				break;
			case MINA_CONTACTO:
				disparo = new MinaContacto(this, posicion);
				disparo.setCosto(150);
				this.disparar((MinaContacto) disparo);
				break;
		}
		return disparo;
	}
	/** Metodo usado para agregar un disparo.
	 * Este metodo deberia ser llamado por el controller, cuando el view le dice que se hizo un disparo.
	 * Debe guardar el disparo en la lista correspondiente, restarle puntos al jugador.
	 * @param Disparo disparo: disparo a realizarse
	 */
	private void disparar(Disparo disparo){
		Logger.log(LogLevel.DEBUG, "Costo de disparo: "+disparo.getCosto());
		this.jugador.addPuntos(- (disparo.getCosto() ) );
	}
	private void disparar(Convencional disparo){
		Logger.log(LogLevel.DEBUG, "Se agrega un convencional");
		this.disparar( (Disparo) disparo);
		this.convencionalList.add(disparo);
	}
	private void disparar(Mina disparo){
		Logger.log(LogLevel.DEBUG, "Se agrega una mina");
		this.disparar( (Disparo) disparo);
		this.minasList.add(disparo);
	}

	/** Metodo usado para agregar una nave.
	 * Debe guardar la nave en la lista.
	 * @param Nave nave: nave a agregarse
	 */
	public void addNave(Nave nave){
		this.navesList.add(nave);
		if(this.eventAgregarNave != null)
			this.eventAgregarNave.agregarNave(nave, nave.getTipo());
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

	/** Agrega las naves del juego en posiciones random.
	 */
	public void agregarNavesRandom(){
		Hashtable<TipoNave, Integer> navesDefault = (Hashtable<TipoNave, Integer>) Config.getObject("navesDefault");
		Enumeration<TipoNave> e = navesDefault.keys();

		while(e.hasMoreElements()){
			TipoNave tipo = e.nextElement();
			int number = navesDefault.get(tipo);
			while((number--)>0){
				Constructor cons = null;
				try {
					switch(tipo){
						case LANCHA:
							cons = navalgobattle.model.naves.Lancha.class.getDeclaredConstructor(Posicion.class, Posicion.class, int.class);
							break;
						case DESTRUCTOR:
							cons = navalgobattle.model.naves.Destructor.class.getDeclaredConstructor(Posicion.class, Posicion.class, int.class);
							break;
						case PORTAAVIONES:
							cons = navalgobattle.model.naves.PortaAviones.class.getDeclaredConstructor(Posicion.class, Posicion.class, int.class);
							break;
						case ROMPEHIELOS:
							cons = navalgobattle.model.naves.RompeHielos.class.getDeclaredConstructor(Posicion.class, Posicion.class, int.class);
							break;
						case BUQUE:
							cons = navalgobattle.model.naves.Buque.class.getDeclaredConstructor(Posicion.class, Posicion.class, int.class);
							break;
					}
				}catch (Exception expt){
					Logger.log(LogLevel.ERROR, "Excepcion obteniendo el constructor");
				}

				if(cons != null)
					this.agregarNaveRandom(cons);
			}
		}
	}

	protected void agregarNaveRandom(Constructor cons){
		try {
			Posicion randomPos = this.randomPosicion();
			int randomDir = this.randomDireccion();
			Nave modelNave = (Nave) cons.newInstance(this.getMaximaPosicion(), randomPos, randomDir);
			this.addNave(modelNave);
		}catch(Exception e){
			Logger.log(LogLevel.WARN, "Error agregando nave. Se vuelve a intentar.");
			this.agregarNaveRandom(cons);
		}
	}

	/** Devuelve una posicion random.
	 * Tiene en cuenta los limites de maxPos
	 * @return Posicion;
	 */
	protected Posicion randomPosicion(){
		int x = (int) Math.round(Math.random() * this.getMaximaPosicion().getX());
		int y = (int) Math.round(Math.random() * this.getMaximaPosicion().getY());

		return new Posicion(x, y);
	}

	/** Devuelve una direccion Random.
	 */
	protected int randomDireccion(){
		int dir = (int) Math.round(Math.random() * 15);//La posicion maxima es 1111 -> 15
		if ((dir & 1) == 0 && (dir & 4) == 0 )
			return this.randomDireccion();
		return dir;
	}

	/** Setea el evento de Agregar nave
	 * @param EventAgregarNave event: evento
	 */
	public void addAgregarNaveListener(EventAgregarNave event){
		this.eventAgregarNave = event;
	}

}
