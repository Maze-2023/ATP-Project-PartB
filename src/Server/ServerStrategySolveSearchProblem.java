package Server;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze inputMaze = (Maze) fromClient.readObject();

            String fileName =  System.getProperty("java.io.tmpdir") + inputMaze.toString().hashCode();
            File file = new File(fileName);
            Solution solution;
            // if the maze solved before
            if(!file.exists()){
                solution = Solve(inputMaze);

                FileOutputStream outStream = new FileOutputStream(fileName);
                ObjectOutputStream fileObjectOut = new ObjectOutputStream(outStream);

                fileObjectOut.writeObject(solution);
                fileObjectOut.close();
            }
            else{
                FileInputStream inputStream = new FileInputStream(fileName);
                ObjectInputStream fileObjectIn = new ObjectInputStream(inputStream);
                solution = (Solution) fileObjectIn.readObject();
            }
            toClient.writeObject(solution);
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Solution Solve(Maze maze){
        String algorithm = Configurations.getInstance().properties.getProperty("mazeSearchingAlgorithm");
        ASearchingAlgorithm searchAlgo = null;
        if(algorithm.equals("BestFirstSearch"))
            searchAlgo = new BestFirstSearch();
        else if(algorithm.equals("BreadthFirstSearch"))
            searchAlgo = new BreadthFirstSearch();
        else if(algorithm.equals("DepthFirstSearch"))
            searchAlgo = new DepthFirstSearch();
        ISearchable searchProb = new SearchableMaze(maze);
        return searchAlgo.solve(searchProb);
    }
}
