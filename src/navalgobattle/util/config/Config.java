package navalgobattle.util.config;

import java.util.Hashtable;

public class Config {
	static protected Hashtable<String, Object> data;

	protected Config(){}

	public static void initialice(){
		data = new Hashtable<String, Object>();
	}

	public static Object getObject(String key){
		return data.get(key);
	}

	public static void setObject(String key, Object obj){
		data.put(key, obj);
	}
}
