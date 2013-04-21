package example;

public class Main {

    public static void main(String[] args) throws Exception {

        if(args.length != 3)  {
            System.err.println("Required parameters: <port> <timeout> <SocketHandlerFactoryClassName>");
            return;
        }

        int port = Integer.parseInt(args[0]);
        int timeout = Integer.parseInt(args[1]);

        SocketHandlerFactory factory =
                (SocketHandlerFactory) Class.forName(args[2]).newInstance();

        Server server = new Server.Builder()
                                  .withTimeout(timeout)
                                  .onPort(port)
                                  .withSocketHandlerFactory(factory)
                                  .build();

        server.start();
    }

}
