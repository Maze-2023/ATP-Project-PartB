package Client;

import java.io.InputStream;
import java.io.OutputStream;

public interface IClientStrategy {

    /**
     * Defines the client strategy to be executed when communicating with the server.
     *
     * @param inFromServer  the input stream to receive data from the server
     * @param outToServer   the output stream to send data to the server
     */
    void clientStrategy(InputStream inFromServer, OutputStream outToServer);
}
