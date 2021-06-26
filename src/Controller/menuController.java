package Controller;

import Model.Main;
import Model.PageLoader;
import Model.client.API;
import javafx.event.ActionEvent;

import java.io.IOException;

public class menuController {
    public void show_list_of_others(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("allAccounts");
    }

    public void show_my_profile(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("myProfile");

    }

    public void show_timeline(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("timeLine");
    }

    public void log_out(ActionEvent actionEvent) throws IOException {
        API.logout(Main.account);
        System.exit(1);
       // Main.account = null;
        //new PageLoader().load("login");
    }
}
