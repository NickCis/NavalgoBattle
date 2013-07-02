package navalgobattle.controller;

import java.util.ArrayList;
import java.lang.Math;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

import navalgobattle.model.Jugador;
import navalgobattle.model.Posicion;
import navalgobattle.model.NavalgoBattle;
//import navalgobattle.model.Nave;
import navalgobattle.model.naves.Lancha;
//import navalgobattle.model.Disparo;
import navalgobattle.model.disparos.Convencional;
import navalgobattle.model.disparos.Mina;
import navalgobattle.model.disparos.MinaRetardada;
import navalgobattle.model.disparos.MinaContacto;

import fiuba.algo3.titiritero.modelo.GameLoop;

//import navalgobattle.view.Nave;
//import navalgobattle.controller.Nave;

//import navalgobattle.controller.Disparo;

import navalgobattle.controller.TipoDisparo;
import navalgobattle.controller.event.EventJuegoTerminado;
import navalgobattle.controller.event.EventJuegoSiguienteTurno;

import navalgobattle.util.config.Config;
import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;

import navalgobattle.model.event.EventAgregarNave;


/** Controlador - Juego.
 * Se encarga de recibir acciones del view y efectuarlas usando al model como backend.
 * Pre: Config y Logger han sido inicializados.
 */
public class Juego {
	protected NavalgoBattle juego;
	protected int width;
	protected int height;
	protected GameLoop gameLoop;
	protected EventJuegoTerminado eventJuegoTerminado = null;
	protected EventJuegoSiguienteTurno eventJuegoSiguienteTurno = null;

	/** Constructor.
	 * @param int width: ancho en pixel del panel.
	 * @param int height: alto en pixel del panel.
	 * @param GameLoop gameLoop: gameLoop del juego..
	 */
	public Juego(int width, int height, GameLoop gameLoop){
		this.width = width;
		this.height = height;
		this.gameLoop = gameLoop;
		//TODO: Sacar jugador de aca, tiene qe levantarlo de algun lado.
		this.juego = new NavalgoBattle(new Posicion((Posicion) Config.getObject("maxPos")), new Jugador((Integer) Config.getObject("puntosPorDefecto")));

		final Juego that = this;
		this.juego.addAgregarNaveListener(new EventAgregarNave(){
			public void agregarNave(navalgobattle.model.Nave modelNave, navalgobattle.model.naves.TipoNave tipo){
				navalgobattle.controller.Nave controllerNave = new navalgobattle.controller.Nave(modelNave, that.gameLoop, that.squareWidth(), that.squareHeight(), tipo);
			}
		});

		//agregar disparo
	}

	public void agregarNavesRandom(){
		this.juego.agregarNavesRandom();
	}

	/** Devuelve los puntos.
	 */
	public int getPuntos(){
		return this.juego.getPuntos();
	}

	/** Devulve el turno */
	public int getTurno(){
		return this.juego.getTurno();
	}

	/** Devuelve el ancho en pixel de un cuadrado (una posicion).
	 */
	protected int squareWidth(){
		return (int) Math.round(this.width / (this.juego.getMaximaPosicion().getX()+1));
	}
	/** Devuelve el alto en pixel de un cuadrado (una posicion).
	 */
	protected int squareHeight(){
		return (int) Math.round(this.height / (this.juego.getMaximaPosicion().getY()+1));
	}
	/** Convierte pixeles del eje x en posiciones.
	 */
	protected int x2pos(int x){
		return (int) Math.round(x * (this.juego.getMaximaPosicion().getX()+1)/ this.width);
	}
	/** Convierte posiciones en pixeles del eje x (devuelve el pixel en el medio del cuadrado).
	 */
	protected int pos2x(int xPos){
		return (int) Math.round(xPos * this.width / (this.juego.getMaximaPosicion().getX()+1) +  this.squareWidth()/2);
	}

	/** Convierte pixeles del eje y en posiciones.
	 */
	protected int y2pos(int y){
		return (int) Math.round(y * (this.juego.getMaximaPosicion().getY()+1)/ this.height);
	}

