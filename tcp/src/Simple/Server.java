package Simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try
        {
            serverSocket = new ServerSocket(1234);

            Socket clientSocket = null;
            clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Connected to client " + clientSocket.getInetAddress().getHostAddress());
            while(true){
                String textFromClient = null;
                String textToClient = null;
                textFromClient = in.readLine();
                System.out.println("Received '" + textFromClient + "'" + " at " + System.nanoTime());

                if (textFromClient.contains("Hello") || textFromClient.contains("Hi"))
                {
                    textToClient = "I am Groot!";
                }
                else if (textFromClient.contains("Thanks") || textFromClient.contains("Welcome"))
                {
                    textToClient = "I am Grooot!";
                }
                else if(textFromClient.equals("End")){
                    textToClient = "I am groot...";
                    in.close();
                    out.close();
                }
                else{
                    textToClient = "I am groot.";
                }

                System.out.println("Writing '" + textToClient + "'");
                out.print(textToClient + "\r\n"); // send the response to client
                out.flush();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
