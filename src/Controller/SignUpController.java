package Controller;

import Model.Main;
import Model.PageLoader;
import Model.client.API;
import Model.common.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SignUpController {
    @FXML
    public TextField password;
    public Label invalid_password;
    public Label taken_username;
    public Circle profileImage;
    public TextField username;
    public TextField father_birth_year;
    public TextField favorite_color;
    public TextField mother_birth_year;
    public TextField last_name;
    public TextField phone_number;
    public TextField first_name;
    String valid_password = "[a-zA-Z0-9]{8,}";
    private Account account;

    @FXML
    public void initialize() {
        profileImage.setFill(new ImagePattern(new Image("View\\user.png")));
        account = new Account("");
    }

    public void Sign_up(ActionEvent actionEvent) throws IOException {
        if (API.doesUserNameExist(username.getText()))
            taken_username.setVisible(true);
        if (!password.getText().matches(valid_password))
            invalid_password.setVisible(true);
        else {
            account.setUsername(username.getText());
            account.setPassword(password.getText());
            account.setPhoneNumber(phone_number.getText());
            account.setFirstName(first_name.getText());
            account.setLastName(last_name.getText());
            account.setFathersBirthYear(father_birth_year.getText());
            account.setMothersBirthYear(mother_birth_year.getText());
            account.setFavoriteColor(favorite_color.getText());
            if (account.getProfilePhotoPath() == null) {
                account.setProfilePhotoPath("G:\\uni\\term2\\ap\\code\\my project\\src\\View\\user.png");
            }
            API.signUp(account);
            Main.account = account;
            new PageLoader().load("timeLine");
        }
    }

    public void login(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("login");
    }


    public void set_image(ActionEvent actionEvent) {
        try {

            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Popup());
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = fileInputStream.readAllBytes();
            account.setProfilePhotoPath(file.getPath());
           // account.setProfilePhoto(bytes);
            Image image = new Image(new ByteArrayInputStream(bytes));
            profileImage.setFill(new ImagePattern(image));
        } catch (Exception e) {
            account.setProfilePhotoPath("View\\user.png");
        }
    }
}
