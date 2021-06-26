package Controller;

import Model.Main;
import Model.PageLoader;
import Model.client.API;
import Model.common.Account;
import Model.common.Post;
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
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());
        post_image.setFill(new ImagePattern(new Image("View\\no_image.png")));
    }

    public void menu(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("menu");
    }

    public void refresh(MouseEvent mouseEvent) {
    }

    public void publish(ActionEvent actionEvent) {
        currentPost= new Post(account,title.getText(),description.getText());
        //currentPost.setWriter(account);
        //currentPost.setTitle(title.getText());
        //currentPost.setDescription(description.getText());
         API.publish_post(currentPost);
        //save the post in arraylist
        posts.add(currentPost);

        //show the arraylist in listview
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());
        /*
        if the listview cells are not customized and list view is made of strings
        this code will add new post title to the list view
        postList.getItems().add(currentPost.getTitle());
         */

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
