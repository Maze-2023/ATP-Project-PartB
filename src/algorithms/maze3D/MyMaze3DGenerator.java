package algorithms.maze3D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * This class generate a new 3D maze and insure a path from start to goal
 */
public class MyMaze3DGenerator extends AMaze3DGenerator{

    private Maze3D maze3D;

    /**
     * Constructor
     * @param depth of the new maze
     * @param row of the new maze
     * @param column of the new maze
     * @return the new maze
     */
    @Override
    public Maze3D generate(int depth, int row, int column) {
        if(depth <=1 && row <= 1 && column <= 1) {
            depth = 1;
            row = 1;
            column = 2; // minimum default
        }
        maze3D = new Maze3D(depth, row, column);
        Maze3D.setAllMazeToWalls(maze3D);
        //set end point and neighbors of this point
        maze3D.setCellInMaze(0, row - 1, column - 1, 0);
        generateIterative(0,0,0);
        maze3D.setCellInMaze(0,row-2,column-1,0);
        return maze3D;
    }

    /**
     * this is based on DFS
     * iterative way to build a new 3D maze
     */
    public void generateIterative(int depth, int row, int column) {
        Stack<Position3D> stack = new Stack<>();
        stack.push(new Position3D(depth, row, column));
        while (!stack.isEmpty()) { // helper struct
            Position3D current = stack.pop(); // work on some position and find the neighbors
            if (maze3D.getMap()[current.getDepthIndex()][current.getRowIndex()][current.getColumnIndex()] == 1) { // its wall
                maze3D.setCellInMaze(current.getDepthIndex(), current.getRowIndex(), current.getColumnIndex(), 0); // open the wall
                ArrayList<Position3D> neighbors = getNeighbors(current.getDepthIndex(), current.getRowIndex(), current.getColumnIndex()); // get the neighbors
                Collections.shuffle(neighbors);
                for (Position3D neighbor : neighbors) { // run on the neighbors list
                    if (maze3D.getMap()[neighbor.getDepthIndex()][neighbor.getRowIndex()][neighbor.getColumnIndex()] == 1) { // its wall
                        /* took the middle between the two walls and open it */
                        maze3D.setCellInMaze((current.getDepthIndex() + neighbor.getDepthIndex()) / 2, (current.getRowIndex() + neighbor.getRowIndex()) / 2, (current.getColumnIndex() + neighbor.getColumnIndex()) / 2, 0);
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    /**
     * calculates the neighbours of the cell with one cell between as a "divider"
     * z - depth
     * x - row
     * y - column
     */
    private ArrayList<Position3D> getNeighbors(int z, int x, int y) {
        ArrayList<Position3D> neighbors = new ArrayList<>();
        if (z > 1 && maze3D.getMap()[z -2][x][y] == 1)
            neighbors.add(new Position3D(z-2, x, y));
        if (x > 1 && maze3D.getMap()[z][x-2][y] == 1)
            neighbors.add(new Position3D(z,x-2,y));
        if (y > 1 && maze3D.getMap()[z][x][y-2]== 1)
            neighbors.add(new Position3D(z, x,y - 2));
        if (z < maze3D.depth - 2 && maze3D.getMap()[z+2][x][y] == 1)
            neighbors.add(new Position3D(z + 2, x, y));
        if (x < maze3D.rows - 2 && maze3D.getMap()[z][x+2][y] == 1)
            neighbors.add(new Position3D(z, x + 2, y));
        if (y < maze3D.columns - 2 && maze3D.getMap()[z][x][y+2] == 1)
            neighbors.add(new Position3D(z, x, y + 2));
        return neighbors;
    }
}
