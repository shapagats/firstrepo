import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TCPServer {
    private static final int serverPort = 9091;
    private static final int threadSize = 5;
    private static Map<String, String> keyNvalue = new HashMap<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(threadSize);

        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println("TCP Server started on port " + serverPort);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected TCP client: " + clientSocket.getInetAddress());

                executor.execute(new Server(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static class Server implements Runnable {
        private final Socket clientSocket;

        public Server(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            LocalDateTime currentTime = null;
            DateTimeFormatter formatter = null;
            String formattedTime = null;

            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("From client: " + inputLine);
                    String[] tokens = inputLine.split(" ");

                    if (tokens.length < 1) {
                        out.println("Invalid command");
                        continue;
                    }

                    String command = tokens[0];
                    String key = tokens.length > 1 ? tokens[1] : "";

                    switch (command) {
                        case "PUT":
                            if (tokens.length < 3) {
                                currentTime = LocalDateTime.now();
                                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                formattedTime = currentTime.format(formatter);
                                out.println("[" + formattedTime + "] Invalid PUT command. Expected format: PUT <key> <value>");
                                break;
                            }
                            String value = tokens[2];
                            synchronized (keyNvalue) {
                                if (keyNvalue.containsKey(key)) {
                                    out.println("Key already exists. Enter new one");
                                } else {
                                    keyNvalue.put(key, value);
                                    out.println("Successfully received data for key: " + key);
                                }
                            }
                            break;

                        case "GET":
                            if (tokens.length < 2) {
                                currentTime = LocalDateTime.now();
                                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                formattedTime = currentTime.format(formatter);
                                out.println("[" + formattedTime + "] Invalid PUT command. Expected format: GET <key>");
                                break;
                            }
                            String getKey = tokens[1];
                            synchronized (keyNvalue) {
                                String retrievedValue = keyNvalue.get(getKey);
                                if (retrievedValue != null) {
                                    out.println("Value for key " + getKey + ": " + retrievedValue);
                                } else {
                                    out.println("Key does not exist.");
                                }
                            }
                            break;

                        case "CHANGE":
                            if (tokens.length < 3) {
                                currentTime = LocalDateTime.now();
                                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                formattedTime = currentTime.format(formatter);
                                out.println("[" + formattedTime + "] Invalid PUT command. Expected format: CHANGE <key> <new value>");
                                break;
                            }
                            String changeKey = tokens[1];
                            String changeValue = tokens[2];
                            synchronized (keyNvalue) {
                                if (!keyNvalue.containsKey(changeKey)) {
                                    out.println("Key does not exist.");
                                } else {
                                    keyNvalue.put(changeKey, changeValue);
                                    out.println("Value for key " + changeKey + " changed to: " + changeValue);
                                }
                            }
                            break;

                        case "DELETE":
                            if (tokens.length < 2) {
                                currentTime = LocalDateTime.now();
                                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                formattedTime = currentTime.format(formatter);
                                out.println("[" + formattedTime + "] Invalid PUT command. Expected format: DELETE <key>");
                                break;
                            }
                            String keyToDelete = tokens[1];
                            synchronized (keyNvalue) {
                                if (!keyNvalue.containsKey(keyToDelete)) {
                                    out.println("Key does not exist.");
                                } else {
                                    keyNvalue.remove(keyToDelete);
                                    out.println("Deleted successfully");
                                }
                            }
                            break;

                        case "KEYS":
                            synchronized (keyNvalue) {
                                if (!keyNvalue.isEmpty()){
                                out.println(keyNvalue.keySet());
                            } else{
                                out.println("key list is for now");
                            }
                        }
                            break;

                        case "ALL_KEYS":
                            synchronized (keyNvalue) {
                                StringBuilder responseBuilder = new StringBuilder();
                                for (Object keys : keyNvalue.keySet()) {
                                    String stringKey = String.valueOf(keys);
                                    Object values = keyNvalue.get(keys);
                                    String stringValue = String.valueOf(values);
                                    responseBuilder.append(stringKey).append(": ").append(stringValue).append(", ");
                                }

                                if (!keyNvalue.isEmpty()){
                                    responseBuilder.delete(responseBuilder.length() - 2, responseBuilder.length());
                                }
                                else{
                                    out.println("List is empty for now");
                                }
                                out.println(responseBuilder.toString());
                            }
                            break;

                        case "QUIT":
                            out.println("bye!");
                            return; 
                        default:
                            out.println("Invalid command");
                    }
                }
            } catch (IOException e) {
                currentTime = LocalDateTime.now();
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                formattedTime = currentTime.format(formatter);
                System.out.println("[" + formattedTime + "] Error: " + e.getMessage());
            }
        }
    }
}






