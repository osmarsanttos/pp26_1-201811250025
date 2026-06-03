import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorPoliticos {
    private List<PoliticoColaborador> politicos;
    private int totalSorteados;

    public GerenciadorPoliticos() {
        this.politicos = new ArrayList<>();
        this.totalSorteados = 0;
    }

    public PoliticoColaborador criarPolitico(String nome, String partido, int numero, Mediador mediador) {
        PoliticoColaborador novoPolitico = new PoliticoBuilder()
                .setNome(nome)
                .setPartido(partido)
                .setNumero(numero)
                .setMediador(mediador)
                .build();
        politicos.add(novoPolitico);
        return novoPolitico;
    }

    public void adicionarPolitico(PoliticoColaborador politico) { politicos.add(politico); }

    public PoliticoColaborador obterPolitico(String nome) {
        for (PoliticoColaborador politico : politicos) {
            if (politico.getNome().equalsIgnoreCase(nome)) return politico;
        }
        return null;
    }

    public PoliticoColaborador sortear() {
        if (politicos.isEmpty()) return null;
        if (totalSorteados >= politicos.size()) {
            for (PoliticoColaborador politico : politicos) politico.setSorteado(false);
            totalSorteados = 0;
        }
        Random random = new Random();
        PoliticoColaborador politico;
        do {
            politico = politicos.get(random.nextInt(politicos.size()));
        } while (politico.getSorteado());
        politico.setSorteado(true);
        totalSorteados++;
        return politico;
    }

    public void listarPoliticos() {
        System.out.println("\nPolíticos cadastrados:");
        if (politicos.isEmpty()) {
            System.out.println("Nenhum político cadastrado.");
            return;
        }
        for (PoliticoColaborador politico : politicos) {
            System.out.println("- " + politico.getNome() + " | Partido: " + politico.getPartido() + " | Número: " + politico.getNumero());
        }
    }
}
