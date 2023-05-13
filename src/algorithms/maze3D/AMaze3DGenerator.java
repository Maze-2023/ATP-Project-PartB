package algorithms.maze3D;

/**
 * An abstract class which present the time cost for some algorithm to run
 */
public abstract class AMaze3DGenerator implements IMaze3DGenerator {

    /**
     * This function calculate the time for "generate" function of 3D maze
     * @param depth of the maze
     * @param row of the maze
     * @param column of the maze
     * @return the total time it takes
     */
    public long measureAlgorithmTimeMillis(int depth, int row, int column){

        long start =  System.currentTimeMillis();
        this.generate(depth, row, column);
        long end = System.currentTimeMillis();
        return end - start;
    }
}
