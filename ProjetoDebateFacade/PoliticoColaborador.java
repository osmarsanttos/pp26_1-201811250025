import java.util.ArrayList;
import java.util.List;

public class PoliticoColaborador {
    protected String nome;
    protected Mediador mediador;
    protected boolean sorteado;
    protected MicrofoneCronometro microfone;
    protected List<ObservadorEleitor> eleitores;

    public PoliticoColaborador(String nome) {
        this.nome = nome;
        this.sorteado = false;
        this.microfone = new MicrofoneCronometro();
        this.eleitores = new ArrayList<>();
    }

    public void falar(int tempo) {
        notificarEleitores();
        microfone.ativar();
        microfone.esperarTempo(tempo);
        microfone.desativar();
    }

    public void adicionarEleitor(ObservadorEleitor eleitor) {
        eleitores.add(eleitor);
    }

    public void removerEleitor(ObservadorEleitor eleitor) {
        eleitores.remove(eleitor);
    }

    public boolean removerEleitorPorNome(String nomeEleitor) {
        for (ObservadorEleitor observador : eleitores) {
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

    public void notificarEleitores() {
        String mensagem = "SEU CANDIDATO ESTÁ FALANDO: Candidato " + nome + " está falando";

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

    public void setMediador(Mediador mediador) {
        this.mediador = mediador;
    }

    public void setSorteado(boolean sorteado) {
        this.sorteado = sorteado;
    }

    public boolean getSorteado() {
        return sorteado;
    }

    public String getNome() {
        return nome;
    }
}
