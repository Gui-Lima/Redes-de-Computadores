import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class BotServer extends Thread {

    private DatagramSocket socket;
    private boolean stop;
    private byte[] buffer = new byte[256];

    public BotServer() throws SocketException {
        socket = new DatagramSocket(5000);
        stop = false;
    }

    public void run() {

        while (!stop) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress receivedAddress = packet.getAddress();
            int receivedPort = packet.getPort();
            packet = new DatagramPacket(buffer, buffer.length, receivedAddress, receivedPort);
            String received = new String(packet.getData(), 0, packet.getLength());

            if (received.equals("stop")) {
                stop = true;
                continue;
            }

            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
