import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        InetAddress ipAddress= InetAddress.getByName("127.0.0.1");
        DatagramSocket datagramSocket = new DatagramSocket();
        String text = scanner.nextLine();
        DatagramPacket packet = new DatagramPacket(text.getBytes(),
                text.length(), ipAddress, 9999);
        datagramSocket.send(packet);
    }
}
