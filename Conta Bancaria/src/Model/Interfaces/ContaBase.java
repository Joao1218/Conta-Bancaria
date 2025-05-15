public interface ContaBase {
     public Boolean Saque(Double valor, String caminho);
     public Boolean deposito (Double valor, String caminho);
     public void transferir (Double valor, String caminho, String contaRecebedora);
     public double exibirSaldo(String caminho);
     public void extrato(String caminho);
}
