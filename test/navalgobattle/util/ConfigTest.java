package navalgobattle.util.config;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import navalgobattle.util.config.Config;
public class ConfigTest extends TestCase{
	@Before
	public void setUp(){
		Config.initialice();
	}

	@Test
	public void testSet() throws Exception {
		Integer a = 1;
		String b = "hola";
		Config.setObject("parametro1", (Object) a);
		Config.setObject("parametro2", (Object) b);

		this.assertEquals(a, (Integer) Config.getObject("parametro1"));
		this.assertEquals(b, (String) Config.getObject("parametro2"));
	}
}
