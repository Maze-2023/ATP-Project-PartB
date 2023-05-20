package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    /**
     * Applies the server strategy to generate and send a compressed maze to the client.
     * @param inFromClient  The input stream from the client.
     * @param outToClient   The output stream to the client.
     */
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            int[] input = (int[]) fromClient.readObject();

            // Create a compressed maze using the input size
            byte[] out = createCompressedMaze(input);

            // Send the compressed maze to the client
            toClient.writeObject(out);
            toClient.flush();
            fromClient.close();
            toClient.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a maze using the configured algorithm and compresses it.
     * @param size  The size of the maze to generate.
     * @return      The compressed byte array representation of the generated maze.
     */
    private byte[] createCompressedMaze(int[] size) {
        String algorithm = Configurations.getInstance().properties.getProperty("mazeGeneratingAlgorithm");
        AMazeGenerator mazeGenerator = null;

        // Determine the maze generator based on the configured algorithm
        if(algorithm.equals("MyMazeGenerator"))
            mazeGenerator = new MyMazeGenerator();
        else if(algorithm.equals("EmptyMazeGenerator"))
            mazeGenerator = new EmptyMazeGenerator();
        else if(algorithm.equals("SimpleMazeGenerator"))
            mazeGenerator = new SimpleMazeGenerator();

        assert mazeGenerator != null;

        // Generate the maze using the selected maze generator
        Maze maze = mazeGenerator.generate(size[0], size[1]);

        // Compress the maze into a byte array
        ByteArrayOutputStream compressedOutput = new ByteArrayOutputStream();
        OutputStream out = new MyCompressorOutputStream(compressedOutput);
        try {
            out.write(maze.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compressedOutput.toByteArray();
    }
}
