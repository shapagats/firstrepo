import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TCPClient {
    public static void main(String[] args) {
        String serverIP = "localhost";
        int port = 9091;

        try (Socket tcp = new Socket()) {
            tcp.connect(new InetSocketAddress(serverIP, port), 1000);
            tcp.setSoTimeout(1000); 
            BufferedReader in = new BufferedReader(new InputStreamReader(tcp.getInputStream()));
            PrintWriter out = new PrintWriter(tcp.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            boolean menu = true;
            while (menu) {

                System.out.println("Choose Option:\n");
                System.out.println("  PUT <key> <value>");
                System.out.println("  GET <key>");
                System.out.println("  CHANGE <key> <new value>");
                System.out.println("  DELETE <key>");
                System.out.println("  KEYS");
                System.out.println("  ALL_KEYS");
                System.out.println("  QUIT");
                System.out.print("Enter Command: ");
                String command = scanner.nextLine().trim();

                out.println(command);

                if (command.equals("QUIT")) {
                    menu = false;
                } else {
                    String response = in.readLine();
                    if (response != null && response.equals("Invalid command")) {
        
                        LocalDateTime currentTime = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String formattedTime = currentTime.format(formatter);
                        System.out.println("[" + formattedTime + "] Wrong input error. Please check your command.");
                    } else {
                        System.out.println(response);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
