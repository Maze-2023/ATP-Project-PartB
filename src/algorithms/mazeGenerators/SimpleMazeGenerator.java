package algorithms.mazeGenerators;
import java.util.*;

public class SimpleMazeGenerator extends AMazeGenerator{


    /**
     * crates a simple maze that has at least one route from start to end
     * @param rows: number of rows in maze
     * @param column: number of columns in maze
     * @return : maze built
     */
    @Override
    public Maze generate(int rows, int column) {

        if(rows <= 1 && column <= 1){
            rows = 1;
            column = 1; // minimum default
        }

        Maze maze = new Maze(rows, column);
        Maze.setAllMazeToWalls(maze);

        //set start point
        maze.setCellInMaze(0,0,0);

        //set end point
        maze.setCellInMaze(maze.getRows() -1, maze.getColumn()-1,0);

        //create a path in the maze
        createPath(maze);

        //randomize cells not in path
        randomCellsNotPath(maze);

        return maze;
    }



    /**
     * all the cells that have walls in them, this function randomly chooses to set in 0 or 1
     * @param maze: updated maze
     */
    public void randomCellsNotPath(Maze maze)
    {
        Random random = new Random();
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getColumn(); col++) {
                if(maze.getCellValue(row,col)==1)//if not in path
                    maze.setCellInMaze(row,col, random.nextInt(2));
            }
        }
    }
}
