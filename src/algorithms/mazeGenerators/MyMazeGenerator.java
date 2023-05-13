package algorithms.mazeGenerators;
import java.util.*;

/**
 * This function extends AMazeGenerator
 * Using prim algorithm
 */
public class MyMazeGenerator extends AMazeGenerator {

    /**
     * Find the nearest neighbors of a specific point
     * @param point current position
     */
    public void setNeighbors(Maze maze, ArrayList<Position> neighbors, Position point)
    {
        int countVisited = 0;
        // Loop over all non-diagonal neighbors of (i, j)
        for (int x = point.getRowIndex() - 1; x <= point.getRowIndex() + 1; x++)
            for (int y = point.getColumnIndex() - 1; y <= point.getColumnIndex() + 1; y++)
                if ( x >= 0 &&  x < maze.getRows() &&  y >= 0 && y < maze.getColumn()) //in bounds
                    if (!(x != point.getRowIndex() && y != point.getColumnIndex())) // not diagonal
                        if (maze.getCellValue(x,  y) == 0)
                            countVisited++;
        // By prim: If only one of the cells that the wall divides is visited, then:
        if (countVisited <2)
        {
            maze.setCellInMaze(point.getRowIndex(), point.getColumnIndex(), 0);
            // Loop over all non-diagonal neighbors of (i, j)
            for (int x = point.getRowIndex() - 1; x <= point.getRowIndex() + 1; x++)
                for (int y = point.getColumnIndex() - 1; y <= point.getColumnIndex() + 1; y++)
                    if (x >= 0 && x < maze.getRows() && y >= 0 && y < maze.getColumn()) //in bounds
                        if (!(x != point.getRowIndex() && y != point.getColumnIndex())) // not diagonal
                            if (maze.getCellValue(x, y) == 1)
                                neighbors.add(new Position( x,  y));
            }
    }
    /**
     * generates a maze using prim randomized algorithm
     * @param row : number of row in maze
     * @param column : number of column in maze
     * @return : return built maze
     */
    @Override
    public Maze generate(int row, int column) {

        if(row <= 1 && column <= 1){
            row = 1;
            column = 1; // minimum default
        }

        // build maze and initialize with only walls
        Maze maze = new Maze(row, column);
        Maze.setAllMazeToWalls(maze);

        //set start point
        maze.setCellInMaze(0,0,0);

        //set end point
        maze.setCellInMaze(maze.getRows() -1, maze.getColumn()-1,0);

        ArrayList<Position> neighbors = new ArrayList<>();
        neighbors.add(maze.getStartPosition());

        while (!neighbors.isEmpty()) {
            // pick current node at random
            Random random = new Random();
            Position currentP = neighbors.remove(random.nextInt(neighbors.size()));
            setNeighbors(maze, neighbors, currentP);
        }

        //create at least one path
        createPath(maze);

        return maze;
    }
}

