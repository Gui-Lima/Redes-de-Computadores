package Simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

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

            while(true){
                String textToServer;
                textToServer = read.readLine();
                long t1 = System.nanoTime();
                System.out.println("Sending '" + textToServer + "'" + " at " + System.nanoTime());
                out.print(textToServer + "\r\n"); // send to server
                out.flush();

                String serverResponse = null;
                serverResponse = in.readLine();
                long RTT = System.nanoTime() - t1;
                RTT = TimeUnit.MICROSECONDS.convert(RTT, TimeUnit.NANOSECONDS);
                System.out.println(serverResponse + " RTT (microseconds) :" + RTT); // read from server and print it.
                if(serverResponse == null){
                    in.close();
                    out.close();
                    socket.close();
                    break;
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
