public class EleitorBuilder {
    private String nome;
    private PoliticoColaborador candidatoPreferido;

    public EleitorBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public EleitorBuilder setCandidatoPreferido(PoliticoColaborador candidatoPreferido) {
        this.candidatoPreferido = candidatoPreferido;
        return this;
    }

    public Eleitor build() {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do eleitor é obrigatório.");
        }
        if (candidatoPreferido == null) {
            throw new IllegalArgumentException("Candidato preferido é obrigatório.");
        }
        return new Eleitor(nome, candidatoPreferido);
    }
}
