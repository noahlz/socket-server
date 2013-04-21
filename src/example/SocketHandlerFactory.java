package example;

import java.net.Socket;

public interface SocketHandlerFactory {

    public SocketHandler newSocketHandler(Socket socket);

}
