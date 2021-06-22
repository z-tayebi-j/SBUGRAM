package Model.server;

import Model.common.*;

import java.io.*;
import java.util.concurrent.*;

public class DBManager {
    public static final String ACCOUNTS_FILE = "G:\\uni\\term2\\ap\\code\\my project\\src\\Model\\database\\AccountsDB.txt";
    public static final String POSTS_FILE = "G:\\uni\\term2\\ap\\code\\my project\\src\\Model\\database\\PostsDB.txt";

    private static DBManager theInstance = new DBManager();

    private DBManager() {
    }

    public static DBManager getInstance() {
        return theInstance;
    }

    @SuppressWarnings("unchecked")
    public synchronized void initializeServer() {
        try {
            FileInputStream fileInputStream = new FileInputStream(DBManager.ACCOUNTS_FILE);
            ObjectInputStream inputFromFile = new ObjectInputStream(fileInputStream);
            ServerEXE.accounts = new ConcurrentHashMap<>((ConcurrentHashMap<String, Account>) inputFromFile.readObject());
            inputFromFile.close();
            fileInputStream.close();
        } catch (EOFException | StreamCorruptedException e) {
            ServerEXE.accounts = new ConcurrentHashMap<>();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(DBManager.POSTS_FILE);
            ObjectInputStream inputFromFile = new ObjectInputStream(fileInputStream);
            ServerEXE.posts = new ConcurrentSkipListSet<>((ConcurrentSkipListSet<Post>) inputFromFile.readObject());
            inputFromFile.close();
            fileInputStream.close();
        } catch (EOFException | StreamCorruptedException e) {
            ServerEXE.posts = new ConcurrentSkipListSet<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void updateDataBase() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(ACCOUNTS_FILE);
            ObjectOutputStream objToFile = new ObjectOutputStream(fileOutputStream);
            objToFile.writeObject(ServerEXE.accounts);
            objToFile.close();
            fileOutputStream.close();

            fileOutputStream = new FileOutputStream(POSTS_FILE);
            objToFile = new ObjectOutputStream(fileOutputStream);
            objToFile.writeObject(ServerEXE.posts);
            objToFile.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
