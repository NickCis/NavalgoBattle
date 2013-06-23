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

public class DisparoMinaDobleConRetardoTest extends TestCase{
	NavalgoBattle juego;
	@Before
	public void setUp(){
		this.juego = new NavalgoBattle();
		this.juego.setMaximaPosicion(new Posicion(5,5));
	}

	@Test
	public void testCrearMinaDobleConRetardo() throws Exception {
		MinaRetardada disparo = new MinaRetardada(juego, new Posicion(5,5));
		disparo.setRetardo(3);
		disparo.setRadio(1);
		disparo.setCosto(100);

		this.assertEquals(1, disparo.getRadio());
		this.assertEquals(3, disparo.getRetardo());
		this.assertEquals(100, disparo.getCosto());
	}
	@Test
	public void testDispararMinaDobleConRetardoSinNaves() throws Exception {
		MinaRetardada disparo = new MinaRetardada(juego, new Posicion(5,5));
		disparo.setRetardo(3);
		disparo.setRadio(1);
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
	public void testDispararMinaDobleConRetardoConNaves() throws Exception {
		MinaRetardada disparo = new MinaRetardada(juego, new Posicion(0,0));
		disparo.setRetardo(3);
		disparo.setRadio(1);
		Buque nave1 = new Buque(new Posicion(5, 5), new Posicion(0, 0), 1);
		Buque nave2 = new Buque(new Posicion(5, 5), new Posicion(1, 0), 1);
		Buque nave3 = new Buque(new Posicion(5, 5), new Posicion(2, 0), 1);
		this.juego.addNave(nave1);
		this.juego.addNave(nave2);
		this.juego.addNave(nave3);
		
		this.assertEquals(true, nave1.estaViva());
		this.assertEquals(true, nave2.estaViva());
		this.assertEquals(true, nave3.estaViva());
		this.assertEquals(false, disparo.disparar());
		this.assertEquals(false, disparo.disparar());
		this.assertEquals(false, disparo.disparar());
		this.assertEquals(true, disparo.disparar());
		this.assertEquals(false, nave1.estaViva());
		this.assertEquals(false, nave2.estaViva());
		this.assertEquals(true, nave3.estaViva());
	}

}