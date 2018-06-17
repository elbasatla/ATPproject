package StageUtills;

import javafx.stage.Stage;

public interface IHolder<V> {

    V getPrimaryObject();
    void deleteObject(int num);
    void registerObject(V obj);
    V getCurrent();
}
