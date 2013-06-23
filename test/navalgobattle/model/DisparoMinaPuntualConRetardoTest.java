package navalgobattle.model;

import junit.framework.TestCase;
import navalgobattle.model.disparos.MinaContacto;
import navalgobattle.model.disparos.MinaRetardada;
import navalgobattle.model.naves.Buque;
import navalgobattle.model.naves.Lancha;
import navalgobattle.model.Posicion;
import navalgobattle.model.NavalgoBattle;
import org.junit.Before;
import org.junit.Test;

public class DisparoMinaPuntualConRetardoTest extends TestCase{
	NavalgoBattle juego;
	@Before
	public void setUp(){
		this.juego = new NavalgoBattle();
	}

	@Test
	public void testCrearMinaPuntualConRetardo() throws Exception {
		MinaRetardada disparo = new MinaRetardada(juego, new Posicion(5,5));
		disparo.setRetardo(3);
		disparo.setCosto(50);

		this.assertEquals(3, disparo.getRetardo());
		this.assertEquals(50, disparo.getCosto());
	}
	@Test
	public void testDispararMinaPuntualConRetardoSinNaves() throws Exception {
		MinaRetardada disparo = new MinaRetardada(juego, new Posicion(5,5));
		disparo.setRetardo(3);
		Buque nave = new Buque(new Posicion(5, 5), new Posicion(0, 0), 1);
		this.juego.addNave(nave);
		
		this.assertEquals(true, nave.estaViva());
		this.assertEquals(false, disparo.disparar());
		this.assertEquals(false, disparo.disparar());
		this.assertEquals(false, disparo.disparar());
		this.assertEquals(true, disparo.disparar());
		this.assertEquals(true, nave.estaViva());
	}
	@Test
	public void testDispararMinaPuntualConRetardoConNaves() throws Exception {
		MinaRetardada disparo = new MinaRetardada(juego, new Posicion(0,0));
		disparo.setRetardo(3);
		disparo.setRadio(0);
		Buque nave = new Buque(new Posicion(5, 5), new Posicion(0, 0), 1);
		this.juego.addNave(nave);
		
		this.assertEquals(true, nave.estaViva());
		this.assertEquals(false, disparo.disparar());
		this.assertEquals(false, disparo.disparar());
		this.assertEquals(false, disparo.disparar());
		this.assertEquals(true, disparo.disparar());
		this.assertEquals(false, nave.estaViva());
	}

}
