package Server;

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
            Solution out = Solve(inputMaze);
            toClient.writeObject(out);
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
