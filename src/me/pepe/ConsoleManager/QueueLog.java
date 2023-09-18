package me.pepe.ConsoleManager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueLog {
    private static final int MAX_CAPACITY = 250;
    private static final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    public static void append(final String message) {
        if (queue.size() >= MAX_CAPACITY) {
            queue.clear();
        }
        queue.add(message);
    }
    public static String getNextLogEvent() {
        try {
            return queue.take();
        }  catch (InterruptedException ex) {}

        return null;
    }
}