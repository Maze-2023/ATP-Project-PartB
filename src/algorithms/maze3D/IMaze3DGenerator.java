package algorithms.maze3D;

/**
 * Interface that make any class incinerate this interface to implement these functions
 */
public interface IMaze3DGenerator {

    Maze3D generate(int depth, int row, int column);
    long measureAlgorithmTimeMillis(int depth, int row, int column);

}
