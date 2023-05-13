package algorithms.maze3D;
import algorithms.search.AState;

/**
 * A class that save the position and value of state in the maze
 */
public class Maze3DState extends AState{

    private Position3D position3D;

    /**
     * Constructor
     * @param node of depth, row and column
     * @param value of the cell in maze
     */
    public Maze3DState(Position3D node, int value) {
        super(value);
        this.position3D = node;
    }

    /**
     * toString for prints
     * @return the print
     */
    public String toString() {
        return "(" + this.position3D.getDepthIndex()+','+ this.position3D.getRowIndex()+','+this.position3D.getColumnIndex() +')';
    }
}
