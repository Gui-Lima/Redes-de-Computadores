package Simple;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    static int originPort = 5000;

    public static void main(String[] args) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(originPort);

        byte[] recieveData = new byte[100];
        byte[] senddata;
        InetAddress clientIP;
        int port;

        while(true){
            DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
            System.out.println("Waiting in port " + originPort + "...");
            serverSocket.receive(recievePacket);
            String response = new String(recieveData);
            System.out.println("Package Recieved!\n" + "Content: " + response);
            clientIP = recievePacket.getAddress();
            port = recievePacket.getPort();
            senddata = ("2").getBytes();
            DatagramPacket sendPacket = new DatagramPacket(senddata, senddata.length, clientIP, port);
            serverSocket.send(sendPacket);
        }
    }


}
