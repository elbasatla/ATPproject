package View;

//import StageUtills.Holder;
import StageUtills.StageHolder;
import ViewModel.MyViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class GameLoadController {


    private MyViewModel viewModel;

    @FXML
    public Button btn_load_file;
    public TextField text_fleld_file_name;

    public void setViewModel(MyViewModel viewModel){
        this.viewModel = viewModel;
    }

    @FXML
    public void handleLoadGame(ActionEvent action){

        this.viewModel.filePathToLoad.bind(text_fleld_file_name.textProperty());
        btn_load_file.setDisable(true);
        if(!this.viewModel.loadGame()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
        System.out.println(this.viewModel.loadGame());
        btn_load_file.setDisable(false);
        handleQuit();

    }

    public void handleQuit(){
        Stage current = (Stage)StageHolder.getInstance().getCurrent();
        current.close();
        StageHolder.getInstance().deleteObject(1);

    }

}

