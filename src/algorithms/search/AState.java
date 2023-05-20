package algorithms.search;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Class that represents a state in the solution path of search
 * makes each state hold it neighbours which represents possible routes in the solution
 * Includes the parent state, color(white, grey or black), value and cost.
 */
public abstract class AState implements Serializable {

    //variables
    protected AState parent;
    protected Color color;
    protected int value;
    protected int cost;
    protected LinkedList<AState> neighbors;

    //constructor
    public AState(int value)
    {
        this.value = value;
        this.cost = 0;
        neighbors = new LinkedList<>();
    }

    //get methods
    public int getValue(){return this.value;}
    public AState getParent(){return this.parent;}
    public Color getColor(){return this.color;}
    public LinkedList<AState> getNeighbors(){return this.neighbors;}
    public int getCost(){return this.cost;}

    //set methods
    public void setColor(Color c)
    {
        this.color = c;
    }
    public void setParent(AState p)
    {
        this.parent = p;
    }
    public void setCost(int cost)
    {
        this.cost = cost;
    }

    //methods regarding neighbours
    public void addToNeighbors(AState state)
    {
        neighbors.add(state);
    }
    public void removeFromNeighbors(AState state)
    {
        neighbors.remove(state);
    }
}
