import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8090);
        System.out.println("Waiting client 1");
        Socket clientSocket1 = serverSocket.accept();
        System.out.println("Client connected 1 " +
                clientSocket1.getInetAddress());

        System.out.println("Waiting client 2");
        Socket clientSocket2 = serverSocket.accept();
        System.out.println("Client connected 2 "
                + clientSocket2.getInetAddress());

        DataInputStream is1 = new DataInputStream(
                clientSocket1.getInputStream());
        DataOutputStream os1 = new DataOutputStream(
                clientSocket1.getOutputStream());
        DataInputStream is2 = new DataInputStream(
                clientSocket2.getInputStream());
        DataOutputStream os2 = new DataOutputStream(
                clientSocket2.getOutputStream());

        while (true) {
            String textFromClient = is1.readUTF();
            System.out.println("Client1: " + textFromClient);
            os2.writeUTF("(" + textFromClient + ")");
            os2.flush();

            textFromClient = is2.readUTF();
            System.out.println("Client2: " + textFromClient);
            os1.writeUTF("(" + textFromClient + ")");
            os1.flush();
        }
    }
}
