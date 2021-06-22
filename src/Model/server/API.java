package Model.server;

import Model.common.*;


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
        Account account = ServerEXE.accounts.get(usernamecheck);
        Boolean exists = (account != null);

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
}