package algorithms.mazeGenerators;

/**
 * Interface which force to implement "generate" and "measureAlgorithmTimeMillis" for any inherited class
 */
public interface IMazeGenerator {

    Maze generate(int rows, int column);
    long measureAlgorithmTimeMillis(int row, int column);
}
