package Controller;

import Model.Main;
import Model.PageLoader;
import Model.client.API;
import Model.common.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField username_field;
    public Button login_button;
    public PasswordField password_field;
    public Label wrong_label;
    public TextField password_field_visible;

    public void show_password(ActionEvent actionEvent) {
        if (!password_field_visible.isVisible()) {
            password_field_visible.setVisible(true);
            password_field.setVisible(false);
            password_field_visible.setText(password_field.getText());
        } else {
            password_field_visible.setVisible(false);
            password_field.setVisible(true);
            password_field.setText(password_field_visible.getText());
        }
    }

    public void Sign_up(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("SignUp");
    }

    public void login(ActionEvent actionEvent) throws IOException {
        Account account = API.login(username_field.getText(), password_field.getText());
        if (account == null)
            wrong_label.setVisible(true);
        else {
            Main.account_username = username_field.getText();
            Main.account = account;
            new PageLoader().load("timeLine");
        }
    }

    public void Forgot_password(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ForgotPassword");
    }
}
