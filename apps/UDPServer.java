import java.io.IOException;
import java.net.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UDPServer {

    private static final int serverPort = 9091;
    private static Map<String, String> keyNvalue = new HashMap<>();

    public static void main(String[] args) {

        try (DatagramSocket serverSocket = new DatagramSocket(serverPort)) {
            System.out.println("UDP Server started on port " + serverPort);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String receivedCommand = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String response = Commands(receivedCommand);
                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            String errorResponse = handleIOException(e);
            System.out.println(errorResponse);
        }
    }

    private static String Commands(String receivedCommand) {
    String[] tokens = receivedCommand.split(" ");

    if (tokens.length < 1) {
        return "Invalid command";
    }

    String command = tokens[0];
    String key = tokens.length > 1 ? tokens[1] : "";

    switch (command) {
        case "PUT":
            if (tokens.length < 3) {
                return timeError("Invalid PUT command. Expected format: PUT <key> <value>");
            }
            String value = tokens[2];
            synchronized (keyNvalue) {
                if (keyNvalue.containsKey(key)) {
                    return "Key already exists. Enter new one";
                } else {
                    keyNvalue.put(key, value);
                    return "Successfully received data for key: " + key;
                }
            }

        case "GET":
            if (tokens.length < 2) {
                return timeError("Invalid GET command. Expected format: GET <key>");
            }
            String retrievedValue = keyNvalue.get(key);
            if (retrievedValue != null) {
                return "Value for key " + key + ": " + retrievedValue;
            } else {
                return "Key does not exist.";
            }

        case "CHANGE":
            if (tokens.length < 3) {
                return timeError("Invalid CHANGE command. Expected format: CHANGE <key> <new value>");
            }
            String changeKey = tokens[1];
            String changeValue = tokens[2];
            synchronized (keyNvalue) {
                if (!keyNvalue.containsKey(changeKey)) {
                    return "Key does not exist.";
                } else {
                    keyNvalue.put(changeKey, changeValue);
                    return "Value for key " + changeKey + " changed to: " + changeValue;
                }
            }

        case "DELETE":
            if (tokens.length < 2) {
                return timeError("Invalid DELETE command. Expected format: DELETE <key>");
            }
            String keyToDelete = tokens[1];
            synchronized (keyNvalue) {
                if (!keyNvalue.containsKey(keyToDelete)) {
                    return "Key does not exist.";
                } else {
                    keyNvalue.remove(keyToDelete);
                    return "Deleted successfully";
                }
            }

        case "KEYS":
            synchronized (keyNvalue) {
                if (!keyNvalue.isEmpty()){
                return keyNvalue.keySet().toString();
            }else {
                return "key list is empty";
            }
        }

        case "ALL_KEYS":
            synchronized (keyNvalue) {
                StringBuilder responseBuilder = new StringBuilder();
                for (Map.Entry<String, String> entry : keyNvalue.entrySet()) {
                    responseBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
                }
                if (!keyNvalue.isEmpty()) {
                    responseBuilder.delete(responseBuilder.length() - 2, responseBuilder.length());
                }
                else{
                    return "List is empty for now";
                }
                return responseBuilder.toString();
            }

        case "QUIT":
            return "bye!";
            
        default:
            return timeError("Invalid command");
    }
}

        private static String timeError(String errorMessage) {

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String finalTime = currentTime.format(formatter);
          return "[" + finalTime + "] " + errorMessage;
    }


        private static String handleIOException(IOException e) {
            
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String finalTime = currentTime.format(formatter);
        return "[" + finalTime + "] Error: " + e.getMessage();
    }
}

