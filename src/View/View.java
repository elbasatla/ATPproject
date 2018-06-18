package View;

import StageUtills.Sounds;
import StageUtills.StageHolder;
import StageUtills.IHolder;
import ViewModel.MyViewModel;
import Work.DoWork;
import algorithms.search.Solution;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class View implements Observer, IVIew, Initializable {


    private int timePassedSoFar ;
    private Thread timeThread = new Thread(() -> updateTime());
    private Solution solution;
    private MediaPlayer btn_click_sound , background_music , game_music;
    /**config**/
    private String configField;
    private String configFieldNewValue;


    @FXML
    private MyViewModel viewModel;
    public MazeDisplayer mazeDisplayer;
    public javafx.scene.control.TextField txtfld_rowsNum;
    public javafx.scene.control.TextField txtfld_columnsNum;
    public javafx.scene.control.Label lbl_rowsNum , showTime , time_remaining;
    public javafx.scene.control.Label lbl_columnsNum;
    public javafx.scene.control.Button btn_generateMaze , btn_solve , btn_hideSolution;
    public javafx.scene.control.MenuItem menu_item_save;



    public void setViewModel(MyViewModel viewModel) {
        this.viewModel = viewModel;
        bindProperties(viewModel);
        Sounds.getInstance().playBackgroundMusic();
    }

    private void updateTime(){
        while(true){
            timePassedSoFar = Integer.parseInt(getTimePassed());
        }
    }

    private void bindProperties(MyViewModel viewModel) {
        lbl_rowsNum.textProperty().bind(viewModel.characterPositionRow);
        lbl_columnsNum.textProperty().bind(viewModel.characterPositionColumn);
        this.showTime.textProperty().bind(viewModel.TimePassed);

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel) {
            displayMaze(viewModel.getMze());
            if(this.mazeDisplayer.checkSolved())
                endGame();
            btn_generateMaze.setDisable(false);
        }
    }

    @Override
    public void displayMaze(int[][] maze) {

        this.mazeDisplayer.setMaze(maze);
        int characterPositionRow = this.viewModel.getCharPositionRowIndex();
        int characterPositionColumn = this.viewModel.getCharacterColIndex();
        mazeDisplayer.setCharacterPosition(characterPositionRow, characterPositionColumn);
        this.characterPositionRow.set(characterPositionRow + "");
        this.characterPositionColumn.set(characterPositionColumn + "");

    }

    /**
     * maze is solved . ask the user how to keep on going
     */

    //region Handle
    private void endGame(){
        Sounds sounds = Sounds.getInstance();
        sounds.stopIngameMsic();
        FXMLLoader loader = new FXMLLoader();
        Stage endGameStage = new Stage();
        try {
            Parent root = loader.load(getClass().getResource("GameDone.fxml").openStream());
            Scene endScene = new Scene(root,600,400);
            GameDoneController gameDone = loader.getController();
            gameDone.setViewModel(this.viewModel);
            endGameStage.setScene(endScene);
            IHolder holder = StageHolder.getInstance();
            holder.registerObject(endGameStage);
            endGameStage.showAndWait();
            endGameStage.close();
            sounds.playBackgroundMusic();
        }catch (Exception e){
            e.printStackTrace();
        }

        this.mazeDisplayer.clearBoard();

    }


    public void handleZoomOnMaze(ScrollEvent event){

        if(event.getSource().equals(this.mazeDisplayer)) {
                this.mazeDisplayer.handleZoom(event);
            }
        event.consume();
    }

    /**
     *
     * move character with the mouse
     * @param event mouse event
     */
    public void handleMouseDragEnter(MouseEvent event){

        int ans = 0 ;
        if(event.getSource().equals(this.mazeDisplayer) && this.mazeDisplayer != null) {

            double cellWidth = this.mazeDisplayer.getCellWidth();
            double cellHeight = this.mazeDisplayer.getCellHeight();

            int charRow = Integer.parseInt(this.characterPositionRow.get());
            int charCol = Integer.parseInt(this.characterPositionColumn.get());

            if(event.getX() >= charCol*cellWidth-cellWidth && event.getX() <= charCol*cellWidth+cellWidth*2 &&
                    event.getY() >= charRow*cellHeight-cellHeight && event.getY() <= charRow*cellHeight+cellHeight*2){

                System.out.println(event.getX());
                if (event.getX() > charCol * cellWidth + cellWidth) { // move right
                    if (this.viewModel.legalCharacterMove(charRow, charCol + 1)) {
                        this.viewModel.setCharacterRowIndex(charRow);
                        this.viewModel.setCharacterColIndex(charCol+1);
                    }
                }

                if (event.getX() < charCol * cellWidth) {//left
                    if (this.viewModel.legalCharacterMove(charRow, charCol - 1)) {
                        this.viewModel.setCharacterRowIndex(charRow);
                        this.viewModel.setCharacterColIndex(charCol-1);
                    }
                }
                if (event.getY() < charRow * cellHeight){
                    if (this.viewModel.legalCharacterMove(charRow - 1, charCol)){
                        this.viewModel.setCharacterRowIndex(charRow-1);
                        this.viewModel.setCharacterColIndex(charCol);
                    }
                }
                if(event.getY() > charRow*cellHeight+cellHeight){
                    if(this.viewModel.legalCharacterMove(charRow+1,charCol)) {
                        this.viewModel.setCharacterRowIndex(charRow+1);
                        this.viewModel.setCharacterColIndex(charCol);
                    }
                }

                this.mazeDisplayer.redraw();
            }

        }
        event.consume();
    }


    public void generateMaze() {
        Sounds.getInstance().playClickMusic();
        Sounds.getInstance().playIngameMusic();
        Sounds.getInstance().stopBackgroundMusic();

        int rows = Integer.valueOf(this.txtfld_rowsNum.getText());
        int cols= Integer.valueOf(this.txtfld_columnsNum.getText());
        this.btn_generateMaze.setDisable(true);
        this.viewModel.generateMaze(rows, cols);
        try {
            Thread.sleep(10);//give the executor enough time to start the generating process
        }catch (Exception e){
            e.printStackTrace();
        }
        this.mazeDisplayer.setEndPosition(this.viewModel.getRowEndPosition(),this.viewModel.getColEndPosition());
        DoWork task = new DoWork();
        task.setViewModel(this.viewModel);
        new Thread(task).start();
        this.solution = null;
        this.mazeDisplayer.setSolution(null);

    }

    public void solveMaze(ActionEvent actionEvent) {
        Sounds.getInstance().playClickMusic();
        this.btn_solve.setDisable(true);
        this.viewModel.solveMaze();
        try{
            Thread.sleep(10);//give time to start solving
        }catch (Exception e){
            e.printStackTrace();
        }
        this.solution = this.viewModel.getSolution();
        this.mazeDisplayer.setSolution(this.solution);
        this.btn_solve.setDisable(false);
    }

    public void setResizeEvent(Scene scene) {
        final double[] width = {0,0};
        final double[] height = {0,0};

        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                mazeDisplayer.handleResizingWidth(newSceneWidth.doubleValue() - oldSceneWidth.doubleValue());
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                mazeDisplayer.handleesizingHeight(newSceneHeight.doubleValue()-oldSceneHeight.doubleValue());

            }
        });

    }

    public void handleHideSolution(){
        Sounds.getInstance().playClickMusic();
        btn_hideSolution.setDisable(true);
        this.mazeDisplayer.setSolution(null);
        btn_hideSolution.setDisable(false);
    }

    public void KeyPressed(KeyEvent keyEvent) {
        viewModel.moveCharacter(keyEvent.getCode());
        keyEvent.consume();
    }
    //endregion




    private void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.show();
    }


    //region String Property for Binding

    public StringProperty timeRemaining = new SimpleStringProperty("0");

    public StringProperty characterPositionRow = new SimpleStringProperty();

    public StringProperty characterPositionColumn = new SimpleStringProperty();

    public String getCharacterPositionRow() {
        return characterPositionRow.get();
    }

    public StringProperty characterPositionRowProperty() {
        return characterPositionRow;
    }

    public String getCharacterPositionColumn() {
        return characterPositionColumn.get();
    }

    public String getTimePassed(){
        return timeRemaining.get();
    }

    public void setTimePassed(int seconds){
        this.timeRemaining.set(seconds+"");
    }

    public StringProperty characterPositionColumnProperty() {
        return characterPositionColumn;
    }



    public void About(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("AboutController");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("About.fxml").openStream());
            Scene scene = new Scene(root, 400, 350);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoWork task = new DoWork();
        task.setViewModel(this.viewModel);
      //  new Thread(task).start();

    }

    //endregion


    @FXML
    private void saveGame(ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader();
        Stage saveGameStage = new Stage();
        saveGameStage.setTitle("Save menu");
        try {
            Parent root = loader.load(getClass().getResource("GameSave.fxml").openStream());
            Scene saveScene = new Scene(root,600,400);
            GameSaveController gameSave = loader.getController();
            gameSave.setViewModel(this.viewModel);
            saveGameStage.setScene(saveScene);
            StageHolder holder = StageHolder.getInstance();
            holder.registerObject(saveGameStage);
            saveGameStage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }

//        this.mazeDisplayer.clearBoard();

    }



    @FXML
    private void loadGame(ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader();
        Stage loadGameStage = new Stage();
        loadGameStage.setTitle("Load menu");
        try {
            Parent root = loader.load(getClass().getResource("GameLoad.fxml").openStream());
            Scene loadScene = new Scene(root,600,400);
            GameLoadController gameLoad = loader.getController();
            gameLoad.setViewModel(this.viewModel);
            loadGameStage.setScene(loadScene);
            StageHolder holder = StageHolder.getInstance();
            holder.registerObject(loadGameStage);
            loadGameStage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }

//        this.mazeDisplayer.clearBoard();

    }




    @FXML
    private void gameHelp(ActionEvent actionEvent){
        FXMLLoader loader = new FXMLLoader();
        Stage helpGameStage = new Stage();
        helpGameStage.setTitle("Game instructions");
        try {
            Parent root = loader.load(getClass().getResource("GameHelp.fxml").openStream());
            Scene loadScene = new Scene(root,600,400);
            GameHelpController gameHelp = loader.getController();
            gameHelp.setViewModel(this.viewModel);
            helpGameStage.setScene(loadScene);
            StageHolder holder = StageHolder.getInstance();
            holder.registerObject(helpGameStage);
            helpGameStage.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
        }

//        this.mazeDisplayer.clearBoard();

    }



     @FXML
     public void editConfig(ActionEvent selection){
         this.configField = ((RadioMenuItem)selection.getSource()).getParentMenu().getId();
         this.configFieldNewValue = ((RadioMenuItem)selection.getSource()).getText();
         this.viewModel.editConfig(configField,configFieldNewValue);
     }





}
