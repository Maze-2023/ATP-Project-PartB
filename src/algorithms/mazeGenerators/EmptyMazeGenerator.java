package algorithms.mazeGenerators;

/**
 * This class extends AMazeGenerator
 * Implement "generate" for an empty maze
 */
public class EmptyMazeGenerator extends AMazeGenerator{


    /**
     * This function build an empty maze
     * @param rows of the maze
     * @param column of the maze
     * @return a new maze
     */
    @Override
    public Maze generate(int rows, int column)
    {
        if(rows <= 1 && column <= 1) {
            rows = 1;
            column = 1; // minimum default
        }
        return new Maze(rows, column);
    }
}
