import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FuncionalidadesConta {
     public static Double lerSaldo(String caminhoArquivo) {
    try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
        String linhaSaldo = leitor.readLine();  
        if (linhaSaldo != null && linhaSaldo.startsWith("Saldo: ")) {
            String valorSaldo = linhaSaldo.substring(7).trim();
            if (valorSaldo.equalsIgnoreCase("null") || valorSaldo.isEmpty()) {
                System.out.println("Saldo inválido no arquivo.");
                return null;
            }
            return Double.parseDouble(valorSaldo);
        } else {
            System.out.println("Formato da linha saldo incorreto.");
        }
    } catch (IOException | NumberFormatException e) {
        System.out.println("Erro ao ler saldo: " + e.getMessage());
    }
    return null;
}
public void salvarConta(String caminhoArquivo) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, false))) {
        writer.write(this.toString());
        writer.newLine();
    } catch (IOException e) {
        System.out.println("Erro ao salvar conta: " + e.getMessage());
    }
}
public static List<String> lerExtrato(String caminho) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'lerExtrato'");
}
}
