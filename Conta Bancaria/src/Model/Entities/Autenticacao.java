import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Autenticacao {
    public static boolean usuarioExiste(Long cpf, String caminhoArquivo) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (linha.trim().isEmpty()) continue; 
                String[] dados = linha.split(",");
                if (dados.length >= 3) { 
                    try {
                        Long cpfArquivo = Long.parseLong(dados[2].trim());
                        if (cpfArquivo.equals(cpf)) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("CPF inválido na linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return false;
    }
    public static boolean login(String email, Integer senha, String caminhoArquivo){
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (linha.trim().isEmpty()) continue; 
                String[] dados = linha.split(",");
                if (dados.length >= 3) { 
                    try {
                        String emailArquivo = dados[1].trim();
                        Integer senhaArquivo = Integer.parseInt(dados[3].trim());
                        if (emailArquivo.equalsIgnoreCase(email) || senhaArquivo.equals(senha)) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("CPF inválido na linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return false;
    }
    public static String nome(String email, String caminhoArquivo){
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (linha.trim().isEmpty()) continue; 
                String[] dados = linha.split(",");
                if (dados.length >= 3) { 
                    try {
                        String emailArquivo = dados[1].trim();
                        if (emailArquivo.equalsIgnoreCase(email)) {
                            return dados[0];
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("CPF inválido na linha: " + linha);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return "Usuario não encontrado";
    }
}
