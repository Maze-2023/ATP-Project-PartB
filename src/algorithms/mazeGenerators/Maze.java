package algorithms.mazeGenerators;

/**
 * Class 2D Maze
 * every maze had frame (the map), rows, columns, start and end point.
 */
public class Maze {

    private int[][] frame;
    private int rows;
    private int column;

    private Position startPoint;
    private Position endPoint;

    /**
     * Constructor
     * @param rows of the new maze
     * @param column of the new maze
     */
    public Maze(int rows, int column)
    {
        this.frame = new int[rows][column]; // default with 0
        this.rows = rows;
        this.column = column;
        this.startPoint = new Position(0,0);
        if(rows == 0 && column == 0)
            this.endPoint=startPoint;
        else
            this.endPoint = new Position(rows - 1, column - 1);
    }

    /**
     * Constructor from byte array to maze
     * @param b_maze of the maze to build
     */
    public Maze(byte[]b_maze)
    {
        this.rows = b_maze[0];
        this.column = b_maze[1];
        this.frame=new int[rows][column];
        this.startPoint = new Position(0,0);
        if(rows == 0 && column == 0)
            this.endPoint=startPoint;
        else
            this.endPoint = new Position(rows - 1, column - 1);

        //change frame
        int k = 2;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                this.frame[i][j] = b_maze[k++];
            }
        }
    }

    /**
     * function that makes all maze to walls
     * @param maze to transform all the cells to walls (1)
     */
    public static void setAllMazeToWalls(Maze maze)
    {
        for(int i=0; i<maze.rows; i++)
        {
            for(int j = 0; j<maze.column; j++)
            {
                maze.frame[i][j] = 1;
            }
        }
    }

    /**
     * function that set a cell in maze to 0 or 1 (val)
     * @param row of the maze
     * @param colum of the maze
     * @param val to set
     */
    public void setCellInMaze(int row, int colum, int val)
    {
        if(0 <= row && row < this.rows && 0 <= colum && colum < this.column)
            this.frame[row][colum] = val;
    }

    // getters
    public int[][] getFrame() {return frame;}

    public int getRows() {
        return rows;
    }

    public int getColumn() {
        return column;
    }

    public int getCellValue(int row, int colum)
    {
        if(0 <= row && row < this.rows && 0 <= colum && colum < this.column)
            return this.frame[row][colum];
        else
            return -1;
    }

    public Position getStartPosition() {return startPoint;}

    public Position getGoalPosition() {return endPoint;}

    /**
     * Print function by the required format
     */
    public void print(){
        for (int i = 0; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j < getColumn(); j++) {

                if (i == 0 && j == 0) {
                    if(i == getColumn() - 1)
                        System.out.print("\033[1;93mS\033[0m");
                    else
                        System.out.print("\033[1;93mS\033[0m, "); // 'S' represents the entry point
                } else if (i == rows-1 && j == column -1) {
                    System.out.print("\033[1;92mE\033[0m"); // 'E' represents the exit point
                } else if (j == getColumn() - 1) {
                    System.out.print(this.frame[i][j]);
                }
                else
                    System.out.print(this.frame[i][j]+", ");
            }
            System.out.println("]");
        }
    }

    /**
     * the maze format:
     * [0] - rows
     * [1] - columns
     * no need to save the start and end point because they are the same.
     * [2-n] - maze itself
     * @return
     */
    public byte[] toByteArray()
    {
        byte[] b_maze = new byte[rows*column+2];
        b_maze[0]= (byte) rows;
        b_maze[1]= (byte) column;

        int k =2;
        //put maze into the array
        for(int i = 0; i<rows;i++)
            for (int j = 0; j<column;j++)
            {
                b_maze[k++]= (byte) this.getCellValue(i,j);
            }
        return b_maze;
    }
}
