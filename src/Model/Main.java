package Model;

import Model.client.ClientNetworker;
import Model.common.Account;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Account account;

    @Override
    public void start(Stage stage) throws Exception{
        PageLoader.initStage(stage); //this is only needed when you start program
        //and need a new stage. all scenes will be loaded on this stage
        new PageLoader().load("login");
    }

    public static void main(String[] args) {
        ClientNetworker.connectToServer(args);
        launch(args);
    }
}
