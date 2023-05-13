package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * implements the BFS search algorithm
 * only difference is the Priority Queue
 */
public class BestFirstSearch extends BreadthFirstSearch {

    //constructor
    public BestFirstSearch(){
        Q = new PriorityQueue<>(Comparator.comparingInt(AState::getCost).reversed());
    }

    @Override
    public String getName() {
        return "Best First Search";
    }

}