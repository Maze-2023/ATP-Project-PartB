package algorithms.search;

import java.util.ArrayList;

/**
 * Hold the solution solves by a searching algorithm of a searchable problem
 */
public class Solution {

    //variables
    private ArrayList<AState> solutionPath;

    //constructor
    public Solution()
    {
        this.solutionPath = new ArrayList<>();
    }

    //get method
    public ArrayList<AState> getSolutionPath()
    {
        return solutionPath;
    }

    //add state to path
    public void addToPath(AState state){solutionPath.add(0,state);}
}
