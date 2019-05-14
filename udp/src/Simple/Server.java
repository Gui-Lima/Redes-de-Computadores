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
            String send;
            if (isHighElf(response)){
                send = new String("Ni *'lassui".getBytes(), 0, "Ni *'lassui".length());
            }
            else{
                send = new String("I only understand High Elf, peasant".getBytes(), 0, "I only understand High Elf, peasant".length());
            }
            System.out.println("Package Recieved!\n" + "Time: " + System.nanoTime() + "\n" + "Content: " + response);
            clientIP = recievePacket.getAddress();
            port = recievePacket.getPort();
            senddata = send.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(senddata, senddata.length, clientIP, port);
            serverSocket.send(sendPacket);
        }
    }

    private static boolean isHighElf(String s) {
        return (s.contains("Êl síla erin lû e-govaned vîn.") ||  s.contains("Gi suilon!") || s.contains("Pedig edhellen?"));
    }
}
