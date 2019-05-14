package Simple;

import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPServer = InetAddress.getByName("localhost");
        byte[] sendData;
        sendData = ("Hello Friend").getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPServer, 5000);

        long as = System.nanoTime();
        System.out.println("Mensagem Enviada em " + System.nanoTime());
        clientSocket.send(sendPacket);
        byte[] recieveData = new byte[100];
        DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
        clientSocket.receive(recievePacket);
        System.out.println("Recebeu do servidor :" + new String(recievePacket.getData()));
        System.out.println("RTT: " + (System.nanoTime() - as) / 1000 + " micro segundos");
        clientSocket.close();
    }
}
