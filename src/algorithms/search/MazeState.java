package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.io.Serializable;

/**
 * A specific state of the maze, holds its position
 */
public class MazeState extends AState {

    private Position position;

    //constructor
    public MazeState(Position position, int value)
    {
        super(value);
        this.position = position;
    }

    //getter
    public Position getPosition()
    {
        return this.position;
    }

    @Override
    public String toString() {
        return "(" + this.position.getRowIndex()+','+this.position.getColumnIndex() +')';
    }
}
