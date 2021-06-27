package Controller;

import Model.Main;
import Model.PageLoader;
import Model.client.API;
import Model.common.Account;
import Model.common.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class othersProfileController {
    public Button follow;
    public Button unfollow;
    public Account account;
    public Circle profileImage;
    public Label name;
    public Label username_label;
    public Label phone_number;
    public Label numOfFollowings;
    public Label numOfFollowers;
    public ListView postList;
    ArrayList<Post> posts;

    public void initialize() {
        account = API.getAccount(Main.toShow_account_username);
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
        posts = API.getAccountsPosts(account.getUsername());
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());
    }

    public void follow(ActionEvent actionEvent) throws IOException {
        int ans = API.follow(Main.account_username, Main.toShow_account_username);
        numOfFollowers.setText(String.valueOf(ans));
        follow.setVisible(false);
        unfollow.setVisible(true);
    }

    public void unfollow(ActionEvent actionEvent) {
        int ans = API.unfollow(Main.account_username, Main.toShow_account_username);
        numOfFollowers.setText(String.valueOf(ans));
        unfollow.setVisible(false);
        follow.setVisible(true);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("menu");
    }
}
