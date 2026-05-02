import java.io.*;
import java.net.*;
import java.util.*;

// MAIN CLASS
public class ChatApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Start Server");
        System.out.println("2. Start Client");
        System.out.print("Choose: ");
        int choice = sc.nextInt();

        if (choice == 1) {
            startServer();
        } else {
            startClient();
        }
    }

    // ================= SERVER =================
    static Set<ClientHandler> clients = new HashSet<>();

    public static void startServer() {
        System.out.println("Server started...");

        // 🔹 Thread for server to send messages
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                String msg = sc.nextLine();
                broadcast("SERVER: " + msg, null);
            }
        }).start();

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                ClientHandler client = new ClientHandler(socket);
                clients.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            // If sender is null (server), send to all
            if (sender == null || client != sender) {
                client.sendMessage(message);
            }
        }
    }

    // ================= CLIENT HANDLER =================
    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(
                        socket.getOutputStream(), true);

                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println("Received: " + msg);
                    broadcast(msg, this);
                }
            } catch (IOException e) {
                System.out.println("Client disconnected");
            } finally {
                clients.remove(this);
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }

        public void sendMessage(String msg) {
            out.println(msg);
        }
    }

    // ================= CLIENT =================
    public static void startClient() {
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("Connected to server!");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);

            // 🔹 Thread to receive messages
            new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server");
                }
            }).start();

            // 🔹 Send messages
            while (true) {
                String msg = sc.nextLine();
                out.println(msg);
            }

        } catch (IOException e) {
            System.out.println("Connection failed!");
        }
    }
}