package example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final int port;
    private final int timeout;
    private final SocketHandlerFactory socketHandlerFactory;
    private final ExecutorService handlerService = Executors.newCachedThreadPool();

    private Server(int port, int timeout, SocketHandlerFactory socketHandlerFactory) {
        this.port = port;
        this.timeout = timeout;
        this.socketHandlerFactory = socketHandlerFactory;
    }

    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(timeout);

        Log.info("Waiting for connections on port "
                 + port + " for " + timeout + " milliseconds (Ctrl-C to exit)");

        while(true) {
            try {

                Socket clientSocket = serverSocket.accept();
                final SocketHandler handler = socketHandlerFactory.newSocketHandler(clientSocket);

                handlerService.submit(new Runnable() {
                    public void run() {
                        handler.handleSocket();
                    }});

            } catch (SocketTimeoutException timeout) {
                Log.info("Timeout: " + timeout.getMessage());
            } catch (IOException ex) {
                Log.error("** ERROR: ", ex);
            }
        }
    }


    public static class Builder {
        private Integer port;
        private Integer timeout;
        private SocketHandlerFactory socketHandlerFactory;

        public Builder withTimeout(int timeout){
            this.timeout = timeout;
            return this;
        }

        public Builder onPort(int port) {
            this.port = port;
            return this;
        }

        public Builder withSocketHandlerFactory(SocketHandlerFactory socketHandlerFactory) {
            this.socketHandlerFactory = socketHandlerFactory;
            return this;
        }

        public Server build() {
            if(port == null || timeout == null) {
                throw new IllegalStateException("port and timeout do not have defaults");
            }
            return new Server(port, timeout, socketHandlerFactory);
        }

    }

}
