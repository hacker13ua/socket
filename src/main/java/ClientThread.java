import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ClientThread extends Thread {
    private Socket socket;
    private List<ClientThread> allClients;
    private DataInputStream is;
    private DataOutputStream os;
    private String nickName;

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
        try {
            nickName = is.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                String text = is.readUTF();
                System.out.println("Text from " +
                        nickName + " " + text);
                final Iterator<ClientThread> iterator =
                        allClients.iterator();
                while (iterator.hasNext()) {
                    final ClientThread client = iterator.next();
                    if (client.socket.isClosed()) {
                        iterator.remove();
                        client.socket.close();
                    }
                }
                for (ClientThread client : allClients) {
                    if (client != this) {
                        client.os.writeUTF(new Date() +
                                " " + nickName
                                + ": " + text);
                        client.os.flush();
                    }
                }
            } catch (Exception e) {
                try {
                    System.out.println(nickName + " вышел из чата");
                    socket.close();
                    allClients.remove(socket);
                    for (ClientThread client : allClients) {
                        client.os.writeUTF(
                                nickName + " вышел из чата");
                        client.os.flush();
                    }

                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
