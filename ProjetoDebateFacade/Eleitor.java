public class Eleitor implements ObservadorEleitor {
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

    public String getNome() {
        return nome;
    }

    public PoliticoColaborador getCandidatoPreferido() {
        return candidatoPreferido;
    }
}
