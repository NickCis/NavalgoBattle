package navalgobattle.model;

import org.junit.Test;
import org.junit.Before;
import junit.framework.TestCase;

import navalgobattle.model.Disparo;
import navalgobattle.model.NavalgoBattle;
import navalgobattle.model.disparos.Convencional;

public class DisparoConvencionalTest extends TestCase{
	protected NavalgoBattle juego;
	@Before
	public void setUp(){
		this.juego = new NavalgoBattle();
	}

	@Test
	public void testCrearDisparoConvensional() throws Exception {
		Convencional disparo = new Convencional(juego, new Posicion(5, 5));
		disparo.setCosto(200);
		this.assertEquals(200,disparo.getCosto());
	}

	@Test
	public void testDispararDisparoConvensional() throws Exception {
		Convencional disparo = new Convencional(juego, new Posicion(5, 5));

		this.assertEquals(true, disparo.disparar());
	}

}
