package Thread;

import java.io.IOException;

public class Main {

    static BotClient client;

    public static void main(String[] args) throws IOException {
        setup();
        send();
        tearDown();
    }

    public static void setup(){
        try {
            Thread t1 = new Thread(new BotServer(5000));
            t1.start();
            client = new BotClient();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send() throws IOException {
        String echo = client.sendMessage("hello server", 5000);
    }

    public static void tearDown() throws IOException {
        client.close();

    }
}
