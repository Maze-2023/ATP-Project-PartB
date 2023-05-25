package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Hold the solution solves by a searching algorithm of a searchable problem
 */
public class Solution implements Serializable {

    //variables
    private final ArrayList<AState> solutionPath;


    //constructor
    public Solution()
    {
        this.solutionPath = new ArrayList<>();
    }

    public Solution(Solution toCopy){
        this.solutionPath = toCopy.solutionPath;
    }

    //get method
    public ArrayList<AState> getSolutionPath()
    {
        return solutionPath;
    }

    //add state to path
    public void addToPath(AState state){solutionPath.add(0,state);}
}
