package View;

import ViewModel.MyViewModel;
import javafx.scene.Scene;

public interface IVIew {

    void setViewModel(MyViewModel model);
    void setResizeEvent(Scene scene);
    void displayMaze(int[][] maze);
}
