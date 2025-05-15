import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = "C:\\Users\\João Henrique\\Documents\\Projeto Conta Bancaria\\Conta-Bancaria\\BaseDeDados\\Usuario.txt";
        String pathPasta = "C:\\Users\\João Henrique\\Documents\\Projeto Conta Bancaria\\Conta-Bancaria\\BaseDeDados\\Usuarios\\";
        System.out.println("<-------------- Bem Vindo a Sua Conta -------------->");
        System.out.println("[1] - Fazer Login");
        System.out.println("[2] - Se Cadastrar");
        System.out.println("[3] - Sair");
        Integer resposta = sc.nextInt();
        sc.nextLine();
        while (!resposta.equals(3)) {
            if (resposta.equals(1)) {

            } else if (resposta.equals(2)) {
                System.out.println("<-------------- Cadastro de Usuario -------------->");
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Cpf: ");
                Long cpf = sc.nextLong();
                System.out.println("Tipo de conta:");
                System.out.println("[1] Corrente");
                System.out.println("[2] Poupança");
                Integer tipoConta = sc.nextInt();
                System.out.print("Senha: ");
                Integer senha = sc.nextInt();
                Usuario usuario = new Usuario(nome, email, cpf, senha);
                if (usuarioExiste(cpf, path)) {
                    System.out.println("Usuario já cadastrado");
                } else {
                    try (BufferedWriter bw = new BufferedWriter(
                            new OutputStreamWriter(new FileOutputStream(path, true), StandardCharsets.UTF_8))) {
                        bw.write(usuario.toString());
                        bw.newLine();
                        String caminho = pathPasta + nome;
                        File pasta = new File(caminho);
                        Boolean retorno = pasta.mkdir();
                        if (tipoConta == 1) {
                            String caminhoArquivo = pathPasta + nome + File.separator + "ContaCorrente.txt";
                            File TipoConta = new File(caminhoArquivo);
                            TipoConta.createNewFile();
                        } else if (tipoConta == 2) {
                            String caminhoArquivo = pathPasta + nome + File.separator + "ContaPoupanca.txt";
                            File TipoConta = new File(caminhoArquivo);
                            TipoConta.createNewFile();
                        }
                        if (retorno != null) {
                            System.out.println("Usuario cadastrado com sucesso");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("<-------------- Bem Vindo a Sua Conta -------------->");
            System.out.println("[1] - Fazer Login");
            System.out.println("[2] - Se Cadastrar");
            System.out.println("[3] - Sair");
            resposta = sc.nextInt();
            sc.nextLine();
        }
        System.out.println("<-------------- Fim -------------->");
        sc.close();
    }
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
}
