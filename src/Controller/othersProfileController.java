package Controller;

import Model.Main;
import Model.PageLoader;
import Model.client.API;
import Model.common.Account;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.nio.file.Paths;

public class othersProfileController {
    public Button follow;
    public Button unfollow;
    public Account account = Main.toShow_account;
    public Circle profileImage;
    public Label name;
    public Label username_label;
    public Label phone_number;
    public Label numOfFollowings;
    public Label numOfFollowers;

    public void initialize() {
        name.setText(account.getName());
        username_label.setText("@" + account.getUsername());
        phone_number.setText(account.getPhoneNumber());
        profileImage.setFill(new ImagePattern(new Image(Paths.get(account.getProfilePhotoPath()).toUri().toString())));
        numOfFollowers.setText(String.valueOf(account.followers.size()));
        numOfFollowings.setText(String.valueOf(account.followings.size()));
        if (account.followers.contains(Main.account)) {
            follow.setVisible(false);
            unfollow.setVisible(true);
        } else {
            unfollow.setVisible(false);
            follow.setVisible(true);
        }
    }

    public void follow(ActionEvent actionEvent) throws IOException {
        numOfFollowers.setText(String.valueOf(account.followers.size() + 1));
        API.follow(Main.account.getUsername(), Main.toShow_account.getUsername());
        follow.setVisible(false);
        unfollow.setVisible(true);
    }

    public void unfollow(ActionEvent actionEvent) {

        unfollow.setVisible(false);
        follow.setVisible(true);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("allAccounts");
    }
}
