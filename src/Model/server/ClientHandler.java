package Model.server;

import Model.common.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {
    private Socket userSocket;
    private ObjectOutputStream socketOut;
    private ObjectInputStream socketIn;
    public Boolean clientIsOnline = true;

    public ClientHandler(Socket socket) {
        try {
            userSocket = socket;
            this.socketIn = new ObjectInputStream(userSocket.getInputStream());
            this.socketOut = new ObjectOutputStream(userSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void run() {

        while (clientIsOnline) {
            Map<String, Object> input = null;

            try {
                input = (Map<String, Object>) socketIn.readObject();
                Map<String, Object> answer = null;
                Command command = (Command) input.get("command");
                switch (command) {
                    case SIGNUP:
                        answer = API.signUp(input);
                        break;
                    case IS_USERNAME_UNIQUE:
                        answer = API.doesUserNameExist(input);
                        break;
                    case LOGIN:
                        answer = API.login(input);
                        break;
                    case FORGOTTEN_PASSWORD:
                        answer = API.forgotten_password(input);
                }
                socketOut.writeObject(answer);
                socketOut.flush();
            } catch (ClassCastException | ClassNotFoundException e) {
            } catch (EOFException e) {
                break;
            } catch (IOException e) {
                break;
            }

        }
        try {
            socketIn.close();
            socketOut.close();
            userSocket.close();
        } catch (IOException e) {
        }

    }

}
