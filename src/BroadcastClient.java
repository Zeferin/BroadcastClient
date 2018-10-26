import java.io.IOException;

public class BroadcastClient {

    public static void main(String args[])
    {
        try {
            Client client = new Client("localhost",14449);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
