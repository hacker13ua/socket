import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ClientThread extends Thread {
    private Socket socket;
    private List<ClientThread> allClients;
    private DataInputStream is;
    private DataOutputStream os;

    public ClientThread(final Socket socket,
                        final List<ClientThread> allClients) {
        this.socket = socket;
        this.allClients = allClients;
        try {
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }

    @Override
    public void run() {
        System.out.println("Run thread for client" +
                socket.getInetAddress());
        while (true) {
            try {
                String text = is.readUTF();
                System.out.println("Text from "+
                        socket.getInetAddress() + " " + text);
                for (ClientThread client : allClients) {
                    if (client != this) {
                        client.os.writeUTF(text);
                        client.os.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
