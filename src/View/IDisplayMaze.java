package View;

import algorithms.search.Solution;

public interface IDisplayMaze {

     void setMaze(int[][] maze);
     void redraw();
     void setCharacterPosition(int row , int col);
     void setSolution(Solution solution);
     void setEndPosition(int row , int col);

}
