import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        byte[] bytes = new byte[1024];
        while (true) {
            DatagramPacket datagramPacket =
                    new DatagramPacket(bytes, bytes.length);
            datagramSocket.receive(datagramPacket);
            final String text = new String(datagramPacket.getData(),
                    0,
                    datagramPacket.getLength());
            final InetAddress address = datagramPacket.getAddress();
            System.out.println(datagramPacket.getLength());
            System.out.println(text);
        }
    }
}
