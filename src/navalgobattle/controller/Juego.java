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

//import navalgobattle.view.Nave;
//import navalgobattle.controller.Nave;

import navalgobattle.controller.TipoDisparo;

public class Juego {
	NavalgoBattle juego;
	int width;
	int height;

	public Juego(int width, int height){
		this.width = width;
		this.height = height;
		//TODO: pasar esto a un lugar configurable
		this.juego = new NavalgoBattle(new Posicion(10,10), new Jugador());
	}

	public ArrayList<navalgobattle.view.Nave> agregarNavesRandom(){
		ArrayList<navalgobattle.view.Nave> naves = new ArrayList<navalgobattle.view.Nave>();
		int ladoX = (int) Math.round(this.width / this.juego.getMaximaPosicion().getX());
		int ladoY = (int) Math.round(this.height / this.juego.getMaximaPosicion().getY());

		try {
			navalgobattle.model.Nave modelNave = new Lancha(this.juego.getMaximaPosicion(), this.randomPosicion(), this.randomDireccion());
			this.juego.addNave(modelNave);
			navalgobattle.controller.Nave controllerNave = new navalgobattle.controller.Nave(modelNave, ladoX, ladoY);
			navalgobattle.view.Nave viewNave = new navalgobattle.view.Nave(controllerNave);
			naves.add(viewNave);
		}catch(Exception e) {
			//TODO: Hacer algo con la excepcion?,, realmente nunca deberia llegar por la forma que se crean las posiciones random
		}

		return naves;
	}

	public void disparar(TipoDisparo disparo, int x, int y){
		Posicion posicion = new Posicion((int) Math.round(x * this.juego.getMaximaPosicion().getX()/ this.width), (int) Math.round(y * this.juego.getMaximaPosicion().getY()/ this.height));
		System.out.println(disparo+" x:"+posicion.getX()+" y:"+posicion.getY());
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
		}catch(Exception e){ System.out.println("exception");} //TODO: Fijarse que hacer con la exceptcion
	}

	protected Posicion randomPosicion(){
		int x = (int) Math.round(Math.random() * this.juego.getMaximaPosicion().getX());
		int y = (int) Math.round(Math.random() * this.juego.getMaximaPosicion().getY());

		return new Posicion(x, y);
	}

	protected int randomDireccion(){
		//TODO: hacerlo mas lindo
		int pos = (int) Math.round(Math.random() * 15);//La posicion maxima es 1111 -> 15
		if (pos == 0)
			return this.randomDireccion();
		return pos;
	}
}
