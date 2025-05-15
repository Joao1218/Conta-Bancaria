public class Usuario {
    private String nome;
    private String email;
    private Long cpf;
    private Integer senha;
    private String tipoConta;
    public Usuario(String nome, String email, Long cpf,String tipoConta, Integer senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.tipoConta = tipoConta;
        this.senha = senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getCpf() {
        return cpf;
    }
    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
    public Integer getSenha() {
        return senha;
    }
    public String getTipoConta() {
        return tipoConta;
    }
    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }
     @Override
    public String toString() {
        return nome + "," + email + ", " + cpf + ","+ tipoConta+ ", "+ senha;
    }
    
}
