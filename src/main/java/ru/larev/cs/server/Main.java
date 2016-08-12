package ru.larev.cs.server;


/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
public class Main {
    public static void main(String[] args) throws Exception {
        int port = 8888;
        if (args.length > 0)
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {
            }
        new Server(port).bind();
    }
}
