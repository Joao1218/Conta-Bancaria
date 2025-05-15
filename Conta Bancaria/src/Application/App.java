import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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
                System.out.println("<-------------- Login de Usuario -------------->");
                System.out.println("Email: ");
                String email = sc.nextLine();
                System.out.println("Senha: ");
                Integer senha = sc.nextInt();
                if(Autenticacao.login(email, senha, path)){
                    System.out.println("<-------------- Bem vindo a sua conta "+Autenticacao.nome(email, path)+" -------------->");
                }
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
                if (Autenticacao.usuarioExiste(cpf, path)) {
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
}
