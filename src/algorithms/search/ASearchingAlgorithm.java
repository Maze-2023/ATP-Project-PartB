package algorithms.search;

import java.io.Serializable;

/**
 * Abstract class that implements this generic part that is common to all searching algorithms
 * currently implement the number of nodes evaluated
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm, Serializable {

    protected int numOfNode;

    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfNode;
    }

    public Solution getSolution(ISearchable domain, Solution solution){
        solution.addToPath(domain.getGoal()); // start from the end to promise a legal path
        AState currentState = domain.getGoal();

        //backtrack to get solution path
        while (currentState.getParent() != null) { // run until start (parent is null)
            solution.addToPath(currentState.getParent());
            numOfNode++;
            currentState = currentState.getParent();
        }
        return solution;
    }
}
