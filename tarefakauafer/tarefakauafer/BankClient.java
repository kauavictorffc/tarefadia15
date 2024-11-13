import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BankClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Digite o número da conta: ");
            String numeroConta = scanner.nextLine();

            System.out.print("Digite a operação (saque/deposito): ");
            String tipoOperacao = scanner.nextLine();

            System.out.print("Digite o valor: ");
            double valor = scanner.nextDouble();

            out.println(numeroConta + "," + tipoOperacao + "," + valor);

            String resposta = in.readLine();
            System.out.println("Resposta do servidor: " + resposta);
        } catch (IOException e) {
            System.out.println("Erro no cliente: " + e.getMessage());
        }
    }
}
