package Model.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

public class ClientNetworker {
    public static String serverAddress="127.0.0.1";
    public static final int PORT = 8888;

    private static boolean isConnected = false;
    public static Socket socket;
    public static ObjectInputStream socketIn;
    public static ObjectOutputStream socketOut;


    public static boolean isConnected(){
        return isConnected;
    }


    public static Boolean connectToServer(String[] args){
        if(socket != null) return false;
        try{
            System.out.println("server ip : " + serverAddress);
            socket = new Socket( serverAddress, PORT);
            socketOut = new ObjectOutputStream( socket.getOutputStream() );
            socketIn = new ObjectInputStream( socket.getInputStream() );
            isConnected = true;
            return true;

        }catch (ConnectException e){
        }
        catch (IOException e) {
        }
        return false;
    }



   public static Boolean disconnectFromServer(){
        try{
            socketIn.close();
            socketOut.close();
            socket.close();
            isConnected = false;

            socket = null;
            socketIn = null;
            socketOut = null;

            return true;
        }
        catch (SocketException | NullPointerException e ){
        }
        catch( Exception e){
        }
        socket = null;
        socketIn = null;
        socketOut = null;
        return false;
    }


    public static Map<String,Object> serve(Map<String,Object> toSend){
        Map<String,Object> recieved = null;
        try{
            socketOut.writeObject(toSend);
            socketOut.flush();
            socketOut.reset();
            recieved = (Map<String,Object>) socketIn.readObject();
            return recieved;

        } catch (ClassNotFoundException e){
        } catch( IOException e){
        }
        return recieved;
    }

}
