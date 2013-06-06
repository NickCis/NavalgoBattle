package navalgobattle.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import navalgobattle.model.naves.PortaAviones;
import navalgobattle.model.exceptions.ExceptionNave;
import navalgobattle.model.exceptions.PosicionInvalida;

public class PortaAvionesTest extends TestCase{
	@Before
	public void setUp(){
	}

	@Test
	public void testCrearNave() throws Exception {
		PortaAviones nave = new PortaAviones(10, 10, 1, 0, 0);

		this.assertEquals(5, nave.getSize());
		this.assertEquals(true, nave.estoyEnPosicion(0, 0));
		this.assertEquals(true, nave.estoyEnPosicion(1, 0));
		this.assertEquals(true, nave.estoyEnPosicion(2, 0));
		this.assertEquals(true, nave.estoyEnPosicion(3, 0));
		this.assertEquals(true, nave.estoyEnPosicion(4, 0));
	}

	@Test
	public void testMoverX() throws Exception{
		PortaAviones nave = new PortaAviones(10, 10, 1, 0, 0);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(1, 0));
		this.assertEquals(true, nave.estoyEnPosicion(2, 0));
		this.assertEquals(true, nave.estoyEnPosicion(3, 0));
		this.assertEquals(true, nave.estoyEnPosicion(4, 0));
		this.assertEquals(true, nave.estoyEnPosicion(5, 0));
	}

	@Test
	public void testRebotarX() throws Exception{
		PortaAviones nave = new PortaAviones(10, 10, 1, 6, 0);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(5, 0));
		this.assertEquals(true, nave.estoyEnPosicion(6, 0));
		this.assertEquals(true, nave.estoyEnPosicion(7, 0));
		this.assertEquals(true, nave.estoyEnPosicion(8, 0));
		this.assertEquals(true, nave.estoyEnPosicion(9, 0));
	}
	@Test
	public void testMoverY() throws Exception{
		PortaAviones nave = new PortaAviones(10, 10, 4, 0, 0);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(0, 1));
		this.assertEquals(true, nave.estoyEnPosicion(0, 2));
		this.assertEquals(true, nave.estoyEnPosicion(0, 3));
		this.assertEquals(true, nave.estoyEnPosicion(0, 4));
		this.assertEquals(true, nave.estoyEnPosicion(0, 5));
	}

	@Test
	public void testRebotarY() throws Exception{
		PortaAviones nave = new PortaAviones(10, 10, 4, 0, 6);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(0, 5));
		this.assertEquals(true, nave.estoyEnPosicion(0, 6));
		this.assertEquals(true, nave.estoyEnPosicion(0, 7));
		this.assertEquals(true, nave.estoyEnPosicion(0, 8));
		this.assertEquals(true, nave.estoyEnPosicion(0, 9));
	}
	@Test
	public void testMoverXY() throws Exception{
		PortaAviones nave = new PortaAviones(10, 10, 5, 4, 4);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(5, 5));
		this.assertEquals(true, nave.estoyEnPosicion(6, 6));
		this.assertEquals(true, nave.estoyEnPosicion(7, 7));
		this.assertEquals(true, nave.estoyEnPosicion(8, 8));
		this.assertEquals(true, nave.estoyEnPosicion(9, 9));
	}

	@Test
	public void testRebotarXY() throws Exception{
		PortaAviones nave = new PortaAviones(10, 10, 5, 6, 6);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(5, 5));
		this.assertEquals(true, nave.estoyEnPosicion(6, 6));
		this.assertEquals(true, nave.estoyEnPosicion(7, 7));
		this.assertEquals(true, nave.estoyEnPosicion(8, 8));
		this.assertEquals(true, nave.estoyEnPosicion(9, 9));
	}

	@Test
	public void testMatarNave() throws Exception{
		PortaAviones nave = new PortaAviones(5, 5, 1, 0, 0);

		nave.danar((Disparo) null, 0, 0);
		nave.danar((Disparo) null, 1, 0);
		nave.danar((Disparo) null, 2, 0);
		nave.danar((Disparo) null, 3, 0);
		nave.danar((Disparo) null, 4, 0);
		this.assertEquals(false, nave.estaViva());
	}

	@Test
	public void testPosicionInvalida() throws Exception{
		boolean hasThrown = false;
		try {
			PortaAviones nave = new PortaAviones(5, 5, 1, 5, 5);
		} catch(ExceptionNave exp){
			hasThrown = true;
		}

		this.assertEquals(true, hasThrown);
	}

}
