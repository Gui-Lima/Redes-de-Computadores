package Simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            socket = new Socket("localhost", 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connected");

            String textToServer;

            textToServer = read.readLine();
            System.out.println("Sending '" + textToServer + "'");
            out.print(textToServer + "\r\n"); // send to server
            out.flush();

            String serverResponse = null;
            while ((serverResponse = in.readLine()) != null)
                System.out.println(serverResponse); // read from server and print it.

            out.close();
            in.close();
            read.close();
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
