public interface EstadoDebate {
    void solicitarDireitoResposta(
            MediadorDebate debate,
            PoliticoColaborador politico,
            String motivo
    );

    void processarDireitosResposta(MediadorDebate debate, Configuracao config);

    String getNomeEstado();
}
