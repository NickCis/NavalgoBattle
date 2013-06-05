package navalgobattle.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class RompeHielosTest extends TestCase{
	@Before
	public void setUp(){
	}

	@Test
	public void testCrearNave() throws Exception {
		RompeHielos nave = new RompeHielos(5, 5, 1, 0, 0);

		this.assertEquals(4, nave.getSize());
		this.assertEquals(true, nave.estoyEnPosicion(0, 0));
		this.assertEquals(true, nave.estoyEnPosicion(1, 0));
		this.assertEquals(true, nave.estoyEnPosicion(2, 0));
		this.assertEquals(true, nave.estoyEnPosicion(3, 0));
	}

	@Test
	public void testMoverX() throws Exception{
		RompeHielos nave = new RompeHielos(5, 5, 1, 0, 0);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(1, 0));
		this.assertEquals(true, nave.estoyEnPosicion(2, 0));
		this.assertEquals(true, nave.estoyEnPosicion(3, 0));
		this.assertEquals(true, nave.estoyEnPosicion(4, 0));
	}

	@Test
	public void testRebotarX() throws Exception{
		RompeHielos nave = new RompeHielos(5, 5, 1, 2, 0);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(1, 0));
		this.assertEquals(true, nave.estoyEnPosicion(2, 0));
		this.assertEquals(true, nave.estoyEnPosicion(3, 0));
		this.assertEquals(true, nave.estoyEnPosicion(4, 0));
	}
	@Test
	public void testMoverY() throws Exception{
		RompeHielos nave = new RompeHielos(5, 5, 4, 0, 0);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(0, 1));
		this.assertEquals(true, nave.estoyEnPosicion(0, 2));
		this.assertEquals(true, nave.estoyEnPosicion(0, 3));
		this.assertEquals(true, nave.estoyEnPosicion(0, 4));
	}

	@Test
	public void testRebotarY() throws Exception{
		RompeHielos nave = new RompeHielos(5, 5, 4, 0, 2);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(0, 1));
		this.assertEquals(true, nave.estoyEnPosicion(0, 2));
		this.assertEquals(true, nave.estoyEnPosicion(0, 3));
		this.assertEquals(true, nave.estoyEnPosicion(0, 4));
	}
	@Test
	public void testMoverXY() throws Exception{
		RompeHielos nave = new RompeHielos(5, 5, 5, 0, 0);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(4, 4));
		this.assertEquals(true, nave.estoyEnPosicion(3, 3));
		this.assertEquals(true, nave.estoyEnPosicion(2, 2));
		this.assertEquals(true, nave.estoyEnPosicion(1, 1));
	}

	@Test
	public void testRebotarXY() throws Exception{
		RompeHielos nave = new RompeHielos(5, 5, 5, 2, 2);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(4, 4));
		this.assertEquals(true, nave.estoyEnPosicion(3, 3));
		this.assertEquals(true, nave.estoyEnPosicion(2, 2));
		this.assertEquals(true, nave.estoyEnPosicion(1, 1));
	}

	@Test
	public void testMatarNave() throws Exception{
		RompeHielos nave = new RompeHielos(5, 5, 1, 0, 0);

		nave.danar((Disparo) null, 0, 0);
		nave.danar((Disparo) null, 1, 0);
		nave.danar((Disparo) null, 2, 0);
		nave.danar((Disparo) null, 3, 0);
		this.assertEquals(true, nave.estaViva());
		nave.danar((Disparo) null, 0, 0);
		nave.danar((Disparo) null, 1, 0);
		nave.danar((Disparo) null, 2, 0);
		nave.danar((Disparo) null, 3, 0);
		this.assertEquals(false, nave.estaViva());
	}

	@Test
	public void testPosicionInvalida() throws Exception{
		boolean hasThrown = false;
		try {
			RompeHielos nave = new RompeHielos(5, 5, 1, 5, 5);
		} catch(ExceptionNave exp){
			hasThrown = true;
		}

		this.assertEquals(true, hasThrown);
	}

}
