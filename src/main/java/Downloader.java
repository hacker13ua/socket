import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Downloader {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8080);
        InputStream is = socket.getInputStream();
        FileOutputStream fileOutputStream
                = new FileOutputStream("/home/esurovskiy/cat_copy.jpg");
        final byte[] bytes = new byte[1024];
        int readBytes;
        while ((readBytes = is.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, readBytes);
        }
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
