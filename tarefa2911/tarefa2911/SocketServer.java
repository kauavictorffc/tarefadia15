import java.io.*;
import java.net.*;

public class SocketServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Servidor Socket iniciado...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                     DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream())) {

                    double x = input.readDouble();
                    double y = input.readDouble();

                    double r = Math.sqrt(x * x + y * y);
                    double theta = Math.atan2(y, x);

                    output.writeDouble(r);
                    output.writeDouble(theta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
