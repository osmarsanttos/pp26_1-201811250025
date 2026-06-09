public class UsuarioOperadorBuilder {
    private String nome;
    private String login;

    public UsuarioOperadorBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public UsuarioOperadorBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UsuarioOperador build() {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do usuário operador é obrigatório.");
        }
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("Login do usuário operador é obrigatório.");
        }
        return new UsuarioOperador(nome, login);
    }
}
