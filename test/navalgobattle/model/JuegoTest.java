package navalgobattle.model;

import java.util.ArrayList;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import navalgobattle.model.Disparo;
import navalgobattle.model.disparos.Convencional;
import navalgobattle.model.disparos.MinaContacto;
import navalgobattle.model.disparos.MinaRetardada;
import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.Jugador;
import navalgobattle.model.Nave;
import navalgobattle.model.naves.Lancha;
import navalgobattle.util.logger.Logger;
import navalgobattle.util.logger.LogLevel;
import navalgobattle.model.disparos.TipoDisparo;

public class JuegoTest extends TestCase{
	NavalgoBattle juego;
	Posicion pos;
	Jugador jugador;
	@Before
	public void setUp(){
		this.jugador = new Jugador();
		this.pos = new Posicion(10,10);
		this.juego = new NavalgoBattle();
		this.juego.setJugador(jugador);

		Logger.initialice();
	}

	public void testTerminoJuego() throws Exception {
		Jugador jug = new Jugador();
		juego.addNave(new Nave(pos,pos,1));
		juego.setJugador(jug);

		this.assertEquals(juego.terminoJuego(), true);
		jug.addPuntos(1);
		this.assertEquals(juego.terminoJuego(), false);
		juego.siguienteTurno(); 
		this.assertEquals(juego.terminoJuego(), true);


	}
	public void testMatarNaveConConvensional()  throws Exception{
		//se matan en el mismo turno para hacer el test
		Lancha nave = new Lancha(pos,new Posicion(2,2),1);
		juego.addNave(nave);
		ArrayList<Posicion> posiciones = nave.getPosiciones();

		juego.doDisparar(TipoDisparo.CONVENCIONAL, posiciones.get(0));
		juego.doDisparar(TipoDisparo.CONVENCIONAL, posiciones.get(1));
		juego.siguienteTurno();
		this.assertEquals(nave.estaViva(), false);
	}
	public void testMatarNaveConMinaContacto()  throws Exception{
		//se matan en el mismo turno para hacer el test
		Lancha nave = new Lancha(pos,new Posicion(2,2),1);
		juego.addNave(nave);
		ArrayList<Posicion> posiciones = nave.getPosiciones();

		juego.doDisparar(TipoDisparo.MINA_CONTACTO, posiciones.get(0).getSiguiente(1));
		juego.doDisparar(TipoDisparo.MINA_CONTACTO, posiciones.get(1).getSiguiente(1));
		juego.siguienteTurno();
		this.assertEquals(nave.estaViva(), false);
		this.assertEquals(juego.terminoJuego(), true);
	}
	public void testMatarNaveConMinaRetardada()  throws Exception{
		//se matan en el mismo turno para hacer el test
		Lancha nave = new Lancha(pos,new Posicion(2,2),1);
		juego.setMaximaPosicion(pos);
		juego.addNave(nave);
		ArrayList<Posicion> posiciones = nave.getPosiciones();
		
		juego.doDisparar(TipoDisparo.MINA_TRIPLE, new Posicion(5, 2));

		juego.siguienteTurno();
		Posicion pos = nave.getPosiciones().get(0);
		juego.siguienteTurno();
		juego.siguienteTurno();
		juego.siguienteTurno();

		this.assertEquals(nave.estaViva(), false);
	}
	public void testPuntosDuranteElJuego()  throws Exception{
		NavalgoBattle juego = new NavalgoBattle(pos,jugador);
		jugador.addPuntos(10000);
		juego.setPuntosPorTurno(10);
		//10000
		juego.doDisparar(TipoDisparo.CONVENCIONAL, pos);
		//9800
		juego.siguienteTurno();
		//9790
		juego.doDisparar(TipoDisparo.MINA_CONTACTO, pos);
		//9640
		juego.siguienteTurno();
		//9630

		this.assertEquals(juego.getTurno(),2 );
		this.assertEquals(jugador.getPuntos(),9630 );
	}
}
