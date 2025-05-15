import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
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
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Senha: ");
                Integer senha = sc.nextInt();
                if (Autenticacao.login(email, senha, path)) {
                    System.out.println("<-------------- Bem vindo a sua conta " + Autenticacao.nome(email, path)
                            + " -------------->");
                    System.out.println("[1] - Sacar");
                    System.out.println("[2] - Depositar");
                    System.out.println("[3] - Ver saldo na conta");
                    System.out.println("[4] - Ver extrato");
                    System.out.println("[5] - Sair");
                    Integer respostaEscolha = sc.nextInt();
                    while (!respostaEscolha.equals(5)) {
                        String tipo = Autenticacao.tipoConta(email, path);
                        ContaBase conta = null;
                        if (tipo.equalsIgnoreCase("contaCorrente")) {
                            conta = new contaCorrente();
                        } else if (tipo.equalsIgnoreCase("contaPoupanca")) {
                            conta = new contaPoupanca();
                        } else {
                            System.out.println("Tipo de conta invalido");
                        }
                        if (conta != null) {
                            if (respostaEscolha.equals(1)) {
                                System.out.println("Qual valor você deseja sacar?");
                                Double valorSaque = sc.nextDouble();
                                String nomeUsuario = Autenticacao.nome(email, path);
                                String tipoConta = Autenticacao.tipoConta(email, path);
                                if (nomeUsuario.equals("Usuario não encontrado") || tipoConta.equals("Usuario não encontrado")) {
                                    System.out.println("Erro: Usuário ou tipo de conta inválido");
                                    return;
                                }

                                String caminhoArquivo = pathPasta + File.separator + nomeUsuario + File.separator
                                        + tipoConta + ".txt";

                                if (conta.Saque(valorSaque, caminhoArquivo)) {
                                    System.out.println("Saque realizado com sucesso");
                                } else {
                                    System.out.println("Erro: Não foi possivel realizar o deposito");
                                }
                            } else if (respostaEscolha.equals(2)) {
                                System.out.println("Qual valor você deseja depositar?");
                                Double valorDeposito = sc.nextDouble();
                                String nomeUsuario = Autenticacao.nome(email, path);
                                String tipoConta = Autenticacao.tipoConta(email, path);
                                if (nomeUsuario.equals("Usuario não encontrado")
                                        || tipoConta.equals("Usuario não encontrado")) {
                                    System.out.println("Erro: Usuário ou tipo de conta inválido");
                                    return;
                                }

                                String caminhoArquivo = pathPasta + File.separator + nomeUsuario + File.separator
                                        + tipoConta + ".txt";

                                if (conta.deposito(valorDeposito, caminhoArquivo)) {
                                    System.out.println("Deposito realizado com sucesso");
                                } else {
                                    System.out.println("Erro: Não foi possivel realizar o deposito");
                                }
                            }
                        }
                    }
                    System.out.println("<-------------- Bem vindo a sua conta " + Autenticacao.nome(email, path)
                            + " -------------->");
                    System.out.println("[1] - Sacar");
                    System.out.println("[2] - Depositar");
                    System.out.println("[3] - Ver saldo na conta");
                    System.out.println("[4] - Ver extrato");
                    System.out.println("[5] - Sair");
                    respostaEscolha = sc.nextInt();
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
                Integer tipoContaNumber = sc.nextInt();
                String tipoConta = "";
                if (tipoContaNumber.equals(1)) {
                    tipoConta = "contaCorrente";
                } else if (tipoContaNumber.equals(2)) {
                    tipoConta = "contaPoupanca";
                }
                System.out.print("Senha: ");
                Integer senha = sc.nextInt();
                nome = removerAcentos(nome);
                Usuario usuario = new Usuario(nome, email, cpf, tipoConta, senha);
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
                        if (tipoContaNumber == 1) {
                            String caminhoArquivo = pathPasta + nome + File.separator + "ContaCorrente.txt";
                            File TipoConta = new File(caminhoArquivo);
                            TipoConta.createNewFile();
                            try (BufferedWriter contaWriter = new BufferedWriter(
                                    new OutputStreamWriter(new FileOutputStream(caminhoArquivo),
                                            StandardCharsets.UTF_8))) {
                                contaCorrente contaCorrente = new contaCorrente();
                                contaWriter.write(contaCorrente.toString());
                                contaWriter.newLine();
                            } catch (IOException e) {
                                System.out.println("Erro ao escrever na conta: " + e.getMessage());
                            }
                        } else if (tipoContaNumber == 2) {
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

    public static String removerAcentos(String str) {
        String normalizado = Normalizer.normalize(str, Normalizer.Form.NFD);
        return normalizado.replaceAll("\\p{M}", "");
    }
}
