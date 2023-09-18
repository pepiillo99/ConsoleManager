package me.pepe.ConsoleManager;

import jline.console.ConsoleReader;
import org.fusesource.jansi.Ansi;

import java.io.IOException;
import java.io.OutputStream;

class TerminalConsoleWriterThread implements Runnable {
    private ConsoleReader reader;
    private OutputStream output;
    public TerminalConsoleWriterThread(OutputStream output, ConsoleReader reader) {
        this.output = output;
        this.reader = reader;
    }
    @Override
    public void run() {
        try {
            String message;
            while (true) {
                message = QueueLog.getNextLogEvent();
                if (message == null) continue;
                reader.print(Ansi.ansi().eraseLine(Ansi.Erase.ALL).toString() + ConsoleReader.RESET_LINE);
                reader.flush();
                output.write((message + System.lineSeparator()).getBytes());
                output.flush();
                try {
                    reader.drawLine();
                } catch (Throwable ex) {
                    reader.getCursorBuffer().clear();
                }
                reader.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
