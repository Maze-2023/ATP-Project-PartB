package algorithms.search;

import java.util.List;

/**
 * Makes sure that each problem that is solved has these functions
 * This insures that all the searching algorithms can search the given problem
 * the possible states help to solve to problem, they are the next possible step in the solution
 */
public interface ISearchable {

     //setters
     void setStart(AState s);
     void setGoal(AState g);

     //getters
     AState getStart();
     AState getGoal();

     //methods regarding possible states
     List<AState> getAllPossibleStates(List<AState> list);
     List<AState> resetPossibleStates();
}
