package navalgobattle.util.logger;

import java.lang.Thread;
import java.lang.StackTraceElement;

import navalgobattle.util.logger.LogLevel;

/** Clase para loggear.
 * Utiliza patron de singleton. Se la debe inicializar a mano para que despues pueda ser usada.
 */
public class Logger {
	static LogLevel level;
	protected Logger(){}

	public static void initialice(){
		level = LogLevel.INFO;
	}

	public static void log(LogLevel log, String str, int nTrace){
		if(!level.doLog(log))
			return;

		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		System.out.println("["+log+"] '"+str+"' at "+trace[nTrace].getFileName()+" line: "+trace[nTrace].getLineNumber());
	}

	/** Logea dependiendo del nivel de log asignado.
	 * @param LogLevel log: nivel de log de este mensaje.
	 * @param Strin str: mensaje a loggear.
	 */
	public static void log(LogLevel log, String str){
		log(log, str, 3);
	}
	public static void log(String str){
		log(LogLevel.INFO, str, 3);
	}

	/** Setear el nivel de log.
	 * @param LogLevel log: Nivel de log.
	 */
	public static void setLogLevel(LogLevel log){
		level = log;
	}
}
