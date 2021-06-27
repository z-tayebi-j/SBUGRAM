package Controller;

import Model.Main;
import Model.PageLoader;
import Model.client.API;
import Model.common.Account;
import Model.common.Post;
import Model.common.Time;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class timeLineController {


    public ListView<Post> postList;
    public TextArea description;
    public TextField title;
    public Account account = Main.account;
    public Rectangle post_image;
    ArrayList<Post> posts = new ArrayList<>();
    Post currentPost;

    @FXML
    public void initialize() {
        currentPost = new Post();
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());
        post_image.setFill(new ImagePattern(new Image("View\\no_image.png")));
        posts = API.getPostsToShow(account.getUsername());
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());
    }

    public void menu(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("menu");
    }

    public void refresh(MouseEvent mouseEvent) {
        posts = API.getPostsToShow(account.getUsername());
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());
    }

    public void publish(ActionEvent actionEvent) {
        currentPost.setWriter(account);
        currentPost.setTitle(title.getText());
        currentPost.setDescription(description.getText());
        currentPost.setCreatedTime(Time.getMilli());
        API.publish_post(currentPost);
        //save the post in arraylist
        posts = API.getPostsToShow(account.getUsername());


        //show the arraylist in listview
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());


        currentPost = new Post();
        title.setText("");
        description.setText("");
        post_image.setFill(new ImagePattern(new Image("View\\no_image.png")));
    }

    public void add_image(ActionEvent actionEvent) {
        try {

            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Popup());
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = fileInputStream.readAllBytes();
            currentPost.setImagePath(file.getPath());
            Image image = new Image(new ByteArrayInputStream(bytes));
            post_image.setFill(new ImagePattern(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
