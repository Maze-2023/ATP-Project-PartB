package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Client class - used to connect with the server
 */
public class Client implements IClientStrategy {

    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;

    /**
     * Constructs a new Client object with the specified server IP, server port, and client strategy.
     *
     * @param serverIP   the IP address of the server to connect to
     * @param serverPort the port number of the server to connect to
     * @param strategy   the client strategy to be executed when communicating with the server
     */
    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }
    ///
    /**
     * Establishes a connection with the server and executes the client strategy.
     */
    public void communicateWithServer() {
        try (Socket serverSocket = new Socket(serverIP, serverPort)) {
            System.out.println("Connected to server - IP = " + serverIP.toString() + ", Port = " + serverPort);
            strategy.clientStrategy(serverSocket.getInputStream(), serverSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //comment
    }

    /**
     * Executes the client strategy by passing the input and output streams of the server socket.
     *
     * @param inFromServer  the input stream to receive data from the server
     * @param outToServer   the output stream to send data to the server
     */
    @Override
    public void clientStrategy(InputStream inFromServer, OutputStream outToServer) {
        // Implementation of the client strategy is in test package
    }
}

