package org.esurovskiy.radmin;


import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.Rectangle;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.awt.image.BufferedImage;
import java.awt.Robot;
import java.awt.Toolkit;


public class RadminClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8080);
        DataInputStream socketIS = new DataInputStream(
                socket.getInputStream());
        DataOutputStream os = new DataOutputStream(
                socket.getOutputStream());
        while (true) {
            final String command = socketIS.readUTF();
            if (command.equals("GET")) {
                BufferedImage image = new Robot().
                        createScreenCapture(new Rectangle(
                                Toolkit.getDefaultToolkit()
                                        .getScreenSize()));
                byte[] byteArray
                        = convertToByte(image);
                os.writeInt(byteArray.length);
                os.flush();
                os.write(byteArray, 0, byteArray.length);

                System.out.println("Send screen to server");
                os.flush();
            }
        }
    }

    static byte[] convertToByte(BufferedImage image) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);
        return os.toByteArray();
    }
}
