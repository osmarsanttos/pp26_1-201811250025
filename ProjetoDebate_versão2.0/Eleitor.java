public class Eleitor implements ObservadorEleitor, Prototype<Eleitor> {
    private String nome;
    private PoliticoColaborador candidatoPreferido;

    public Eleitor(String nome, PoliticoColaborador candidatoPreferido) {
        this.nome = nome;
        this.candidatoPreferido = candidatoPreferido;
    }

    @Override
    public void receberNotificacao(String mensagem) {
        System.out.println("[NOTIFICAÇÃO para " + nome + "] " + mensagem);
    }

    @Override
    public Eleitor clonar() {
        return new Eleitor(this.nome, this.candidatoPreferido);
    }

    public Eleitor clonarComNovoNome(String novoNome) {
        return new Eleitor(novoNome, this.candidatoPreferido);
    }

    public String getNome() { return nome; }
    public PoliticoColaborador getCandidatoPreferido() { return candidatoPreferido; }
}
