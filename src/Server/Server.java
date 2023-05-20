package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Server class represents a server that listens for client connections and applies a server strategy.
 */
public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;

    /**
     * Constructs a new Server object with the specified port, listening interval, and server strategy.
     * @param port                 The port number on which the server listens for client connections.
     * @param listeningIntervalMS  The timeout interval for accepting client connections.
     * @param strategy             The server strategy to apply for handling client requests.
     */
    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(Integer.parseInt(Configurations.getInstance().properties.getProperty("threadPoolSize")));
    }

    /**
     * Starts the server by running it in a separate thread.
     */
    public void start() {
        new Thread(this::run).start();
    }

    /**
     * Runs the server and handles client connections.
     */
    private void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + port);

            while (true) {
                synchronized ((Object) stop){
                    if(stop)
                        break;
                }
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());
                    // Handle the client request in a separate thread from the thread pool
                    Thread newThread = new Thread(() -> handleClient(clientSocket));
                    threadPool.execute(newThread);
                } catch (SocketTimeoutException e) {
                    System.out.println("Socket timeout");
                    break;
                }
            }
            // Shut down the thread pool after handling a single client connection
            threadPool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles a client connection by applying the server strategy to the client's input.
     * @param clientSocket  The socket representing the client connection.
     */
    private void handleClient(Socket clientSocket) {
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        } catch (IOException o) {
            o.printStackTrace();
        }
    }

    /**
     * Stops the server by setting the stop flag.
     */
    public void stop() {
        synchronized ((Object) stop){
            stop = true;
        }

    }
}
