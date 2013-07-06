package navalgobattle.controller;

import java.util.ArrayList;

import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

import navalgobattle.model.Jugador;
import navalgobattle.model.Posicion;
import navalgobattle.model.NavalgoBattle;
//import navalgobattle.model.Nave;
import navalgobattle.model.naves.Lancha;
//import navalgobattle.model.Disparo;
import navalgobattle.model.disparos.TipoDisparo;
import navalgobattle.model.disparos.Convencional;
import navalgobattle.model.disparos.Mina;
import navalgobattle.model.disparos.MinaRetardada;
import navalgobattle.model.disparos.MinaContacto;

import fiuba.algo3.titiritero.modelo.GameLoop;

//import navalgobattle.view.Nave;
//import navalgobattle.controller.Nave;

//import navalgobattle.controller.Disparo;


import navalgobattle.controller.CalculadoraDePixeles;
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
	protected CalculadoraDePixeles calc;
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
		this.calc = new CalculadoraDePixeles(this.juego, this.width, this.height);
		final Juego that = this;
		final CalculadoraDePixeles calc = this.calc;
		this.juego.addAgregarNaveListener(new EventAgregarNave(){
			public void agregarNave(navalgobattle.model.Nave modelNave, navalgobattle.model.naves.TipoNave tipo){
				navalgobattle.controller.Nave controllerNave = new navalgobattle.controller.Nave(modelNave, that.gameLoop, calc.squareWidth(), calc.squareHeight(), tipo);
			}
		});
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

	/** Metodo que llama el view al efectuar un disparo.
	 * @param TipoDisparo disparo: tipo del disparo que se efectuo, es un enum.
	 * @param int x: coordenada en pixeles x del panel donde se realizo el disparo.
	 * @param int y: coordenada en pixeles y del panel donde se realizo el disparo.
	 */
	public void disparar(TipoDisparo disparo, int x, int y){
		Posicion posicion = new Posicion(this.calc.x2pos(x), this.calc.y2pos(y));
		Logger.log(LogLevel.INFO, disparo+" x:"+posicion.getX()+" y:"+posicion.getY());
		navalgobattle.model.Disparo modelDisparo = this.juego.doDisparar(disparo, posicion);
		navalgobattle.controller.Disparo controllerDisparo = new navalgobattle.controller.Disparo(modelDisparo, this.gameLoop, this.calc.squareWidth(), this.calc.squareHeight(), disparo);

		try { 
			this.juego.siguienteTurno();
		}catch(Exception e){ Logger.log(LogLevel.ERROR, "exception");} //TODO: Fijarse que hacer con la exceptcion

		if(this.juego.terminoJuego() && this.eventJuegoTerminado != null)
			this.eventJuegoTerminado.juegoTermino(this.juego.ganoJuego(), this.juego.getPuntos());

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
