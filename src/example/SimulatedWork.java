package example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Prints dots!
 */
class SimulatedWork {

    private final int requestId;
    private final OutputStream out;

    SimulatedWork(int requestId, OutputStream out) {
        this.requestId = requestId;
        this.out = out;
    }

    void doStuff() throws IOException {
        Log.info("(Request #{0}) Writing response to {1}", requestId, out);
        sendResponse();
    }

    private void sendResponse() throws IOException {
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(out));
        output.write("Received your command! Processing...");
        output.flush();
        printDots(output);
        output.newLine();
        output.write("OK");
        output.newLine();
        output.flush();
    }


    private void printDots(BufferedWriter out) throws IOException {
        Random random = new Random();
        int count = random.nextInt(10) + 1;
        for(int i = 0; i < count; i++) {
            out.write(".");
            out.flush();
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1L));
            } catch (InterruptedException e) {
                Log.error("(Request #{0}) Work interrupted! ", e, requestId);
            }
        }

    }
}
