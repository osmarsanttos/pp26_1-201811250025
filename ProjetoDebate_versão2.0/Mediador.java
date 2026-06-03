public interface Mediador {
    void debate(Configuracao config);
    void setInquiridor(PoliticoColaborador politico);
    void setInquirido(PoliticoColaborador politico);
}
