package Model;

import Model.client.ClientNetworker;
import Model.common.Account;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Account account;
    public static Account toShow_account;
    @Override
    public void start(Stage stage) throws Exception{
        PageLoader.initStage(stage);
        new PageLoader().load("login");
       // new PageLoader().load("othersProfile");

    }

    public static void main(String[] args) {
        ClientNetworker.connectToServer(args);
        launch(args);
    }
}
