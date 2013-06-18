package navalgobattle.util.logger;

public enum LogLevel {
	DEBUG(1|2|4|8|16),
	INFO(1|2|4|8),
	WARN(1|2|4),
	ERROR(1|2),
	FATAL(1),
	OFF(0);

	private int level;

	LogLevel(int level){
		this.level = level;
	}

	protected int getLevel(){
		return this.level;
	}

	public boolean doLog(LogLevel level){
		if((this.level & level.getLevel()) == level.getLevel())
			return true;
		return false;
	}
}
