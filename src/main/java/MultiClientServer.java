import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiClientServer {
    public static void main(String[] args) throws Exception {
        List<ClientThread> allClients =
                new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            System.out.println("Wait client");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            ClientThread clientThread = new ClientThread(socket,
                    allClients);
            allClients.add(clientThread);
        }
    }
}
