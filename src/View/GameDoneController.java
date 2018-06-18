package View;

import StageUtills.Sounds;
import StageUtills.StageHolder;
import StageUtills.IHolder;
import ViewModel.MyViewModel;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class GameDoneController {

    private MyViewModel viewModel;
    @FXML
    public javafx.scene.control.Button btn_newGame , btn_quit;

    public void setViewModel(MyViewModel viewModel){
        this.viewModel = viewModel;
    }

    public void handleNewGame() {
        IHolder holder = StageHolder.getInstance();
        Stage currentStage = (Stage) holder.getCurrent();
        holder.deleteObject(1);
        currentStage.close();
        Sounds.getInstance().playBackgroundMusic();
    }

    public void handleQuit(){
        this.viewModel.shutDownServers();
        System.exit(0);
    }
}
