package StageUtills;

import java.util.LinkedList;

public class WorkersHolder<V> implements IHolder<V>{

    private static WorkersHolder ourInstance = new WorkersHolder<>();
    private LinkedList<V> stages ;
    private V currentStage;
    public static WorkersHolder getInstance() {
        return ourInstance;
    }

    private WorkersHolder() {
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
}
