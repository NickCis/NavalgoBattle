package punto;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;



public class VectorTest extends TestCase{

	private Vector Vector1 ;
	private Vector Vector2 ;
	
	@Before
	public void setUp() {
		Vector1 = new Vector(5,2);
		Vector2 = new Vector(3,-2);	
	}
	
	
	@Test
	public void testSumar(){
		Vector aux = new Vector(8.0,0.0);		
		Vector resultado = Vector1.mas(Vector2);
		
		assertEquals(resultado.getX(),aux.getX());
		assertEquals(resultado.getY(),aux.getY());
	}
	
	@Test
	public void testMultiplicar(){
		assertEquals(Vector1.productoEscalarInternoCon(Vector2),11.0);
	}
	
	@Test
	public void testRestar(){
		Vector aux = new Vector(2.0,4.0);		
		Vector resultado = Vector1.menos(Vector2);
		
		assertEquals(resultado.getX(),aux.getX());
		assertEquals(resultado.getY(),aux.getY());
	}
	
	@Test
	public void testModulo(){
		assertEquals(Vector1.calcularModulo(),Math.sqrt(29));
		assertEquals(Vector2.calcularModulo(),Math.sqrt(13));
	}
	
	@Test
	public void testAngulo(){
		double alfa = Math.acos(11/Math.sqrt(377)); 
		assertEquals(Vector1.calcularAnguloCon(Vector2),alfa,1e-5);
	}
	
	@Test
	public void testPorEscalar(){
		Vector aux = new Vector (10,4);
		Vector resultado = Vector1.porEscalar(2);
		
		assertEquals(resultado.getX(),aux.getX());
		assertEquals(resultado.getY(),aux.getY());
	}

}
