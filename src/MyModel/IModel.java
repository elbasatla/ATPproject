package MyModel;

import algorithms.search.AState;
import algorithms.search.Solution;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public interface IModel {

    /**
     * generate a maze
     * @param rows the number of rows
     * @param cols the number of columns
     */
    void generateMaze(int rows , int cols);

    /**
     *
     * @return the maze matrix
     */
    int[][] getMaze();

    /**
     *
     * @return the current row index of the character
     */
    int getCharRowPosition();

    /**
     *
     * @return the current column index of the character
     */
    int getCharColPosition();

    void startServers();

    int moveCharacter(KeyCode movement);

    void solveMaze();

    Solution getSolution();

    int getRowEndIndex();

    int getColEndIndex();

    void shutDownServers();

    boolean possibleMove(int rowDest , int colDest);

    void setCharRowPos(int row);

    void setCharColPos(int col);

}
