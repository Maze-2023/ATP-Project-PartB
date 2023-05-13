package algorithms.maze3D;

/**
 * This class save the coordinates of a specific point in 3D maze
 */
public class Position3D {

    private int depth;
    private int row;
    private int colum;

    /**
     * Constructor
     * @param depth
     * @param row
     * @param column
     */
    public Position3D(int depth, int row, int column)
    {
        this.depth = depth;
        this.row = row;
        this.colum = column;
    }

    /**
     *
     * @return the depth coordinate
     */
    public int getDepthIndex(){

        return this.depth;
    }

    /**
     *
     * @return the row coordinate
     */
    public int getRowIndex()
    {

        return this.row;
    }

    /**
     *
     * @return the column coordinate
     */
    public int getColumnIndex()
    {

        return this.colum;
    }

    /**
     * toString for prints
     * @return the print of the position
     */
    @Override
    public String toString() {
        return "Position{ depth num:"+ this.getDepthIndex() +" row num:"+this.getRowIndex()+" col num:"+ this.getColumnIndex()+"}";
    }
}
