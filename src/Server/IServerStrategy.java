package Server;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * The IServerStrategy interface represents a strategy for handling client requests in a server.
 * Implementations of this interface define how to process incoming data from the client and
 * provide a response.
 */
public interface IServerStrategy {

    /**
     * Applies the server strategy to process client requests.
     *
     * @param inFromClient  The input stream for receiving data from the client.
     * @param outToClient   The output stream for sending data to the client.
     */
    void applyStrategy(InputStream inFromClient, OutputStream outToClient);
}
