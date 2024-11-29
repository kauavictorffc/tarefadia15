import javax.swing.*;
import java.rmi.*;

public class RMIClient {
    public static void main(String[] args) {
        try {
            ConverterInterface converter = (ConverterInterface) Naming.lookup("rmi://localhost/Converter");

            String xStr = JOptionPane.showInputDialog("Insira a coordenada x:");
            String yStr = JOptionPane.showInputDialog("Insira a coordenada y:");

            double x = Double.parseDouble(xStr);
            double y = Double.parseDouble(yStr);

            double[] resultado = converter.converterParaPolar(x, y);

            JOptionPane.showMessageDialog(null, "Coordenadas polares:\n" + "r = " + resultado[0] + "\nθ = " + resultado[1] + " radianos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
