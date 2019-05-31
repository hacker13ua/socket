import java.io.DataInputStream;
import java.net.Socket;

public class ClientReadMsg extends Thread {
    private Socket socket;

    public ClientReadMsg(final Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream =
                    new DataInputStream(socket.getInputStream());
            while (true) {
                final String readText = inputStream.readUTF();
                System.out.println(readText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
