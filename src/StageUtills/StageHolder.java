package StageUtills;

import javafx.stage.Stage;

import java.util.LinkedList;

public class StageHolder<V> implements IHolder<V> {

    private static StageHolder ourInstance = new StageHolder();
    private LinkedList<V> stages ;
    private V currentStage;
    public static StageHolder getInstance() {
        return ourInstance;
    }

    private StageHolder() {
        stages = new LinkedList<>();
    }

    @Override
    public void deleteObject(int num){
        this.stages.remove(num);
    }

    @Override
    public void registerObject(V obj) {
        this.stages.add(obj);
        currentStage = this.stages.get(stages.size()-1);
    }

    @Override
    public V getPrimaryObject(){
        return stages.get(0);
    }


    @Override
    public V getCurrent() {
        return this.currentStage;
    }

    public void deleteCurrentStage(){this.stages.remove(currentStage); }

   // public void setCurrentStage(Stage stage){this.currentStage = stage;}
}
