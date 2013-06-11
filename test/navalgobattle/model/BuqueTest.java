package navalgobattle.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import navalgobattle.model.naves.Buque;
import navalgobattle.model.Posicion;
import navalgobattle.model.exceptions.ExceptionNave;
import navalgobattle.model.exceptions.PosicionInvalida;

public class BuqueTest extends TestCase{
	@Before
	public void setUp(){
	}

	@Test
	public void testCrearNave() throws Exception {
		Buque nave = new Buque(new Posicion(5,5), new Posicion(0, 0), 1);

		this.assertEquals(4, nave.getSize());
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 0)));
	}

	@Test
	public void testMoverX() throws Exception{
		Buque nave = new Buque(new Posicion(5, 5), new Posicion(0, 0), 1);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(4, 0)));
	}

	@Test
	public void testRebotarX() throws Exception{
		Buque nave = new Buque(new Posicion(5, 5), new Posicion(0, 0), 1);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(4, 0)));
	}
	@Test
	public void testMoverY() throws Exception{
		Buque nave = new Buque(new Posicion(5, 5), new Posicion(0, 0), 4);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 1)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 2)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 3)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 4)));
	}

	@Test
	public void testRebotarY() throws Exception{
		Buque nave = new Buque(new Posicion(5, 5), new Posicion(0, 2), 4);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 1)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 2)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 3)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 4)));
	}
	@Test
	public void testMoverXY() throws Exception{
		Buque nave = new Buque(new Posicion(5, 5), new Posicion( 0, 0), 5);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(4, 4)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 3)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 2)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 1)));
	}

	@Test
	public void testRebotarXY() throws Exception{
		Buque nave = new Buque(new Posicion(5, 5), new Posicion(2, 2), 5);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(4, 4)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 3)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 2)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 1)));
	}

	@Test
	public void testMatarNave() throws Exception{
		Buque nave = new Buque(new Posicion(5, 5), new Posicion(0, 0), 1);

		nave.danar((Disparo) null, new Posicion(0, 0));
		this.assertEquals(false, nave.estaViva());
	}

	@Test
	public void testPosicionInvalida() throws Exception{
		boolean hasThrown = false;
		try {
			Buque nave = new Buque(new Posicion(5, 5), new Posicion(5, 5), 1);
		} catch(ExceptionNave exp){
			hasThrown = true;
		}

		this.assertEquals(true, hasThrown);
	}

}
