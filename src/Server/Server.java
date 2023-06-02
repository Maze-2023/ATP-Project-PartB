package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private final int listeningIntervalMS;
    private final IServerStrategy strategy;
    private final ExecutorService threadPool;
    private volatile boolean stop;

    /**
     * Constructs a new Server object with the specified port, listening interval, and server strategy.
     *
     * @param port                 The port number on which the server listens for client connections.
     * @param listeningIntervalMS  The timeout interval for accepting client connections.
     * @param strategy             The server strategy to apply for handling client requests.
     */
    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        int threadPoolSize = Integer.parseInt(Configurations.getInstance().properties.getProperty("threadPoolSize"));
        this.threadPool = Executors.newFixedThreadPool(threadPoolSize);
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
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + port);
            while (!stop) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted: " + clientSocket.toString());
                    // Handle the client request in a separate thread from the thread pool
                    threadPool.execute(() -> handleClient(clientSocket));
                } catch (SocketTimeoutException e) {
                    System.out.println("Socket timeout");
                    break;
                }
            }
            threadPool.shutdownNow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles a client connection by applying the server strategy to the client's input.
     *
     * @param clientSocket  The socket representing the client connection.
     */
    private void handleClient(Socket clientSocket) {
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stops the server by setting the stop flag and shutting down the thread pool.
     */
    public void stop() {
        stop = true;
    }
}
