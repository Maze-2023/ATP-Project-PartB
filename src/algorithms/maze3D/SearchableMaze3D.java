package algorithms.maze3D;
import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze3D implements ISearchable {

    private Maze3D maze;
    Maze3DState[][][] s_maze;
    Maze3DState start;
    Maze3DState goal;
    private ArrayList<AState> possibleStates;

    public SearchableMaze3D(Maze3D m)
    {
        if(m == null)
            return;

        this.maze = m;
        this.start = new Maze3DState(m.getStartPosition(), 0);
        this.goal = new Maze3DState(m.getGoalPosition(), 0);
        this.s_maze = new Maze3DState[m.depth][m.rows][m.columns];
        for(int k = 0; k< m.depth; k++)
            for (int i = 0; i < m.rows; i ++){
                for(int j = 0; j < m.columns; j++){
                    s_maze[k][i][j]=new Maze3DState(new Position3D(k,i,j),maze.getMap()[k][i][j]);
                }
            }
        for(int depth = 0; depth< m.depth; depth++)
            for(int row = 0; row < m.rows; row ++)
            {
                for(int col = 0; col < m.columns; col++)
                {
                        // set the neighbors list
                        for(int k = depth-1; k<=depth+1; k++)
                            for (int i = row-1; i <= row+1; i++)
                                for(int j = col-1; j <=col+1; j++)
                                {
                                    if (k >= 0 &&  k < m.depth && i >= 0 &&  i < m.rows &&  j >= 0 && j < m.columns) //in bounds
                                        {
                                            if (k == depth) // same plain
                                            {
                                                if(!(i != row && j != col)) // not diagonal
                                                    if (m.getMap()[k][i][j] == 0)
                                                        s_maze[depth][row][col].addToNeighbors(s_maze[k][i][j]);
                                            }
                                            else // different plain
                                            {
                                                if(i == row && j == col) // IN or OUT
                                                    if (m.getMap()[k][i][j] == 0)
                                                        s_maze[depth][row][col].addToNeighbors(s_maze[k][i][j]);
                                            }
                                        }
                                }
                }
            }
        setStart(s_maze[0][0][0]);
        setGoal(s_maze[0][maze.rows-1][maze.columns-1]);
    }


    public ArrayList<AState> resetPossibleStates()
    {
        this.possibleStates = new ArrayList<>();
        for(int k=0; k< maze.depth; k++)
            for (int i = 0; i < maze.rows; i ++)
                for(int j = 0; j < maze.columns; j++){
                    if(s_maze[k][i][j].getValue() == 0)
                        possibleStates.add(s_maze[k][i][j]);
                }

        return this.possibleStates;
    }

    /**
     * Set the possible states' list
     * @param state to optional solutions
     */
    public void setPossibleStates(Maze3DState state)
    {
        if(state.getValue() == 0)
            possibleStates.add(state);

    }

    @Override
    public void setStart(AState s) {
        this.start = (Maze3DState) s;
    }

    @Override
    public void setGoal(AState g) {
        this.goal = (Maze3DState) g;
    }

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
    public ArrayList<AState> getAllPossibleStates(List<AState> states)
    {
        this.possibleStates = new ArrayList<>();
        for(AState s: states){
            setPossibleStates((Maze3DState)s);
        }
        return this.possibleStates;
    }
}


