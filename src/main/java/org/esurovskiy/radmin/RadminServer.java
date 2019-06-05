package org.esurovskiy.radmin;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RadminServer {
    public static void main(String[] args) throws Exception {
//        new RadminServer().startServer(window.getGraphics());
    }

    public void startServer(final Graphics graphics, final RadminServerWindow window) throws Exception {
        ServerSocket serverSocket =
                new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        System.out.println("Client connected");
        DataInputStream is = new DataInputStream(
                socket.getInputStream());
        DataOutputStream os = new DataOutputStream(
                socket.getOutputStream());
        while (true) {
            os.writeUTF("GET");
            System.out.println("Send get command");

            final int length = is.readInt();
            if(length<=0){
                continue;
            }
            final byte[] bytes = new byte[length];
            is.read(bytes,0, length);
            final ByteArrayInputStream bis =
                    new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(bis);
            System.out.println("Draw image");

            graphics.drawImage(bufferedImage,
                    0, 0, window);

//            File outputfile = new File("/home/esurovskiy/image.jpg");
//            ImageIO.write(bufferedImage, "jpg", outputfile);

            Thread.sleep(1000);
        }
    }
}
