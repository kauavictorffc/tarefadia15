import java.io.*;
import java.net.*;
import java.util.HashMap;

public class BankServer {
    private static HashMap<String, Double> contas = new HashMap<>();

    public static void main(String[] args) {
        carregarContas("contas.txt");

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor iniciado na porta 12345...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String requisicao = in.readLine();
                    String[] partes = requisicao.split(",");
                    String numeroConta = partes[0];
                    String tipoOperacao = partes[1];
                    double valor = Double.parseDouble(partes[2]);

                    String resposta = processarOperacao(numeroConta, tipoOperacao, valor);
                    out.println(resposta);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
        }
    }

    private static void carregarContas(String arquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                contas.put(partes[0], Double.parseDouble(partes[1]));
            }
            System.out.println("Contas carregadas com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar contas: " + e.getMessage());
        }
    }

    private static String processarOperacao(String numeroConta, String tipoOperacao, double valor) {
        if (!contas.containsKey(numeroConta)) return "Conta não encontrada!";

        double saldoAtual = contas.get(numeroConta);
        if (tipoOperacao.equalsIgnoreCase("saque")) {
            if (valor > saldoAtual) return "Saldo insuficiente!";
            saldoAtual -= valor;
        } else if (tipoOperacao.equalsIgnoreCase("deposito")) {
            saldoAtual += valor;
        } else {
            return "Operação inválida!";
        }

        contas.put(numeroConta, saldoAtual);
        atualizarContas("contas.txt");
        return "Novo saldo: " + saldoAtual;
    }

    private static void atualizarContas(String arquivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(arquivo))) {
            for (String numeroConta : contas.keySet()) {
                writer.println(numeroConta + "," + contas.get(numeroConta));
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar contas: " + e.getMessage());
        }
    }
}
