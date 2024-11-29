import javax.swing.*;
import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             DataInputStream input = new DataInputStream(socket.getInputStream())) {

            String xStr = JOptionPane.showInputDialog("Insira a coordenada x:");
            String yStr = JOptionPane.showInputDialog("Insira a coordenada y:");

            double x = Double.parseDouble(xStr);
            double y = Double.parseDouble(yStr);

            output.writeDouble(x);
            output.writeDouble(y);

            double r = input.readDouble();
            double theta = input.readDouble();

            JOptionPane.showMessageDialog(null, "Coordenadas polares:\n" + "r = " + r + "\nθ = " + theta + " radianos");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
