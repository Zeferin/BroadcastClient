import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    String host;
    int port;
    private SocketChannel socket;

    public Client(String host, int port)  {
        this.host = host;
        this.port = port;
        this.socket = null;
    }

    public void start() throws IOException
    {
        socket = SocketChannel.open(new InetSocketAddress(host, port));
        Thread receiver = new Thread(new ReceiverThread(socket));
        receiver.start();

        String msg;
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            try {
                msg = sc.nextLine();
                broadcastMessage(msg);
            }catch(IOException e)
            {
                break;
            }
        }
    }

    public void broadcastMessage(String msg) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        socket.write(buffer);

    }
}
