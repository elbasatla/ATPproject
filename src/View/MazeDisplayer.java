package View;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;
import algorithms.search.Solution;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MazeDisplayer extends Canvas implements IDisplayMaze {


    private final double SCALE = 1.03;

    private double cellWidth , cellHeight , originalWidth , originalHeight;
    private int[][] maze ;
    private Solution solution;
    private int characterPositionRow = 1;
    private int characterPositionColumn = 1;
    private int rowEndIndex , colEndIndex ;

    //region Graphics

    public void clearBoard(){

        GraphicsContext g = getGraphicsContext2D();
        g.clearRect(0,0,getWidth(),getHeight());
        this.maze = null;
    }

    public void redraw() {
        if (this.maze != null){
            try {
                double scaleWidth = this.getWidth() / this.originalWidth;
                double scaleHeight = this.getHeight() / this.originalHeight;
                System.out.println("got " + maze.length + " rows and " + maze[0].length + " cols");

                double cellHeight = this.getHeight()/maze.length;
                double cellWidth = this.getWidth()/maze[0].length;

                Image wallImage = new Image(new FileInputStream(ImageFileNameWall.get()));
                Image characterImage = new Image(new FileInputStream(ImageFileNameCharacter.get()));
                Image solutionPathImage = new Image(new FileInputStream(ImageFileNameSolutionImage.get()));
                Image endImage = new Image(new FileInputStream(ImageFileNameEndImage.get()));
                Image pathWay = new Image(new FileInputStream(ImagePathWay.get()));


                GraphicsContext g = getGraphicsContext2D();
                g.clearRect(0, 0, getWidth(), getHeight());

                //Draw Maze
                for (int rows = 0; rows < maze.length; rows++) {
                    for (int cols = 0; cols < maze[0].length; cols++) {
                        if (maze[rows][cols] == 1) {
                            g.drawImage(wallImage, cols * cellWidth, rows * cellHeight, cellWidth, cellHeight);
                        }else{
                            g.drawImage(pathWay, cols * cellWidth, rows * cellHeight, cellWidth, cellHeight);
                        }
                    }
                }

                //Draw Character
                g.drawImage(characterImage, characterPositionColumn * cellWidth, characterPositionRow * cellHeight, cellWidth, cellHeight);
                g.drawImage(endImage,colEndIndex*cellWidth,rowEndIndex*cellHeight,cellWidth,cellHeight);
                //draw solution
                if (solution != null) {

                    ArrayList<AState> path = this.solution.getSolutionPath();
                    for (AState state :
                            path) {
                        int row = state.getPosition().getRowIndex();
                        int col = state.getPosition().getColumnIndex();

                        g.drawImage(solutionPathImage, col * cellWidth, row * cellHeight, cellWidth, cellHeight);

                    }
                }
                } catch(FileNotFoundException e){
                    e.printStackTrace();
                }
        }
    }

    //endregion

    //region Handle

    public void handleResizingWidth(double deltaWidth){
        this.widthProperty().set(this.getWidth() + deltaWidth);
        redraw();
    }

    public void handleesizingHeight(double deltaHeight){
        this.heightProperty().set(this.getHeight()+deltaHeight);
        redraw();
    }

    public void handleZoom(ScrollEvent event){

        if(event.getDeltaY() < 0) { // zoom out
            this.setScaleX(this.getScaleX() * (1/SCALE));
            this.setScaleY(this.getScaleY() * (1/SCALE));
        }else {
            this.setScaleX(this.getScaleX() * SCALE);
            this.setScaleY(this.getScaleY() * SCALE);
        }
    }


    //endregion

    //region Properties
    private StringProperty ImageFileNameWall = new SimpleStringProperty();
    private StringProperty ImageFileNameCharacter = new SimpleStringProperty();
    private StringProperty ImageFileNameSolutionImage = new SimpleStringProperty();
    private StringProperty ImageFileNameEndImage = new SimpleStringProperty();
    private StringProperty ImagePathWay = new SimpleStringProperty();

    public String getImageFileNameEndImage(){
        return ImageFileNameEndImage.get();
    }

    public String getImagePathWay(){return this.ImagePathWay.get();}

    public void setImagePathWay(String imagePathWay){this.ImagePathWay.set(imagePathWay);}


    public void setImageFileNameEndImage(String imageFileNameEndImage){
        this.ImageFileNameEndImage.set(imageFileNameEndImage);
    }

        public boolean checkSolved(){
        boolean ans = characterPositionRow == rowEndIndex && characterPositionColumn == colEndIndex;
        if(ans)
            this.characterPositionRow = -1;
        return ans ;
    }

    public String getImageFileNameWall() {
        return ImageFileNameWall.get();
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.ImageFileNameWall.set(imageFileNameWall);
    }

    public String getImageFileNameCharacter() {
        return ImageFileNameCharacter.get();
    }

    public void setImageFileNameSolutionImage(String imageFileNameSolutionImage){this.ImageFileNameSolutionImage.set(imageFileNameSolutionImage);}

    public String getImageFileNameSolutionImage(){return this.ImageFileNameSolutionImage.get();}

    public void setImageFileNameCharacter(String imageFileNameCharacter) {
        this.ImageFileNameCharacter.set(imageFileNameCharacter);
    }
    //endregion

    //region SetAndGet

    public void setMaze(int[][] maze) {
        this.maze = maze;
        this.originalWidth = this.getWidth();
        this.originalHeight = this.getHeight();
        this.cellHeight = getHeight() / maze.length;
        this.cellWidth = getWidth() / maze[0].length;

        redraw();
    }

    public void setCharacterPosition(int row, int column) {
        this.characterPositionRow = row;
        this.characterPositionColumn = column;
        redraw();
    }

    @Override
    public void setSolution(Solution solution) {
        this.solution = solution;
        redraw();
    }

    @Override
    public void setEndPosition(int row, int col) {
        this.rowEndIndex = row;
        this.colEndIndex = col ;
    }


    public double getCellWidth(){
        return this.cellWidth;
    }

    public double getCellHeight(){
        return this.cellHeight;
    }

    //endregion
}
