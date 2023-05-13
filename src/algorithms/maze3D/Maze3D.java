package algorithms.maze3D;

/**
 * 3D Maze
 * like in 2D, the 3D maze had frame, start and end point.
 */
public class Maze3D {

    private int[][][] frame;
    public int depth;
    public int rows;
    public int columns;
    private Position3D startPoint;
    private Position3D endPoint;

    /**
     * Constructor
     * @param depth of new maze
     * @param row of new maze
     * @param column of new maze
     * Initialize the start (0,0,0) and end point (0,row-1,column-1)
     */
    public Maze3D(int depth, int row, int column)
    {
        this.frame = new int[depth][row][column];
        this.rows = row;
        this.columns = column;
        this.depth = depth;
        startPoint = new Position3D(0,0,0);
        endPoint = new Position3D(0,row - 1 ,column -1);
    }

    /**
     * function that makes all maze to walls
     * @param maze to transform all the cells to walls (1)
     */
    public static void setAllMazeToWalls(Maze3D maze)
    {
        for(int k=0; k<maze.depth; k++)
        {
            for(int i=0; i<maze.rows; i++)
            {
                for(int j=0; j<maze.columns; j++)
                {
                    maze.frame[k][i][j] = 1;
                }
            }
        }

    }

    /**
     * function that set a cell in maze to 0 or 1 (val)
     * @param depth of the maze
     * @param row of the maze
     * @param colum of the maze
     * @param val to set
     */
    public void setCellInMaze(int depth, int row, int colum, int val)
    {
        if(0 <= depth && depth <= this.depth && 0 <= row && row < this.rows && 0 <= colum && colum < this.columns)
            this.frame[depth][row][colum] = val;
    }

    /**
     *
     * @return the start position
     */
    public Position3D getStartPosition()
    {

        return this.startPoint;
    }

    /**
     *
     * @return the goal position
     */
    public Position3D getGoalPosition(){

        return this.endPoint;
    }


    /**
     * function that print the maze by the required format
     */
    public void print() {
        for(int k=0; k<depth; k++)
        {
            System.out.println("[");
            for (int i = 0; i < rows; i++) {
                System.out.print("\t[");
                for (int j = 0; j < columns; j++) {

                    if (k == 0 && i == 0 && j == 0) {
                        System.out.print("\033[1;93mS\033[0m, "); // 'S' represents the entry point
                    } else if (k== 0 && i == rows-1 && j == columns-1) {
                        System.out.print("\033[1;92mE\033[0m"); // 'E' represents the exit point
                    } else if (j == columns - 1) {
                        System.out.print(this.frame[k][i][j]);
                    }
                    else
                        System.out.print(this.frame[k][i][j]+", ");
                }
                System.out.println("],");
            }
            if(k == depth - 1)
                System.out.println("]");
            else
                System.out.println("],");
        }

    }

    /**
     *
     * @return the frame of the maze
     */
    public int[][][] getMap(){return this.frame;}
}
