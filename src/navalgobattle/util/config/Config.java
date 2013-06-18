package navalgobattle.util.config;

import java.util.Hashtable;

/** Clase para almacenar la configuracion.
 * Debe ser inicializada en un ppcio para que despues pueda ser usada. Utiliza patron de singleton.
 */
public class Config {
	static protected Hashtable<String, Object> data;

	protected Config(){}

	public static void initialice(){
		data = new Hashtable<String, Object>();
	}

	/** Obtiene objeto bajo la llave pasada.
	 * @param String key: llave.
	 * @return Object: objeto guardado o null si no se encontro la llave.
	 */
	public static Object getObject(String key){
		return data.get(key);
	}

	/** Guarda objeto bajo la llave pasada.
	 * @param String key: llave.
	 * @param Object obj: objeto a guardar.
	 */
	public static void setObject(String key, Object obj){
		data.put(key, obj);
	}
}
