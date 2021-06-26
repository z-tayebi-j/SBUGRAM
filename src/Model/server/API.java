package Model.server;

import Model.common.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class API {
    @SuppressWarnings("unchecked")
    public static Map<String, Object> signUp(Map<String, Object> income) {
        Account newAccount = (Account) income.get("account");
        String username = newAccount.getUsername();
        ServerEXE.accounts.put(username, newAccount);
        DBManager.getInstance().updateDataBase();
        Map<String, Object> answer = new HashMap<>();
        answer.put("command", Command.SIGNUP);
        answer.put("answer", true);

        System.out.println(newAccount.getUsername() + " register " + newAccount.getProfilePhotoPath());
        System.out.println("time : " + Time.getTime());
        System.out.println(newAccount.getUsername() + " login");
        System.out.println("time : " + Time.getTime());

        return answer;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> doesUserNameExist(Map<String, Object> income) {
        String usernamecheck = (String) income.get("username");
        Boolean exists = ServerEXE.accounts.containsKey(usernamecheck);

        Map<String, Object> answer = new HashMap<>();
        answer.put("answer", exists);
        answer.put("command", Command.IS_USERNAME_UNIQUE);
        return answer;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> login(Map<String, Object> income) {

        String username = (String) income.get("username");
        String password = (String) income.get("password");

        Boolean isNullProfile = (ServerEXE.accounts.get(username) == null);
        Map<String, Object> answer = new HashMap<>();
        answer.put("command", Command.LOGIN);
        answer.put("exists", !isNullProfile);
        if (isNullProfile)
            return answer;

        Account account = ServerEXE.accounts.get(username).authenticate(username, password);
        answer.put("answer", account);

        if (account != null) {
            System.out.println(account.getUsername() + " login");
            System.out.println("time : " + Time.getTime());
        }
        return answer;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> forgotten_password(Map<String, Object> income) {
        String username = (String) income.get("username");
        String fathersBirthYear = (String) income.get("fathersBirthYear");
        String mothersBirthYear = (String) income.get("mothersBirthYear");
        String favoriteColor = (String) income.get("favoriteColor");

        Map<String, Object> answer = new HashMap<>();
        answer.put("command", Command.FORGOTTEN_PASSWORD);
        Account account = ServerEXE.accounts.get(username);
        if (account == null) {
            answer.put("answer", null);
            return answer;
        }
        if (account.getFathersBirthYear().equals(fathersBirthYear) && account.getMothersBirthYear().equals(mothersBirthYear) && account.getFavoriteColor().equals(favoriteColor)) {
            answer.put("answer", account);
            return answer;
        }
        answer.put("answer", null);
        return answer;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> publish_post(Map<String, Object> income) {
        Post post = (Post) income.get("post");
        ServerEXE.posts.add(post);
        ServerEXE.accounts.get(post.getWriter().getUsername()).posts.add(post);

        DBManager.getInstance().updateDataBase();
        Map<String, Object> answer = new HashMap<>();
        answer.put("command", Command.PUBLISH_POST);
        answer.put("answer", true);

        System.out.println(post.getWriter().getUsername() + " publish");
        System.out.println("message: " + post.getTitle() + " " + post.getImagePath() + " " + post.getWriter().getUsername());
        System.out.println("time : " + Time.getTime());

        return answer;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> logout(Map<String, Object> income) {
        Account account = (Account) income.get("account");
        System.out.println(account.getUsername() + " logout");
        System.out.println("time : " + Time.getTime());
        Map<String, Object> answer = new HashMap<>();
        answer.put("command", Command.LOGOUT);
        answer.put("answer", true);
        return answer;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> updateAccount(Map<String, Object> income) {
        Account newAccount = (Account) income.get("account");
        String username = newAccount.getUsername();
        //ServerEXE.accounts.replace(username, newAccount);
        DBManager.getInstance().updateDataBase();
        Map<String, Object> answer = new HashMap<>();
        answer.put("command", Command.UPDATE_ACCOUNT_INFO);
        answer.put("answer", true);

        System.out.println(newAccount.getUsername() + " update info ");
        System.out.println("message : " + newAccount.getProfilePhotoPath());
        System.out.println("time : " + Time.getTime());

        return answer;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> gatAllAccounts(Map<String, Object> income) {
        Map<String, Object> answer = new HashMap<>();
        answer.put("answer", ServerEXE.accounts);
        answer.put("command", Command.GET_ALL_ACCOUNTS);
        return answer;
    }

    public static Map<String, Object> gatAccountsPosts(Map<String, Object> income) {
        String username = (String) income.get("username");
        ArrayList<Post> posts = ServerEXE.accounts.get(username).posts;
        Map<String, Object> answer = new HashMap<>();
        answer.put("answer", posts);
        answer.put("command", Command.GET_POSTS_OF_AN_ACCOUNT);
        System.out.println(username + " get posts list");
        System.out.println("time : " + Time.getTime());

        return answer;
    }

    public static Map<String, Object> follow(Map<String, Object> income) {
        String follower = (String) income.get("follower");
        String following = (String) income.get("following");
        ServerEXE.accounts.get(follower).followings.add(ServerEXE.accounts.get(following));
        ServerEXE.accounts.get(following).followers.add(ServerEXE.accounts.get(follower));
        Map<String, Object> answer = new HashMap<>();
        answer.put("command", Command.FOLLOW);
        System.out.println(follower + " started following " + following);
        System.out.println("time : " + Time.getTime());
        return answer;
    }

    public static Map<String, Object> unfollow(Map<String, Object> income) {
        String ufollower = (String) income.get("unfollower");
        String target = (String) income.get("target");
        ServerEXE.accounts.get(ufollower).followings.remove(ServerEXE.accounts.get(target));
        ServerEXE.accounts.get(target).followers.remove(ServerEXE.accounts.get(ufollower));
        Map<String, Object> answer = new HashMap<>();
        System.out.println(ufollower + " unfollowed " + target);
        System.out.println("time : " + Time.getTime());
        answer.put("command", Command.UNFOLLOW);
        return answer;
    }
}