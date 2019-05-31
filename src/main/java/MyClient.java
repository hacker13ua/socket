import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);

        DataOutputStream outputStream =
                new DataOutputStream(socket.getOutputStream());
        DataInputStream inputStream =
                new DataInputStream(socket.getInputStream());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            outputStream.writeUTF(line);
            outputStream.flush();

            String serverAnswer = inputStream.readUTF();
            System.out.println(serverAnswer);
        }
    }
}
