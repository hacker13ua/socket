import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("172.30.2.224",
                8888);
        Scanner scanner = new Scanner(System.in);
        new ClientWriteMsg(socket, scanner).start();
        new ClientReadMsg(socket).start();
    }
}
