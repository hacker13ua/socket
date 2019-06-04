import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class FileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        OutputStream os = socket.getOutputStream();
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(
                        "/home/esurovskiy/cat.jpeg"));

        final byte[] bytes = new byte[1024];
        int readBytes;
        while ((readBytes = bis.read(bytes)) != -1) {
            os.write(bytes, 0, readBytes);
        }
        os.flush();
        os.close();
    }
}
