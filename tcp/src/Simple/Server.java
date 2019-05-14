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
            while(true){
                clientSocket = serverSocket.accept();
                System.out.println("Connected");

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String textFromClient = null;
                String textToClient = null;
                textFromClient = in.readLine(); // read the text from client
                System.out.println("Read '" + textFromClient + "'");
                if ("A".equals(textFromClient))
                {
                    textToClient = "1111";
                }
                else if ("B".equals(textFromClient))
                {
                    textToClient = "2222\r\n3333";
                }

                System.out.println("Writing '" + textToClient + "'");
                out.print(textToClient + "\r\n"); // send the response to client
                out.flush();
                out.close();
                in.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
