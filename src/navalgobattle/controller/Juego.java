package navalgobattle.controller;

import java.util.ArrayList;
import java.lang.Math;


import fiuba.algo3.titiritero.modelo.ObjetoPosicionable;

import navalgobattle.model.Jugador;
import navalgobattle.model.Posicion;
import navalgobattle.model.NavalgoBattle;
//import navalgobattle.model.Nave;
import navalgobattle.model.naves.Lancha;
import navalgobattle.model.Disparo;
import navalgobattle.model.disparos.Convencional;
import navalgobattle.model.disparos.Mina;
import navalgobattle.model.disparos.MinaRetardada;
import navalgobattle.model.disparos.MinaContacto;

import fiuba.algo3.titiritero.modelo.GameLoop;

//import navalgobattle.view.Nave;
//import navalgobattle.controller.Nave;

import navalgobattle.controller.TipoDisparo;

import navalgobattle.util.config.Config;
import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;


public class Juego {
	protected NavalgoBattle juego;
	protected int width;
	protected int height;
	protected GameLoop gameLoop;

	public Juego(int width, int height, GameLoop gameLoop){
		this.width = width;
		this.height = height;
		this.gameLoop = gameLoop;
		this.juego = new NavalgoBattle(new Posicion((Posicion) Config.getObject("maxPos")), new Jugador());
	}

	public void agregarNavesRandom(){
		//TODO: pasar estas cuentas a otro metodo
		int ladoX = (int) Math.round(this.width / (this.juego.getMaximaPosicion().getX()+1));
		int ladoY = (int) Math.round(this.height / (this.juego.getMaximaPosicion().getY()+1));

		try {
			Posicion randomPos = this.randomPosicion();
			int randomDir = this.randomDireccion();

			navalgobattle.model.Nave modelNave = new Lancha(this.juego.getMaximaPosicion(), randomPos, randomDir);
			Logger.log(LogLevel.INFO, "Nave creada x:"+randomPos.getX()+" y:"+randomPos.getY()+" dir:"+randomDir);

			navalgobattle.controller.Nave controllerNave = new navalgobattle.controller.Nave(modelNave, this.gameLoop, ladoX, ladoY);

			this.juego.addNave(modelNave);

		}catch(Exception e) {
			Logger.log(LogLevel.ERROR, "Excepcion que nunca deberia pasar");
			//TODO: Hacer algo con la excepcion?,, realmente nunca deberia llegar por la forma que se crean las posiciones random
		}
	}

	public void disparar(TipoDisparo disparo, int x, int y){
		//TODO: pasarlo a un metodo
		Posicion posicion = new Posicion((int) Math.round(x * (this.juego.getMaximaPosicion().getX()+1)/ this.width), (int) Math.round(y * (this.juego.getMaximaPosicion().getY()+1)/ this.height));
		Logger.log(LogLevel.INFO, disparo+" x:"+posicion.getX()+" y:"+posicion.getY());
		switch(disparo){
			case CONVENCIONAL:
				this.juego.disparar(new Convencional(this.juego, posicion));
				break;
			case MINA:
				this.juego.disparar(new Mina(this.juego, posicion));
				break;
			case MINA_RETARDADA:
				this.juego.disparar(new MinaContacto(this.juego, posicion));
				break;
			case MINA_CONTACTO:
				this.juego.disparar(new MinaRetardada(this.juego, posicion));
				break;
		}
		try { 
			this.juego.siguienteTurno();
		}catch(Exception e){ Logger.log(LogLevel.ERROR, "exception");} //TODO: Fijarse que hacer con la exceptcion
	}

	protected Posicion randomPosicion(){
		int x = (int) Math.round(Math.random() * this.juego.getMaximaPosicion().getX());
		int y = (int) Math.round(Math.random() * this.juego.getMaximaPosicion().getY());

		return new Posicion(x, y);
	}

	protected int randomDireccion(){
		//TODO: hacerlo mas lindo
		int dir = (int) Math.round(Math.random() * 15);//La posicion maxima es 1111 -> 15
		//TODO: Habria que poner la direccion como un enum o algo y dejar de hacer estas cosas sucias
		if ((dir & 1) == 0 && (dir & 4) == 0 )
			return this.randomDireccion();
		return dir;
	}
}
