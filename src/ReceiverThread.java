import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ReceiverThread implements Runnable {

    SocketChannel socket;

    ReceiverThread(SocketChannel socket)
    {
        this.socket = socket;
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(256);
        while(true) {
            try {
                socket.read(buffer);
                System.out.println(new String(buffer.array()));
                buffer.clear();
            } catch (IOException e) {
                break;
            }
        }
    }
}
