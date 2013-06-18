package navalgobattle.util.logger;

import java.lang.Thread;
import java.lang.StackTraceElement;

import navalgobattle.util.logger.LogLevel;

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
		System.out.println("["+level+"] '"+str+"' at "+trace[nTrace].getFileName()+" line: "+trace[nTrace].getLineNumber());
	}

	public static void log(LogLevel log, String str){
		log(log, str, 3);
	}
	public static void log(String str){
		log(LogLevel.INFO, str, 3);
	}
}
