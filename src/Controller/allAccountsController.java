package Controller;

import Model.Main;
import Model.PageLoader;
import Model.client.API;
import Model.common.Account;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class allAccountsController {
    public ListView<Account> profilesList;
    public Map<String, Account> profiles = new HashMap<>();

    @FXML
    public void initialize() {
        profiles = API.gatAllAccounts();
        profiles.remove(Main.account.getUsername());
        profilesList.setItems(FXCollections.observableArrayList(profiles.values()));
        profilesList.setCellFactory(profilesList -> new profileItem());
    }

    public void menu(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("menu");
    }
}
