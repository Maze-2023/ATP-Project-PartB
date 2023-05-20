package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that gets a maze and converts it to be searchable in the generic way so that all solve
 * algorithms can solve it.
 */
public class SearchableMaze implements ISearchable, Serializable {

    //variables
     private Maze maze;
     MazeState[][] s_maze;
     MazeState start;
     MazeState goal;

     private List<AState> possibleStates;

    /**
     * Create a searchable maze with an existing maze, start and goal points
     * @param m the original maze
     */
    public SearchableMaze(Maze m)
    {
        if(m == null)
            return;

        this.maze = m;
        this.start = new MazeState(m.getStartPosition(), 0);
        this.goal = new MazeState(m.getGoalPosition(), 0);

        //searchable maze built base on the original maze
        this.s_maze = new MazeState[m.getRows()][m.getColumn()];

        //convert all cells to maze state
        for (int i = 0; i < m.getRows(); i ++){
            for(int j = 0; j < m.getColumn(); j++){
                s_maze[i][j]=new MazeState(new Position(i,j),maze.getCellValue(i,j));
            }
        }

        //set maze sate neighbours in s_maze
        for(int row = 0; row < m.getRows(); row ++)
        {
            for(int col = 0; col < m.getColumn(); col++)
            {
                // set the neighbors list
                for (int i = -1; i <= 1; i++)
                    for(int j = -1; j <=1; j++)
                    {
                        //check bounds
                        if(0 <= (row + i) && (row + i) < m.getRows() && 0 <= (col + j) && (col + j) < m.getColumn())
                            if(!(i == 0 && j == 0)) // check if not the same maze state
                                if(s_maze[row + i][col + j].value == 0) //path
                                    s_maze[row][col].addToNeighbors(s_maze[row + i][col + j]);
                    }
            }
        }

        //check that neighbours are
        checkNeighbours();

        //set start and goal state in s_maze
        setStart(s_maze[0][0]);
        setGoal(s_maze[maze.getRows()-1][maze.getColumn()-1]);
    }

    /**
     * check if the diagonals are valid - that there is an 'R' route as well as a diagonal one
     * if not then removes from neighbours list
     */
    public void checkNeighbours()
    {
        for(int row = 0; row < this.maze.getRows(); row ++)
        {
            for(int col = 0; col < this.maze.getColumn(); col++)
            {
                //check the neighbors list only diagonals
                for (int i = -1; i <= 1; i++)
                {
                    for(int j = -1; j <=1; j++)
                    {
                        //check bounds
                        if(0 <= (row + i) && (row + i) < this.maze.getRows() && 0 <= (col + j) && (col + j) < this.maze.getColumn())
                            //if diagonal to position
                            if(row != row+i && col != col +j)
                            {
                                //if there is no other path then remove from neighbours
                                if(!checkCommonNeighbour(this.s_maze[row][col],this.s_maze[row+i][col+j]))
                                    s_maze[row][col].removeFromNeighbors(s_maze[row + i][col + j]);
                            }
                    }
                }
            }
        }
    }

    /**
     * helper function that checks if there is a path between two cells that is not only diagonal
     * @param first : first cell
     * @param second : second cell
     * @return : true if there is a third cell that connects them, in other words, another valid path
     */
    public boolean checkCommonNeighbour(MazeState first, MazeState second)
    {
        //goes over the list of the neighbours of both position and compares them
        for (int i =0; i<first.neighbors.size();i++)
        {
            for (int j=0; j<second.neighbors.size(); j++)
            {
                if(first.neighbors.get(i)==second.neighbors.get(j))
                    return true;
            }
        }
        return false;
    }

    /**
     * Possible Sates are paths only
     * @return List of all opposable AStates that can be in the solution
     */
    public List<AState> resetPossibleStates()
    {
        this.possibleStates = new ArrayList<>();
        for (int i = 0; i < maze.getRows(); i ++){
            for(int j = 0; j < maze.getColumn(); j++){
                if(s_maze[i][j].value == 0)
                    possibleStates.add(s_maze[i][j]);
            }
        }
        return this.possibleStates;
    }

    /**
     * Set the possible states' list
     * @param state to optional solutions
     */
    public void setPossibleStates(MazeState state)
    {
        if(state.getValue() == 0){
            MazeState parent = (MazeState)(state.getParent());
            //set the cost of the state
            //if diagonal
            if(parent != null && Math.abs(state.getPosition().getRowIndex() - parent.getPosition().getRowIndex()) == 1 && Math.abs(state.getPosition().getColumnIndex() - parent.getPosition().getColumnIndex()) == 1)
                state.setCost(15);
            else
                state.setCost(10);
            possibleStates.add(state);
        }
    }

    //setters
    @Override
    public void setStart(AState s) {
        this.start = (MazeState) s;
    }

    @Override
    public void setGoal(AState g) {
        this.goal = (MazeState) g;
    }

    //getters
    @Override
    public AState getStart() {
        return this.start;
    }

    @Override
    public AState getGoal() {
        return this.goal;
    }

    /**
     * @return a list of states which create a legal solution
     */
    @Override
    public List<AState> getAllPossibleStates(List<AState> states)
    {
        this.possibleStates = new ArrayList<>();
        for(AState s: states){
            setPossibleStates((MazeState)s);
        }
        return this.possibleStates;
    }
}
