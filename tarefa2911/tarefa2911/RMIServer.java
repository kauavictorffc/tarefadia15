import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class RMIServer extends UnicastRemoteObject implements ConverterInterface {
    public RMIServer() throws RemoteException {}

    @Override
    public double[] converterParaPolar(double x, double y) throws RemoteException {
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);
        return new double[]{r, theta};
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/Converter", new RMIServer());
            System.out.println("Servidor RMI iniciado...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
