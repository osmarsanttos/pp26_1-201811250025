public class UsuarioOperador implements Prototype<UsuarioOperador> {
    private String nome;
    private String login;

    public UsuarioOperador(String nome, String login) {
        this.nome = nome;
        this.login = login;
    }

    @Override
    public UsuarioOperador clonar() {
        return new UsuarioOperador(this.nome, this.login);
    }

    public String getNome() { return nome; }
    public String getLogin() { return login; }
}
