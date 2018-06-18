package Work;

import ViewModel.MyViewModel;
import javafx.concurrent.Task;

public class DoWork extends Task<Integer> {

    private MyViewModel viewModel;
    @Override
    protected Integer call() throws Exception {

        Thread timeThread = new Thread(() -> this.viewModel.startTimeThread());
        //timeThread.start();
        System.out.println("Worker started threads");
        return 1;
    }

    public void setViewModel(MyViewModel viewModel){
        this.viewModel = viewModel;
    }

}