	/** Convierte posiciones en pixeles del eje y (devuelve el pixel en el medio del cuadrado).
	 */
	protected int pos2y(int yPos){
		return (int) Math.round(yPos * this.height/ (this.juego.getMaximaPosicion().getY()+1)+this.squareHeight()/2);
	}

	/** Metodo que llama el view al efectuar un disparo.
	 * @param TipoDisparo disparo: tipo del disparo que se efectuo, es un enum.
	 * @param int x: coordenada en pixeles x del panel donde se realizo el disparo.
	 * @param int y: coordenada en pixeles y del panel donde se realizo el disparo.
	 */
	public void disparar(TipoDisparo disparo, int x, int y){
		Posicion posicion = new Posicion(this.x2pos(x), this.y2pos(y));
		Logger.log(LogLevel.INFO, disparo+" x:"+posicion.getX()+" y:"+posicion.getY());
		navalgobattle.model.Disparo modelDisparo = null;
		switch(disparo){
			case CONVENCIONAL:
				modelDisparo = new Convencional(this.juego, posicion);
				//TODO: Costos se podrian pasar a la configuracion del config y levantarlos con (Integer) Config.getObjeto("costoConvencional")
				modelDisparo.setCosto(200);
				this.juego.disparar((Convencional) modelDisparo);
				break;
			case MINA_SIMPLE:
				modelDisparo = new MinaRetardada(this.juego, posicion);
				modelDisparo.setCosto(50);
				((MinaRetardada) modelDisparo).setRetardo(3);
				this.juego.disparar((Mina) modelDisparo);
				break;
			case MINA_DOBLE:
				modelDisparo = new MinaRetardada(this.juego, posicion);
				modelDisparo.setCosto(100);
				((MinaRetardada) modelDisparo).setRetardo(3);
				((MinaRetardada) modelDisparo).setRadio(1);
				this.juego.disparar((MinaRetardada) modelDisparo);
				break;
			case MINA_TRIPLE:
				modelDisparo = new MinaRetardada(this.juego, posicion);
				modelDisparo.setCosto(125);
				((MinaRetardada) modelDisparo).setRetardo(3);
				((MinaRetardada) modelDisparo).setRadio(2);
				this.juego.disparar((MinaRetardada) modelDisparo);
				break;
			case MINA_CONTACTO:
				modelDisparo = new MinaContacto(this.juego, posicion);
				modelDisparo.setCosto(150);
				this.juego.disparar((MinaContacto) modelDisparo);
				break;
		}

		navalgobattle.controller.Disparo controllerDisparo = new navalgobattle.controller.Disparo(modelDisparo, this.gameLoop, this.squareWidth(), this.squareHeight());

		try { 
			this.juego.siguienteTurno();
		}catch(Exception e){ Logger.log(LogLevel.ERROR, "exception");} //TODO: Fijarse que hacer con la exceptcion

		if(this.juego.terminoJuego() && this.eventJuegoTerminado != null)
			this.eventJuegoTerminado.juegoTermino(this.juego.getNaves().size() == 0, this.juego.getPuntos());

		if(this.eventJuegoSiguienteTurno != null)
			this.eventJuegoSiguienteTurno.siguienteTurno();
	}


	/** Setea el evento de juego terminado
	 * @param EventJuegoTerminado eventJuegoTerminado: evento
	 */
	public void addJuegoTerminadoListener(EventJuegoTerminado eventJuegoTerminado){
		this.eventJuegoTerminado = eventJuegoTerminado;
	}

	/** Setea el evento de siguiente turno
	 * @param EventJuegoSiguienteTurno eventJuegoSiguienteTurno: evento
	 */
	public void addJuegoSiguienteTurnoListener(EventJuegoSiguienteTurno eventJuegoSiguienteTurno){
		this.eventJuegoSiguienteTurno = eventJuegoSiguienteTurno;
	}
}
