package navalgobattle.model;

import junit.framework.TestCase;
import navalgobattle.model.disparos.MinaContacto;
import navalgobattle.model.naves.Lancha;
import navalgobattle.model.Posicion;
import navalgobattle.model.NavalgoBattle;
import org.junit.Before;
import org.junit.Test;

public class DisparoMinaContactoTest extends TestCase{
	NavalgoBattle juego;
	@Before
	public void setUp(){
		this.juego = new NavalgoBattle();
	}

	@Test
	public void testCrearMinaContacto() throws Exception {
		MinaContacto disparo = new MinaContacto(juego, new Posicion(5,5));
		disparo.setCosto(50);

		this.assertEquals(50, disparo.getCosto());
	}
	@Test
	public void testDispararMinaContactoSinNaves() throws Exception {
		MinaContacto disparo = new MinaContacto(juego, new Posicion(5,5));
		Lancha nave = new Lancha(new Posicion(5, 5), new Posicion(0, 0), 1);
		this.juego.addNave(nave);
		disparo.disparar();

		this.assertEquals(false, disparo.disparar());
	}
	@Test
	public void testDispararMinaContactoConNaves() throws Exception {
		MinaContacto disparo = new MinaContacto(juego, new Posicion(0,0));
		Lancha nave = new Lancha(new Posicion(5, 5), new Posicion(0, 0), 1);
		this.juego.addNave(nave);
		
		this.assertEquals(true, disparo.disparar());
	}

}
