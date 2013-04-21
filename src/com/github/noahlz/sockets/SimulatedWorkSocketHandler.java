package com.github.noahlz.sockets;

import java.io.*;
import java.net.Socket;

class SimulatedWorkSocketHandler implements SocketHandler {

    private final int id;
    private final Socket clientSocket;

    SimulatedWorkSocketHandler(int id, Socket clientSocket) {
        this.id = id;
        this.clientSocket = clientSocket;
    }

    @Override
    public void handleSocket() {

        Log.info("(Request #{0}) STARTING", id);

        InputStream in = null;
        OutputStream out = null;

        try {
            in = clientSocket.getInputStream();
            out = clientSocket.getOutputStream();

            Log.info("(Request #{0}) Reading output from {1}", id, in);
            receiveMessage(in);

            new SimulatedWork(id, out).doStuff();

        } catch (IOException ex) {
            Log.error("(Request #{0}) ERROR: ", ex);
        } finally {
            closeQuietly(in);
            closeQuietly(out);
            Log.info("(Request #{0}) FINISHED", id);
        }
    }

    private void receiveMessage(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        Log.info("(Request #{0}) Received message: {1}", id, line);
    }

    private void closeQuietly(Closeable closeable) {
        if(closeable != null) try {
            closeable.close();
        } catch (IOException e) {
            Log.error("(Request #{0}) FAILED TO CLOSE STREAM: ", e, id);
        } finally { /* we tried! */ }
    }

}
