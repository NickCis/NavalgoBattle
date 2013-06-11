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
		PortaAviones nave = new PortaAviones(new Posicion(10, 10), new Posicion(0, 0), 1);

		this.assertEquals(5, nave.getSize());
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(4, 0)));
	}

	@Test
	public void testMoverX() throws Exception{
		PortaAviones nave = new PortaAviones(new Posicion(10, 10), new Posicion(0, 0), 1);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(4, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(5, 0)));
	}

	@Test
	public void testRebotarX() throws Exception{
		PortaAviones nave = new PortaAviones(new Posicion(10, 10), new Posicion(6, 0), 1);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(5, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(6, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(7, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(8, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(9, 0)));
	}
	@Test
	public void testMoverY() throws Exception{
		PortaAviones nave = new PortaAviones(new Posicion(10, 10), new Posicion(0, 0), 4);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 1)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 2)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 3)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 4)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 5)));
	}

	@Test
	public void testRebotarY() throws Exception{
		PortaAviones nave = new PortaAviones(new Posicion(10, 10), new Posicion(0, 6), 4);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 5)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 6)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 7)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 8)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 9)));
	}
	@Test
	public void testMoverXY() throws Exception{
		PortaAviones nave = new PortaAviones(new Posicion(10, 10), new Posicion(4, 4), 5);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(5, 5)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(6, 6)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(7, 7)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(8, 8)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(9, 9)));
	}

	@Test
	public void testRebotarXY() throws Exception{
		PortaAviones nave = new PortaAviones(new Posicion(10, 10), new Posicion(6, 6), 5);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(5, 5)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(6, 6)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(7, 7)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(8, 8)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(9, 9)));
	}

	@Test
	public void testMatarNave() throws Exception{
		PortaAviones nave = new PortaAviones(new Posicion(5, 5), new Posicion(0, 0), 1);

		nave.danar((Disparo) null, new Posicion(0, 0));
		nave.danar((Disparo) null, new Posicion(1, 0));
		nave.danar((Disparo) null, new Posicion(2, 0));
		nave.danar((Disparo) null, new Posicion(3, 0));
		nave.danar((Disparo) null, new Posicion(4, 0));
		this.assertEquals(false, nave.estaViva());
	}

	@Test
	public void testPosicionInvalida() throws Exception{
		boolean hasThrown = false;
		try {
			PortaAviones nave = new PortaAviones(new Posicion(5, 5), new Posicion(5, 5), 1);
		} catch(ExceptionNave exp){
			hasThrown = true;
		}

		this.assertEquals(true, hasThrown);
	}

}
