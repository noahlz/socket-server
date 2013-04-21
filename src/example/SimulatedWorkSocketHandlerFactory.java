package example;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Factory that returns socket handlers that simulate work being done on the server.
 */
public class SimulatedWorkSocketHandlerFactory implements SocketHandlerFactory {

    private final AtomicInteger nextId = new AtomicInteger();

    @Override
    public SocketHandler newSocketHandler(Socket socket) {
        int id = nextId.getAndIncrement();
        return new SimulatedWorkSocketHandler(id, socket);
    }
}
