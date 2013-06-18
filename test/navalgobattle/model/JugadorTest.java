package navalgobattle.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import navalgobattle.model.Nave;

public class JugadorTest extends TestCase{
	@Before
	public void setUp(){
	}
	
	@Test
	public void testNuevoJugador() throws Exception {
		Jugador jugador = new Jugador();
		this.assertEquals(0, jugador.getPuntos());
	}

	@Test
	public void testNuevoJugadorConPuntos() throws Exception {
		Jugador jugador = new Jugador(100);
		this.assertEquals(100, jugador.getPuntos());
	}

	@Test
	public void testAddPuntos() throws Exception {
		Jugador jugador = new Jugador(100);
		jugador.addPuntos(100);
		this.assertEquals(200, jugador.getPuntos());
	}
}
