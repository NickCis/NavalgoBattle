package navalgobattle.model;

import junit.framework.TestCase;
import navalgobattle.model.disparos.Convencional;
import navalgobattle.model.Disparo;       
import org.junit.Before;
import org.junit.Test;

public class DisparoConvencionalTest extends TestCase{
	@Before
	public void setUp(){
	}

	@Test
	public void testCrearDisparoConvensional() throws Exception {
		Convencional disparo = new Convencional(5, 5);
                disparo.setCosto(200);
		this.assertEquals(200,disparo.getCosto());
	}
        
        @Test
	public void testDispararDisparoConvensional() throws Exception {
		Convencional disparo = new Convencional(5, 5);
                disparo.disparar();

		this.assertEquals(true, disparo.disparar());
	}
        
}       