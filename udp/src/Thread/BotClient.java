package Thread;

import java.io.IOException;
import java.net.*;

public class BotClient {
        private DatagramSocket socket;
        private InetAddress address;

        private byte[] buffer;

        public BotClient() throws IOException {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
        }

        public String sendMessage(String msg, int port) throws IOException {
            buffer = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            long as = System.nanoTime();
            socket.send(packet);
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            System.out.println("RTT: " + (System.nanoTime() - as) / 1000 + "ms");
            String received = new String(packet.getData(), 0, packet.getLength());
            return received;
        }

        public void close() {
            socket.close();
        }
}
