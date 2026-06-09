import java.util.ArrayList;
import java.util.List;

public class PoliticoColaborador implements Prototype<PoliticoColaborador> {
    protected String nome;
    protected String partido;
    protected int numero;
    protected Mediador mediador;
    protected boolean sorteado;
    protected MicrofoneCronometro microfone;
    protected List<ObservadorEleitor> eleitores;

    public PoliticoColaborador(String nome, String partido, int numero, Mediador mediador) {
        this.nome = nome;
        this.partido = partido;
        this.numero = numero;
        this.mediador = mediador;
        this.sorteado = false;
        this.microfone = new MicrofoneCronometro();
        this.eleitores = new ArrayList<>();
    }

    public void falar(int tempo) {
        notificarEleitores("SEU CANDIDATO ESTÁ FALANDO: Candidato " + nome + " está falando.");
        microfone.ativar();
        microfone.esperarTempo(tempo);
        microfone.desativar();
    }

    public void falarDireitoResposta(int tempo) {
        notificarEleitores("DIREITO DE RESPOSTA: Candidato " + nome + " está realizando sua defesa.");
        microfone.ativar();
        microfone.esperarTempo(tempo);
        microfone.desativar();
    }

    public void solicitarDireitoResposta(String motivo) {
        microfone.acionarBotaoDR(this, motivo);
    }

    public void adicionarEleitor(ObservadorEleitor eleitor) {
        eleitores.add(eleitor);
    }

    public void removerEleitor(ObservadorEleitor eleitor) {
        eleitores.remove(eleitor);
    }

    public boolean removerEleitorPorNome(String nomeEleitor) {
        for (ObservadorEleitor observador : new ArrayList<>(eleitores)) {
            if (observador instanceof Eleitor) {
                Eleitor eleitor = (Eleitor) observador;
                if (eleitor.getNome().equalsIgnoreCase(nomeEleitor)) {
                    eleitores.remove(observador);
                    return true;
                }
            }
        }
        return false;
    }

    public Eleitor obterEleitorPorNome(String nomeEleitor) {
        for (ObservadorEleitor observador : eleitores) {
            if (observador instanceof Eleitor) {
                Eleitor eleitor = (Eleitor) observador;
                if (eleitor.getNome().equalsIgnoreCase(nomeEleitor)) {
                    return eleitor;
                }
            }
        }
        return null;
    }

    public void notificarEleitores(String mensagem) {
        if (eleitores.isEmpty()) {
            System.out.println("Nenhum eleitor cadastrado para receber notificação de " + nome + ".");
        }

        for (ObservadorEleitor eleitor : eleitores) {
            eleitor.receberNotificacao(mensagem);
        }
    }

    public void listarEleitores() {
        System.out.println("\nEleitores cadastrados para " + nome + ":");

        if (eleitores.isEmpty()) {
            System.out.println("Nenhum eleitor cadastrado.");
            return;
        }

        for (ObservadorEleitor observador : eleitores) {
            if (observador instanceof Eleitor) {
                Eleitor eleitor = (Eleitor) observador;
                System.out.println("- " + eleitor.getNome());
            }
        }
    }

    @Override
    public PoliticoColaborador clonar() {
        PoliticoColaborador copia = new PoliticoColaborador(
                this.nome,
                this.partido,
                this.numero,
                this.mediador
        );
        copia.setSorteado(false);
        return copia;
    }

    public PoliticoColaborador clonarComNovoNome(String novoNome) {
        PoliticoColaborador copia = this.clonar();
        copia.nome = novoNome;
        return copia;
    }

    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }

    public void setSorteado(boolean sorteado) {
        this.sorteado = sorteado;
    }

    public boolean getSorteado() {
        return sorteado;
    }

    public MicrofoneCronometro getMicrofone() {
        return microfone;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public int getNumero() {
        return numero;
    }
}
