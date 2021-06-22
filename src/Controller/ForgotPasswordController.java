package Controller;

import Model.PageLoader;
import Model.client.API;
import Model.common.Account;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ForgotPasswordController {
    public Label False_information_label;
    public Label password_show_label;
    public Label guide_label;
    public TextField password_field;
    public TextField mother_birth_year;
    public TextField favorite_color;
    public TextField father_birth_year;
    public TextField username;

    public void login(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("login");
    }

    public void getPassword(ActionEvent actionEvent) {
        Account account = API.forgotten_password(username.getText(),father_birth_year.getText(),mother_birth_year.getText(),favorite_color.getText());

        if (account == null)
            False_information_label.setVisible(true);
        else
        {
            password_show_label.setVisible(true);
            guide_label.setVisible(true);
            password_field.setText(account.getPassword());
        }

    }
}
