import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class User2 {

    public static void main(String[] args){
        try{
            privateServerUser2 s = new privateServerUser2(8001, 100);
            Thread t = new Thread(s);
            t.start();

            DatagramSocket otherUser = new DatagramSocket();
            InetAddress IPaddress = InetAddress.getByName("localhost");
            byte[] data;

            Scanner scan = new Scanner(System.in);

            while(1==1){
                String send = scan.nextLine();
                data = send.getBytes();
                otherUser.send(new DatagramPacket(data, data.length, IPaddress, 8002));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

class privateServerUser2 implements Runnable{
    byte[] data;
    DatagramSocket socket;

    public privateServerUser2(int socketN, int size) throws SocketException {
        data = new byte[size];
        socket = new DatagramSocket(socketN);
    }

    @Override
    public void run() {

        while(true){
            DatagramPacket inFromClient = new DatagramPacket(data, data.length);
            try {
                socket.receive(inFromClient);
                String message = new String(inFromClient.getData(), 0, inFromClient.getLength());
                System.out.println(inFromClient.getAddress().getHostAddress() + " says " + message);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}