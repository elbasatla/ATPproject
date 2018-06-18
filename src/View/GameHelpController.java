package View;

import ViewModel.MyViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameHelpController {

    private MyViewModel viewModel;
    @FXML
    public Image keyBoardMoveImage;
    public ImageView keyBoardMoveImageView;
    public Image mouseDragImage;
    public ImageView mouseDragImageView;
    public Image keyBoardZoomImage;
    public ImageView keyBoardZoomImageView;
    public Image mouseZoomImage;
    public ImageView mouseZoomImageView;

    public AnchorPane mainPane;

    public VBox moveAndZoomVbox;
    public HBox movmentHbox;
    public HBox zoomHbox;




    public void setViewModel(MyViewModel viewModel) {
        this.viewModel = viewModel;

    }


    public void loadImages(){
     try {
     keyBoardMoveImage = new Image(new FileInputStream("resources/Images/keyBoardMoves.jpg"));
     keyBoardMoveImageView = new ImageView(keyBoardMoveImage);
     mouseDragImage = new Image(new FileInputStream("resources/Images/mouseDrag.jpg"));
     mouseDragImageView = new ImageView(mouseDragImage);
     keyBoardZoomImage = new Image(new FileInputStream("resources/Images/keyBoardZoom.jpg"));
     keyBoardMoveImageView = new ImageView(keyBoardZoomImage);
     mouseZoomImage = new Image(new FileInputStream("resources/Images/mouseZoom.jpg"));
     mouseDragImageView = new ImageView(mouseZoomImage);
     } catch (FileNotFoundException e) {
     e.printStackTrace();
     }

    }



    public void handleHelpGame(ActionEvent action){


        // handleQuit();

    }

}
