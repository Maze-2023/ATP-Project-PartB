package algorithms.mazeGenerators;

import java.util.Random;

/**
 * An abstract class which implements IMazeGenerator
 */

public abstract class AMazeGenerator implements IMazeGenerator {

    /**
     * This function calculate the time for "generate" function of 2D maze
     * @param row of the maze
     * @param column of the maze
     * @return the total time it takes
     */
    public long measureAlgorithmTimeMillis(int row, int column)
    {
        long start =  System.currentTimeMillis();
        this.generate(row, column);
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * creates one path from start to end in maze
     * @param maze: maze to edit
     */
    public void createPath(Maze maze)
    {
        Random random = new Random();
        int startRow = 0;
        int startCol = 0;
        int endRow = maze.getRows() - 1;
        int endCol = maze.getColumn() - 1;

        // Create a single path from start to end
        while (startRow != endRow || startCol != endCol) {
            // Move towards the end goal
            if (startRow < endRow && (startCol == endCol || random.nextBoolean())) {
                startRow++;
            } else if (startCol < endCol && (startRow == endRow || random.nextBoolean())) {
                startCol++;
            } else if (startRow > endRow && (startCol == endCol || random.nextBoolean())) {
                startRow--;
            } else if (startCol > endCol && (startRow == endRow || random.nextBoolean())) {
                startCol--;
            }

            // Carve out a path in the maze
            maze.setCellInMaze(startRow,startCol,0);
        }
    }
}
