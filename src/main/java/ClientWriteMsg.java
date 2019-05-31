import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriteMsg extends Thread {
    private Socket socket;
    private Scanner scanner;

    public ClientWriteMsg(Socket socket,
                          Scanner scanner) {
        this.socket = socket;
        this.scanner = scanner;
    }

    @Override
    public void run() {
        try {
            DataOutputStream outputStream =
                    new DataOutputStream(socket.getOutputStream());

            while (true) {
                final String text = scanner.nextLine();
                outputStream.writeUTF(text);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
