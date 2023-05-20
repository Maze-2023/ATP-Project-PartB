package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {

    /**
     * Applies the server strategy to solve a search problem based on the received maze and send the solution back to the client.
     * @param inFromClient  The input stream from the client.
     * @param outToClient   The output stream to the client.
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze inputMaze = (Maze) fromClient.readObject();

            // Generate a unique filename based on the hash code of the input maze
            String fileName =  System.getProperty("java.io.tmpdir") + inputMaze.toString().hashCode();
            File file = new File(fileName);
            Solution solution;

            // If the maze has not been solved before
            if (!file.exists()) {
                solution = Solve(inputMaze);

                FileOutputStream outStream = new FileOutputStream(fileName);
                ObjectOutputStream fileObjectOut = new ObjectOutputStream(outStream);

                fileObjectOut.writeObject(solution);
                fileObjectOut.close();
            } else { // If the maze has been solved before, retrieve the solution from the file
                FileInputStream inputStream = new FileInputStream(fileName);
                ObjectInputStream fileObjectIn = new ObjectInputStream(inputStream);
                solution = (Solution) fileObjectIn.readObject();
            }

            // Send the solution to the client
            toClient.writeObject(solution);
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Solves the maze using the configured searching algorithm
     * @param maze  The maze to be solved.
     * @return      The solution to the maze.
     */
    private Solution Solve(Maze maze) {
        String algorithm = Configurations.getInstance().properties.getProperty("mazeSearchingAlgorithm");
        ASearchingAlgorithm searchAlgo = null;

        // Determine the searching algorithm based on the configured algorithm
        if (algorithm.equals("BestFirstSearch"))
            searchAlgo = new BestFirstSearch();
        else if (algorithm.equals("BreadthFirstSearch"))
            searchAlgo = new BreadthFirstSearch();
        else if (algorithm.equals("DepthFirstSearch"))
            searchAlgo = new DepthFirstSearch();

        ISearchable searchProb = new SearchableMaze(maze);
        return searchAlgo.solve(searchProb);
    }
}
