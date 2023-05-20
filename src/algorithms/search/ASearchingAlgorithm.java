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

}
