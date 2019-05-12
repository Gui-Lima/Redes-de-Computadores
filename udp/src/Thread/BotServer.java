package Thread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class BotServer implements Runnable {

    private DatagramSocket socket;
    private boolean stop;
    private byte[] buffer = new byte[256];

    public BotServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
        stop = false;
    }

    @Override
    public void run() {

        while (!stop) {
            buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress receivedAddress = packet.getAddress();
            int receivedPort = packet.getPort();
            packet = new DatagramPacket(buffer, buffer.length, receivedAddress, receivedPort);
            if(isHighElf(new String(packet.getData()))){
                String received = new String("Ni *'lassui".getBytes(), 0, "Ni *'lassui".length());
            }
            else {
                String received = new String("I only understand High Elf, peasant".getBytes(), 0, "I only understand High Elf, peasant".length());
            }
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

    private boolean isHighElf(String s) {
        return (s.equals("Êl síla erin lû e-govaned vîn.") ||  s.equals("Gi suilon!") || s.equals("Pedig edhellen?"));
    }


}
