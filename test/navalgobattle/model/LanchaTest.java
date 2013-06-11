package navalgobattle.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import navalgobattle.model.naves.Lancha;
import navalgobattle.model.exceptions.ExceptionNave;
import navalgobattle.model.exceptions.PosicionInvalida;


public class LanchaTest extends TestCase{
	@Before
	public void setUp(){
	}

	@Test
	public void testCrearNave() throws Exception {
		Lancha nave = new Lancha(new Posicion(5, 5), new Posicion(0, 0), 1);

		this.assertEquals(2, nave.getSize());
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 0)));
	}

	@Test
	public void testMoverX() throws Exception{
		Lancha nave = new Lancha(new Posicion(5, 5), new Posicion(0, 0), 1);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 0)));
	}

	@Test
	public void testRebotarX() throws Exception{
		Lancha nave = new Lancha(new Posicion(5, 5), new Posicion(4, 0), 1);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(4, 0)));
	}
	@Test
	public void testMoverY() throws Exception{
		Lancha nave = new Lancha(new Posicion(5, 5), new Posicion(0, 0), 4);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 1)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 2)));
	}

	@Test
	public void testRebotarY() throws Exception{
		Lancha nave = new Lancha(new Posicion(5, 5), new Posicion(0, 4), 4);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 3)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 4)));
	}
	@Test
	public void testMoverXY() throws Exception{
		Lancha nave = new Lancha(new Posicion(5, 5),new Posicion( 0, 0), 5);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 2)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 1)));
	}

	@Test
	public void testRebotarXY() throws Exception{
		Lancha nave = new Lancha(new Posicion(5, 5), new Posicion(4, 4), 5);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(4, 4)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 3)));
	}

	@Test
	public void testMatarNave() throws Exception{
		Lancha nave = new Lancha(new Posicion(5, 5), new Posicion(0, 0), 1);

		this.assertEquals(true, nave.estaViva());
		nave.danar((Disparo) null, new Posicion(0, 0));
		this.assertEquals(true, nave.estaViva());
		nave.danar((Disparo) null, new Posicion(1, 0));
		this.assertEquals(false, nave.estaViva());
	}

	@Test
	public void testPosicionInvalida() throws Exception{
		boolean hasThrown = false;
		try {
			Lancha nave = new Lancha(new Posicion(5, 5), new Posicion(5, 5), 1);
		} catch(ExceptionNave exp){
			hasThrown = true;
		}

		this.assertEquals(true, hasThrown);
	}

}
