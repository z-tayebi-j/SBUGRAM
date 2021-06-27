package Controller;

import Model.Main;
import Model.PageLoader;
import Model.client.API;
import Model.common.Account;
import javafx.event.ActionEvent;
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
import java.nio.file.Paths;

public class updateInfoController {
    public TextField password;
    public TextField father_birth_year;
    public TextField favorite_color;
    public TextField mother_birth_year;
    public TextField last_name;
    public TextField phone_number;
    public TextField first_name;
    public Label invalid_password;
    public Circle profileImage;
    public Label username_label;
    private Account account = API.getAccount(Main.account_username);
    String valid_password = "[a-zA-Z0-9]{8,}";

    public void initialize() {
        username_label.setText("@" + account.getUsername());
        password.setText(account.getPassword());
        first_name.setText(account.getFirstName());
        last_name.setText(account.getLastName());
        phone_number.setText(account.getPhoneNumber());
        father_birth_year.setText(account.getFathersBirthYear());
        mother_birth_year.setText(account.getMothersBirthYear());
        favorite_color.setText(account.getFavoriteColor());
        profileImage.setFill(new ImagePattern(new Image(Paths.get(account.getProfilePhotoPath()).toUri().toString())));
    }

    public void set_image(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Popup());
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = fileInputStream.readAllBytes();
            account.setProfilePhotoPath(file.getPath());
            Image image = new Image(new ByteArrayInputStream(bytes));
            profileImage.setFill(new ImagePattern(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(ActionEvent actionEvent) throws IOException {
        if (!password.getText().matches(valid_password))
            invalid_password.setVisible(true);
        else {
            account.setFirstName(first_name.getText());
            account.setLastName(last_name.getText());
            account.setPhoneNumber(phone_number.getText());
            account.setFathersBirthYear(father_birth_year.getText());
            account.setMothersBirthYear(mother_birth_year.getText());
            account.setFavoriteColor(favorite_color.getText());
            account.setPassword(password.getText());
            API.updateAccount(account);
            new PageLoader().load("myProfile");

        }
    }
}
