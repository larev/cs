package ru.larev.cs.client;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8888;
        if (args.length > 1) host = args[0];
        if (args.length > 2) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException ignored) {
            }
        }
        Client.connect(host, port);
    }
}
