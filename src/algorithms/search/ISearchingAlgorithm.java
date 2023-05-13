package algorithms.search;

/**
 * Interface that ensures all search algorithm to implement these methods
 */
public interface ISearchingAlgorithm {

    String getName();
    int getNumberOfNodesEvaluated();
    Solution solve(ISearchable domain);
}
