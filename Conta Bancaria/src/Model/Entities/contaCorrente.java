import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class contaCorrente implements ContaBase {
    private Double saldo;
    private List<String> extrato = new ArrayList<>();
    public contaCorrente() {
    this.saldo = 0.0;
    this.extrato = new ArrayList<>();
    }

    public contaCorrente(Double saldo, List<String> extrato) {
        this.saldo = saldo;
        this.extrato = extrato;
    }
    public Boolean Saque(Double valor, String caminho) {
        Double saldoAtual = FuncionalidadesConta.lerSaldo(caminho);
        if (saldoAtual == null) {
            System.out.println("Não foi possível ler o saldo.");
            return false;
        }
        if (valor > saldoAtual) {
            System.out.println("Saldo insuficiente para saque.");
            return false;
        }
        List<String> extrato = FuncionalidadesConta.lerExtrato(caminho);
        String saqueExtrato = "Saque de "+valor;
        saldoAtual -= valor;
        this.saldo = saldoAtual; 
        extrato.add(saqueExtrato);
        StringBuilder extratoTexto = new StringBuilder();
        for(String linha : extrato){
            extratoTexto.append(linha).append("\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, false))) {
            writer.write("Saldo: " + saldoAtual + "\nExtrato:"+extratoTexto.toString());
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o saldo no arquivo: " + e.getMessage());
            return false;
        }
        return true; 
    }
    public Boolean deposito(Double valor, String caminho){
        Double saldoAtual = FuncionalidadesConta.lerSaldo(caminho);
        if (saldoAtual == null) {
            System.out.println("Não foi possível ler o saldo.");
            return false;
        }
        List<String> extrato = FuncionalidadesConta.lerExtrato(caminho);
        String depositoDescricao = "Depósito de " + valor;
        saldoAtual += valor;
        this.saldo = saldoAtual;

        extrato.add(depositoDescricao);
        StringBuilder extratoTexto = new StringBuilder();
        for(String linha : extrato){
            extratoTexto.append(linha).append("\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, false))) {
            writer.write("Saldo: " + saldoAtual + "\nExtrato:\n" + extratoTexto.toString());
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o saldo no arquivo: " + e.getMessage());
            return false;
        }
        return true; 
    }

     public void transferir (Double valor, String caminho, String contaRecebedora){

     };
     public double exibirSaldo(String caminho){
        return 0.0;
     };
     public void extrato(String caminho){

     };
     @Override
    public String toString() {
        return "Saldo: " + saldo + "\n" + "Extrato: " + extrato;
    }

    public static List<String> lerExtrato(String caminhoArquivo) {
        List<String> extrato = new ArrayList<>();
        boolean lendoExtrato = false;

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (linha.startsWith("Extrato:")) {
                    lendoExtrato = true;
                    continue; }
                if (lendoExtrato) {
                    extrato.add(linha);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler extrato: " + e.getMessage());
        }

        return extrato;
    }

}
