package Controller;

import Model.Main;
import Model.PageLoader;
import Model.client.API;
import Model.common.Account;
import Model.common.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class myProfileController {
    public Circle profileImage;
    public Label name;
    public Label numOfFollowings;
    public Label numOfFollowers;
    public Label phone_number;
    public Label username_label;
    public ListView postList;
    ArrayList<Post> posts;
    private Account account = Main.account;

    @FXML
    public void initialize() {
        name.setText(account.getName());
        username_label.setText("@" + account.getUsername());
        phone_number.setText(account.getPhoneNumber());
        numOfFollowers.setText(String.valueOf(account.followers.size()));
        numOfFollowings.setText(String.valueOf(account.followings.size()));
        profileImage.setFill(new ImagePattern(new Image(Paths.get(account.getProfilePhotoPath()).toUri().toString())));
        posts = API.gatAccountsPosts(account.getUsername());
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());
    }

    public void menu(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("menu");
    }

    public void update_info(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("updateInfo");

    }
}
