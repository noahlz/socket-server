package com.github.noahlz.sockets;

import java.net.Socket;

public interface SocketHandlerFactory {

    public SocketHandler newSocketHandler(Socket socket);

}
