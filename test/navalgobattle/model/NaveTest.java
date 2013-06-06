package navalgobattle.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import navalgobattle.model.Nave;
import navalgobattle.model.exceptions.ExceptionNave;
import navalgobattle.model.exceptions.PosicionInvalida;

public class NaveTest extends TestCase{
	@Before
	public void setUp(){
	}
	
	@Test
	public void testCrearNave() throws Exception {
		Nave nave = new Nave(5, 5, 1, 0, 0);
		this.assertEquals(true, nave.estoyEnPosicion(0, 0));
	}

	@Test
	public void testMoverX() throws Exception{
		Nave nave = new Nave(5, 5, 1, 0, 0);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(1, 0));
	}

	@Test
	public void testRebotarX() throws Exception{
		Nave nave = new Nave(5, 5, 1, 5, 0);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(4, 0));
	}
	@Test
	public void testMoverY() throws Exception{
		Nave nave = new Nave(5, 5, 4, 0, 0);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(0, 1));
	}

	@Test
	public void testRebotarY() throws Exception{
		Nave nave = new Nave(5, 5, 4, 0, 5);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(0, 4));
	}
	@Test
	public void testMoverXY() throws Exception{
		Nave nave = new Nave(5, 5, 5, 0, 0);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(1, 1));
	}

	@Test
	public void testRebotarXY() throws Exception{
		Nave nave = new Nave(5, 5, 5, 5, 5);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(4, 4));
	}

	@Test
	public void testMatarNave() throws Exception{
		Nave nave = new Nave(5, 5, 0, 0, 0);

		this.assertEquals(true, nave.estaViva());
		nave.danar((Disparo) null, 0, 0);
		this.assertEquals(false, nave.estaViva());
	}

	@Test
	public void testPosicionInvalida() throws Exception{
		boolean hasThrown = false;
		try {
			Nave nave = new Nave(5, 5, 0, 6, 6);
		} catch(ExceptionNave exp){
			hasThrown = true;
		}

		this.assertEquals(true, hasThrown);
	}

}


