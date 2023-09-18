package me.pepe.ConsoleManager;

public enum ChatColors {
	RESET("&r", "\033[0m"), 
	DARK_BLUE("&1", "\033[0;34m"), 
	DARK_GREEN("&2", "\033[0;32m"), 
	CYAN("&3", "\033[0;36m"),
	DARK_RED("&4", "\033[0;31m"), 
	PURPLE("&5", "\033[0;35m"), 
	GOLD("&6", "\u001b[33m"), 
	GRAY("&7", "\u001b[37m"),
	DARK_GRAY("&8", "\u001b[30;1m"), 
	BLUE("&9", "\u001b[34;1m"), 
	YELLOW("&e", "\u001b[33;1m"),
	RED("&c", "\u001b[31;1m"), 
	AQUA("&b", "\u001b[36;1m"), 
	PINK("&d", "\u001b[35;1m"), 
	GREEN("&a", "\u001b[32;1m"),
	BLACK("&0", "\u001b[0m");

	private String code;
	private String replace;
	private ChatColors(String code, String replace) {
		this.code = code;
		this.replace = replace;
	}
	public String getCode() {
		return this.code;
	}
	public String getReplace() {
		return this.replace;
	}
	public static String replace(String message) {
		for (ChatColors cc : values()) {
			message = message.replaceAll(cc.getCode(), cc.getReplace());
		}
		return message + RESET.getReplace();
	}
}