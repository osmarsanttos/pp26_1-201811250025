public class EstadoFluxoNormal implements EstadoDebate {
    @Override
    public void solicitarDireitoResposta(
            MediadorDebate debate,
            PoliticoColaborador politico,
            String motivo
    ) {
        debate.registrarSolicitacaoDireitoResposta(politico, motivo);
    }

    @Override
    public void processarDireitosResposta(MediadorDebate debate, Configuracao config) {
        debate.analisarDireitosResposta(config);
    }

    @Override
    public String getNomeEstado() {
        return "Fluxo Normal";
    }
}
