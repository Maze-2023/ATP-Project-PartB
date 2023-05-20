package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.BestFirstSearch;
import algorithms.search.ISearchable;
import algorithms.search.SearchableMaze;
import algorithms.search.Solution;

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
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        ISearchable searchProb = new SearchableMaze(maze);
        return bestFirstSearch.solve(searchProb);
    }
}
