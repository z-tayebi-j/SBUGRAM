package Model.client;

import Model.common.*;

import java.util.HashMap;
import java.util.Map;

public class API {
    public static Boolean signUp(Account account) {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("command", Command.SIGNUP);
        toSend.put("account", account);
        Map<String, Object> recieved = ClientNetworker.serve(toSend);
        if (recieved.get("answer") == null) return null;
        return (Boolean) recieved.get("answer");
    }

    public static boolean doesUserNameExist(String username) {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("command", Command.IS_USERNAME_UNIQUE);
        toSend.put("username", username);
        Map<String, Object> recieved = ClientNetworker.serve(toSend);
        return (boolean) recieved.get("answer");
    }

    public static Account login(String username, String password) {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("command", Command.LOGIN);
        toSend.put("username", username);
        toSend.put("password", password);
        Map<String, Object> recieved = ClientNetworker.serve(toSend);
        if (recieved.get("answer") == null)
            return null;
        return (Account) recieved.get("answer");
    }

    public static Account forgotten_password(String username, String fathersBirthYear, String mothersBirthYear, String favoriteColor) {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("command", Command.FORGOTTEN_PASSWORD);
        toSend.put("username", username);
        toSend.put("fathersBirthYear", fathersBirthYear);
        toSend.put("mothersBirthYear", mothersBirthYear);
        toSend.put("favoriteColor", favoriteColor);
        Map<String, Object> recieved = ClientNetworker.serve(toSend);
        if (recieved.get("answer") == null)
            return null;
        return (Account) recieved.get("answer");
    }
}
