package Controller;

import Model.common.Account;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class profileItem extends ListCell<Account> {
    @Override
    public void updateItem(Account account, boolean empty) {
        super.updateItem(account, empty);
        if (account != null) {
            try {
                setGraphic(new profileItemController(account).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
