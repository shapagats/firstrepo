import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        int serverPort = 9091;
        int timeout = 1000;

        try (DatagramSocket udp = new DatagramSocket()) {
            udp.setSoTimeout(timeout);
            InetAddress serverAddress = InetAddress.getByName("localhost");

            while (true) {
                try {
                    
                    System.out.println("Choose Option:\n");
                    System.out.println("  PUT <key> <value>");
                    System.out.println("  GET <key>");
                    System.out.println("  CHANGE <key> <new value>");
                    System.out.println("  DELETE <key>");
                    System.out.println("  KEYS");
                    System.out.println("  ALL_KEYS");
                    System.out.println("  QUIT\n");
                    System.out.print("Enter Command: ");
                    Scanner scanner = new Scanner(System.in);
                    String command = scanner.nextLine().trim();

                   
                    byte[] sendData = command.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                    udp.send(sendPacket);

                 
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    udp.receive(receivePacket);
                    String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println(response);
                } catch (SocketTimeoutException e) {
                    System.out.println("Timeout occurred.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
