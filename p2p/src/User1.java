import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class User1 {

    public static void main(String[] args){
        try{
            privateServerUser1 s = new privateServerUser1(8002, 100);
            Thread t = new Thread(s);
            t.start();

            DatagramSocket otherUser = new DatagramSocket();
            InetAddress IPaddress = InetAddress.getByName("localhost");
            byte[] data;

            System.out.println("AA");
            Scanner scan = new Scanner(System.in);

            while(1==1){
                String send = scan.nextLine();
                data = send.getBytes();
                otherUser.send(new DatagramPacket(data, data.length, IPaddress, 8001));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

class privateServerUser1 implements Runnable{
    byte[] data;
    DatagramSocket socket;

    public privateServerUser1(int socketN, int size) throws SocketException {
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