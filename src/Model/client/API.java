package Model.client;

import Model.common.Account;
import Model.common.Command;
import Model.common.Post;

import java.util.ArrayList;
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

    public static boolean publish_post(Post post) {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("command", Command.PUBLISH_POST);
        toSend.put("post", post);
        Map<String, Object> recieved = ClientNetworker.serve(toSend);
        if (recieved.get("answer") == null)
            return false;
        return (Boolean) recieved.get("answer");
    }

    public static Boolean logout(Account account) {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("command", Command.LOGOUT);
        toSend.put("account", account);
        Map<String, Object> recieved = ClientNetworker.serve(toSend);
        if (recieved.get("answer") == null) return null;
        return (Boolean) recieved.get("answer");
    }

    public static Boolean updateAccount(Account account) {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("command", Command.UPDATE_ACCOUNT_INFO);
        toSend.put("account", account);
        Map<String, Object> recieved = ClientNetworker.serve(toSend);
        if (recieved.get("answer") == null) return false;
        return (Boolean) recieved.get("answer");
    }

    public static Map<String, Account> gatAllAccounts() {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("command", Command.GET_ALL_ACCOUNTS);
        Map<String, Object> recieved = ClientNetworker.serve(toSend);
        return (Map<String, Account>) recieved.get("answer");
    }

    public static ArrayList<Post> gatAccountsPosts(String username) {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("command", Command.GET_POSTS_OF_AN_ACCOUNT);
        toSend.put("username", username);
        Map<String, Object> recieved = ClientNetworker.serve(toSend);
        return (ArrayList<Post>) recieved.get("answer");
    }

    public static void follow(String follower, String following) {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("command", Command.FOLLOW);
        toSend.put("follower", follower);
        toSend.put("following", following);
        Map<String, Object> recieved = ClientNetworker.serve(toSend);
    }
    public static void unfollow(String unfollower, String target) {
        Map<String, Object> toSend = new HashMap<>();
        toSend.put("command", Command.UNFOLLOW);
        toSend.put("unfollower", unfollower);
        toSend.put("target", target);
        Map<String, Object> recieved = ClientNetworker.serve(toSend);
    }
}
