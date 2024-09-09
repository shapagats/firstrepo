import java.io.*;
import java.net.*;
import java.util.*;

public class KeyValueStoreServer {
    private static final int TCP_PORT = 9091;
    private static final int UDP_PORT = 9091;


    private HashMap<String, String> keyValueStore;

    public KeyValueStoreServer() {
        keyValueStore = new HashMap<>();
    }

    
    private synchronized void put(String key, String value) {
        keyValueStore.put(key, value);
    }

    // Method to handle GET operation
    private synchronized String get(String key) {
        return keyValueStore.get(key);
    }

    // Method to handle DELETE operation
    private synchronized void delete(String key) {
        keyValueStore.remove(key);
    }

    // Method to handle KEYS operation
    private synchronized Set<String> keys() {
        return keyValueStore.keySet();
    }

    // Method to handle TCP client requests
    private void handleTCPClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String request;
            while ((request = in.readLine()) != null) {
                String[] tokens = request.split(" ");
                String command = tokens[0];

                switch (command) {
                    case "PUT":
                        // Handle PUT command
                        break;
                    case "GET":
                        // Handle GET command
                        break;
                    case "DELETE":
                        // Handle DELETE command
                        break;
                    case "KEYS":
                        // Handle KEYS command
                        break;
                    default:
                        out.println("Invalid command");
                        break;
                }
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to handle UDP client requests
    private void handleUDPClient(DatagramSocket serverSocket) {
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        try {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            InetAddress clientIPAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            String request = new String(receivePacket.getData(), 0, receivePacket.getLength());
            String[] tokens = request.split(" ");
            String command = tokens[0];

            switch (command) {
                case "PUT":
                    // Handle PUT command
                    break;
                case "GET":
                    // Handle GET command
                    break;
                case "DELETE":
                    // Handle DELETE command
                    break;
                case "KEYS":
                    // Handle KEYS command
                    break;
                default:
                    sendData = "Invalid command".getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIPAddress, clientPort);
                    serverSocket.send(sendPacket);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to start TCP server
    private void startTCPServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(TCP_PORT);
            System.out.println("TCP Server started on port " + TCP_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New TCP client connected: " + clientSocket.getInetAddress().getHostAddress());
                new Thread(() -> handleTCPClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to start UDP server
    private void startUDPServer() {
        try {
            DatagramSocket serverSocket = new DatagramSocket(UDP_PORT);
            System.out.println("UDP Server started on port " + UDP_PORT);

            while (true) {
                handleUDPClient(serverSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        KeyValueStoreServer server = new KeyValueStoreServer();
        new Thread(server::startTCPServer).start();
        new Thread(server::startUDPServer).start();
    }
}
