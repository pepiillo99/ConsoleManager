package me.pepe.ConsoleManager;

public abstract class Command {
	private String cmd;
	private String usage;
	public Command(String cmd, Console console, String uso) {
		this.cmd = cmd;
		console.addCommand(this);
		this.usage = uso;
	}
	public String getCommand() {
		return cmd;
	}
	public String getUsage() {
		return usage;
	}
	public abstract void execute(String[] var1);
}