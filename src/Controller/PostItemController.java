package Controller;

import Model.Main;
import Model.PageLoader;
import Model.common.Post;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Paths;

public class PostItemController {
    public AnchorPane root;
    public Label title;
    public Circle profileImage;
    public Label writer_name;
    public Text description;
    public Rectangle post_image;
    Post post;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public PostItemController(Post post) throws IOException {
        new PageLoader().load("postItem", this);
        this.post = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() throws IOException {
        title.setText(post.getTitle());
        writer_name.setText(post.getWriter().getName());
        profileImage.setFill(new ImagePattern(new Image(Paths.get(post.getWriter().getProfilePhotoPath()).toUri().toString())));
        description.setText(post.getDescription());
        if (post.getImagePath().equals(""))
            post_image.setVisible(false);
        else
            post_image.setFill(new ImagePattern(new Image(Paths.get(post.getImagePath()).toUri().toString())));
        return root;
    }

    public void show_profile() throws IOException {
        Main.toShow_account = post.getWriter();
        new PageLoader().load("othersProfile");
    }

    public void repost() {
    }

    public void like() {
    }
}
