package me.pepe.ConsoleManager;

import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fusesource.jansi.AnsiConsole;

import jline.console.ConsoleReader;

public class Console {
	private List<Command> commands = new ArrayList<Command>();
	public Console() {
        AnsiConsole.systemInstall();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String outPrefix = "[{hour} INFO]: ";
        String errPrefix = "[{hour} &4WARN]&r: ";
		System.setOut(new PrintStream(System.out) {
            @Override
            public void println(String s) {
                QueueLog.append(ChatColors.replace(outPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(Object s) {
                QueueLog.append(ChatColors.replace(outPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s.toString()));
            }
            @Override
            public void println(boolean s) {
                QueueLog.append(ChatColors.replace(outPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(float s) {
                QueueLog.append(ChatColors.replace(outPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(double s) {
                QueueLog.append(ChatColors.replace(outPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(int s) {
                QueueLog.append(ChatColors.replace(outPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(long s) {
                QueueLog.append(ChatColors.replace(outPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(char s) {
                QueueLog.append(ChatColors.replace(outPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
        });
        System.setErr(new PrintStream(System.err) {
            @Override
            public void println(String s) {
                QueueLog.append(ChatColors.replace(errPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(Object s) {
                QueueLog.append(ChatColors.replace(errPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s.toString()));
            }
            @Override
            public void println(boolean s) {
                QueueLog.append(ChatColors.replace(errPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(float s) {
                QueueLog.append(ChatColors.replace(errPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(double s) {
                QueueLog.append(ChatColors.replace(errPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(int s) {
                QueueLog.append(ChatColors.replace(errPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(long s) {
                QueueLog.append(ChatColors.replace(errPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
            @Override
            public void println(char s) {
                QueueLog.append(ChatColors.replace(errPrefix.replace("{hour}", dateFormat.format(new Date(System.currentTimeMillis()))) + s));
            }
        });
        try {
        	ConsoleReader consoleReader = new ConsoleReader(System.in, System.out);
            consoleReader.setExpandEvents(false);
            new Thread(new TerminalConsoleWriterThread(System.out, consoleReader)).start();
            new Thread() {
            	@Override
            	public void run() {
                    while (true) {
						try {
                            executeCommand(consoleReader.readLine(">", null));
						} catch (IOException e) {
							e.printStackTrace();
						}
                    }
            	}
            }.start();        
        } catch (IOException e) {
            e.printStackTrace();
        }
		new Command("help", this, "Muestra la lista de comandos del servidor y dice para que se usa") {
			@Override
			public void execute(String[] args) {
				if (args.length == 0) {
					System.out.println("Lista de comandos disponibles: ");
					for (Command cmdx : commands) {
						System.out.println("/" + cmdx.getCommand() + " - " + cmdx.getUsage());
					}
				} else if (args.length == 1) {
					String cmd = args[0];
					if (getRegistredCommand(cmd) != null) {
						System.out.println("Ayuda para el comando " + cmd + " '" + getRegistredCommand(cmd).getUsage() + "'");
					} else {
						System.out.println("El comando '" + cmd + "' no existe");
					}
				} else {
					System.out.println("Argumentos invalidos, usa 'help <comando>'");
				}
			}
		};
    }
	public void addCommand(Command command) {
		commands.add(command);
	}
	private void executeCommand(String allCommand) {
		if (allCommand != null) {
			String[] args = allCommand.split(" ");
			if (args.length == 1) {
				if (!command(args[0].replace(" ", ""), new String[0])) {
					System.out.println("Ese comando no existe, usa 'help' para información sobre los comandos del servidor");
				}
			} else {
				if (!command(args[0].replace(" ", ""), allCommand.replace(args[0] + " ", "").split(" "))) {
					System.out.println("Ese comando no existe, usa 'help' para información sobre los comandos del servidor");
				}
			}
		}
	}
	private boolean command(String command, String[] args) {
		String allcmd = command;
		for (String arg : args) {
			allcmd = allcmd + " " + arg;
		}
		System.out.println("Running command '" + allcmd + "'");
		if (getRegistredCommand(command) != null) {
			try {
				getRegistredCommand(command).execute(args);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}
	private Command getRegistredCommand(String command) {
		for (Command cmd : commands) {
			if (cmd.getCommand().equals(command)) {
				return cmd;
			}
		}
		return null;
	}
}
