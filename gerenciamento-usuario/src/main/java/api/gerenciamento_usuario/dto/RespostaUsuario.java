package api.gerenciamento_usuario.dto;

public class RespostaUsuario {

    private String senhaAtual;
    private String senhaNova;
    private String SenhaNovaConfirmacao;

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

    public String getSenhaNovaConfirmacao() {
        return SenhaNovaConfirmacao;
    }

    public void setSenhaNovaConfirmacao(String senhaNovaConfirmacao) {
        this.SenhaNovaConfirmacao = senhaNovaConfirmacao;
    }
}