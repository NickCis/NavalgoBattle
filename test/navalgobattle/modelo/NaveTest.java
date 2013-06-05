package navalgobattle.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class NaveTest extends TestCase{
	@Before
	public void setUp(){
	}
	
	@Test
	public void testCrearNave() {
		Nave nave = new Nave(5, 5, 0, 0, 1);
		this.assertEquals(true, nave.estoyEnPosicion(0, 0));
	}

	@Test
	public void testMoverX(){
		Nave nave = new Nave(5, 5, 0, 0, 1);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(1, 0));
	}

	@Test
	public void testRebotarX(){
		Nave nave = new Nave(5, 5, 5, 0, 1);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(4, 0));
	}
	@Test
	public void testMoverY(){
		Nave nave = new Nave(5, 5, 0, 0, 4);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(0, 1));
	}

	@Test
	public void testRebotarY(){
		Nave nave = new Nave(5, 5, 0, 5, 4);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(0, 4));
	}
	@Test
	public void testMoverXY(){
		Nave nave = new Nave(5, 5, 0, 0, 5);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(1, 1));
	}

	@Test
	public void testRebotarXY(){
		Nave nave = new Nave(5, 5, 5, 5, 5);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(4, 4));
	}

	@Test
	public void testMatarNave(){
		Nave nave = new Nave(5, 5, 0, 0, 0);

		this.assertEquals(true, nave.estaViva());
		nave.danar((Disparo) null, 0, 0);
		this.assertEquals(false, nave.estaViva());
	}
}
