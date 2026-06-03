public class PoliticoBuilder {
    private String nome;
    private String partido;
    private int numero;
    private Mediador mediador;

    public PoliticoBuilder setNome(String nome) { this.nome = nome; return this; }
    public PoliticoBuilder setPartido(String partido) { this.partido = partido; return this; }
    public PoliticoBuilder setNumero(int numero) { this.numero = numero; return this; }
    public PoliticoBuilder setMediador(Mediador mediador) { this.mediador = mediador; return this; }

    public PoliticoColaborador build() {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome do político é obrigatório.");
        if (partido == null || partido.isBlank()) throw new IllegalArgumentException("Partido do político é obrigatório.");
        if (numero <= 0) throw new IllegalArgumentException("Número do político deve ser maior que zero.");
        if (mediador == null) throw new IllegalArgumentException("Mediador é obrigatório.");
        return new PoliticoColaborador(nome, partido, numero, mediador);
    }
}
