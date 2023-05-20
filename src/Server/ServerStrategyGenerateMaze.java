package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            int[] input = (int[]) fromClient.readObject();
            byte[] out = createCompressedMaze(input);
            toClient.writeObject(out);
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private byte[] createCompressedMaze(int[] size) {
        String algorithm = Configurations.getInstance().properties.getProperty("mazeGeneratingAlgorithm");
        AMazeGenerator mazeGenerator = null;
        if(algorithm.equals("MyMazeGenerator"))
            mazeGenerator = new MyMazeGenerator();
        else if(algorithm.equals("EmptyMazeGenerator"))
            mazeGenerator = new EmptyMazeGenerator();
        else if(algorithm.equals("SimpleMazeGenerator"))
            mazeGenerator = new SimpleMazeGenerator();

        assert mazeGenerator != null;
        Maze maze = mazeGenerator.generate(size[0], size[1]);
        ByteArrayOutputStream compressedOutput = new ByteArrayOutputStream();
        OutputStream out = new MyCompressorOutputStream(compressedOutput);
        try {
            out.write(maze.toByteArray());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return compressedOutput.toByteArray();

    }
}
