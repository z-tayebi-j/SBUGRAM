package Controller;

import Model.Main;
import Model.PageLoader;
import Model.common.Account;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.nio.file.Paths;


public class profileItemController {
    public AnchorPane root;
    public Label username;
    public Label name;
    public Circle profileImage;
    Account account;

    public profileItemController(Account account) throws IOException {
        new PageLoader().load("profileItem", this);
        this.account = account;
    }

    public AnchorPane init() throws IOException {
        username.setText("@" + account.getUsername());
        name.setText(account.getName());
        profileImage.setFill(new ImagePattern(new Image(Paths.get(account.getProfilePhotoPath()).toUri().toString())));
        return root;
    }
    public void view() throws IOException {
        Main.toShow_account = account;
        new PageLoader().load("othersProfile");
    }
}
