package View;

import IO.MyCompressorOutputStream;
//import StageUtills.Holder;
import StageUtills.Sounds;
import StageUtills.StageHolder;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.Maze;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public class GameSaveController {



    private MyViewModel viewModel;

    @FXML
    public Button btn_save_as;
    public TextField lbl_file_name_area;


    public void setViewModel(MyViewModel viewModel){
        this.viewModel = viewModel;
        btn_save_as.setDisable(true);
    }



    @FXML
    public void handleSaveGame(ActionEvent mouseClick) {

        this.viewModel.filePathToSave.bind(lbl_file_name_area.textProperty());
        File saveFile = new File(System.getProperty("user.home")+"\\MazeRunnerGameSaves\\"+this.viewModel.filePathToSave.getValue()+".Maze");
        Alert overWriteAlert = new Alert(Alert.AlertType.CONFIRMATION, "are you sure to over write file: " + saveFile.getAbsolutePath() + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        overWriteAlert.setHeaderText("Confrim over writing");
        if(saveFile.exists()){
            overWriteAlert.showAndWait();

            if (!(overWriteAlert.getResult() == ButtonType.YES)) {
                overWriteAlert.close();
                return;
            }
        }
        else{//game saved . pop "game saved" and go back

        }
        btn_save_as.setDisable(true);
        this.viewModel.saveGame();
        Sounds.getInstance().playSaveSound();
        btn_save_as.setDisable(false);
        handleQuit();
    }

    public void handleQuit(){
        Stage current = (Stage)StageHolder.getInstance().getCurrent();
        current.close();
        StageHolder.getInstance().deleteObject(1);


    }

    public void enableSaveButton(){btn_save_as.setDisable(false);}


}
