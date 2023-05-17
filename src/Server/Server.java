package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int port;
    private int listeningIntervalMS;
    private IServerStrategy strategy;
    private volatile boolean stop;
    private ExecutorService threadPool;

    public Server(int port, int listeningIntervalMS, IServerStrategy strategy) {
        this.port = port;
        this.listeningIntervalMS = listeningIntervalMS;
        this.strategy = strategy;
        this.threadPool = Executors.newFixedThreadPool(10);
    }

    public void start()
    {
        new Thread(this::run).start();

    }

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningIntervalMS);
            System.out.println("Starting server at port = " + port);

            while(!stop){
                try{
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client accepted" + clientSocket.toString());
                    threadPool.execute(new Thread(() -> handleClient(clientSocket)));
                }
                catch (SocketTimeoutException e){
                    System.out.println("Socket timeout");
                }
                threadPool.shutdown();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket){
        try {
            strategy.applyStrategy(clientSocket.getInputStream(), clientSocket.getOutputStream());
            clientSocket.close();
        }
        catch (IOException o){
            o.printStackTrace();
        }
    }


    public void stop(){
        stop = true;
    }
}
