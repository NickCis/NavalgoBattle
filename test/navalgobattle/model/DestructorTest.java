package navalgobattle.model;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import navalgobattle.model.naves.Destructor;
import navalgobattle.model.exceptions.ExceptionNave;
import navalgobattle.model.exceptions.PosicionInvalida;
import navalgobattle.model.Posicion;

import navalgobattle.model.Disparo;
import navalgobattle.model.disparos.Convencional;

import navalgobattle.util.logger.Logger;

public class DestructorTest extends TestCase{
	@Before
	public void setUp(){
		Logger.initialice();
	}

	@Test
	public void testCrearNave() throws Exception {
		Destructor nave = new Destructor(new Posicion(5, 5), new Posicion(0, 0), 1);

		this.assertEquals(3, nave.getSize());
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 0)));
	}

	@Test
	public void testMoverX() throws Exception{
		Destructor nave = new Destructor(new Posicion(5, 5), new Posicion(0, 0), 1);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 0)));
	}

	@Test
	public void testRebotarX() throws Exception{
		Destructor nave = new Destructor(new Posicion(5, 5), new Posicion(3, 0), 1);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 0)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(4, 0)));
	}
	@Test
	public void testMoverY() throws Exception{
		Destructor nave = new Destructor(new Posicion(5, 5), new Posicion(0, 0), 4);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 1)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 2)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 3)));
	}

	@Test
	public void testRebotarY() throws Exception{
		Destructor nave = new Destructor(new Posicion(5, 5), new Posicion(0, 3), 4);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 2)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 3)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(0, 4)));
	}
	@Test
	public void testMoverXY() throws Exception{
		Destructor nave = new Destructor(new Posicion(5, 5), new Posicion(0, 0), 5);
		nave.mover();
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 3)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 2)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(1, 1)));
	}

	@Test
	public void testRebotarXY() throws Exception{
		Destructor nave = new Destructor(new Posicion(5, 5), new Posicion(3, 3), 5);
		nave.mover();

		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(4, 4)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(3, 3)));
		this.assertEquals(true, nave.estoyEnPosicion(new Posicion(2, 2)));
	}

	@Test
	public void testMatarNaveNoConvencional() throws Exception{
		Destructor nave = new Destructor(new Posicion(5, 5), new Posicion(0, 0), 1);

		nave.danar((Disparo) null, new Posicion(0, 0));
		nave.danar((Disparo) null, new Posicion(1, 0));
		nave.danar((Disparo) null, new Posicion(2, 0));
		this.assertEquals(true, nave.estaViva());
	}

	@Test
	public void testMatarNave() throws Exception{
		Destructor nave = new Destructor(new Posicion(5, 5), new Posicion(0, 0), 1);

		nave.danar((Convencional) null, new Posicion(0, 0));
		nave.danar((Convencional) null, new Posicion(1, 0));
		nave.danar((Convencional) null, new Posicion(2, 0));
		this.assertEquals(false, nave.estaViva());
	}

	@Test
	public void testPosicionInvalida() throws Exception{
		boolean hasThrown = false;
		try {
			Destructor nave = new Destructor(new Posicion(5, 5), new Posicion(4, 5), 1);
		} catch(ExceptionNave exp){
			hasThrown = true;
		}

		this.assertEquals(true, hasThrown);
	}

}
