package ViewModel;

public interface IMazeViewModel {

    void generateMaze(int rowsNum , int colsNum);
    int[][] getMze();
    int getCharRowPosition();
    int getCharColPosition();
}
