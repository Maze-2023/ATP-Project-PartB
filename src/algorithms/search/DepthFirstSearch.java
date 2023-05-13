package algorithms.search;

import java.awt.*;
import java.util.List;
import java.util.Stack;

/**
 * DFS search algorithms
 * solves the problem using AStates
 */
public class DepthFirstSearch extends ASearchingAlgorithm {


    /**
     * DFS algorithm
     * Two functions used to set the distance and parents of states
     * @param searchProblem to solve
     * @param start pose
     */
    public void DFS(ISearchable searchProblem, AState start) {
        List<AState> possibleStates = searchProblem.resetPossibleStates();
        for (AState u : possibleStates) {
            u.setColor(Color.white);
            u.setParent(null);
        }

        Stack<AState> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            AState current = stack.pop();
            if (current.getColor() == Color.white) {
                current.setColor(Color.gray);
                List<AState> adjVertices = searchProblem.getAllPossibleStates(current.getNeighbors());
                for (AState v : adjVertices) {
                    if (v.getColor() == Color.white)
                    {
                        stack.push(v);
                        v.setParent(current);
                    }
                }
            }
            current.setColor(Color.black);
        }
    }

    @Override
    public String getName() {
        return "Depth First Search";
    }

    /**
     * Use DFS algorithm and return a solution
     *
     * @param domain the problem for solving
     * @return a new solution for this problem
     */
    @Override
    public Solution solve(ISearchable domain) {
        DFS(domain, domain.getStart());
        Solution solution = new Solution();
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

