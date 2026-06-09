public class EstadoDefesaDireitoResposta implements EstadoDebate {
    @Override
    public void solicitarDireitoResposta(
            MediadorDebate debate,
            PoliticoColaborador politico,
            String motivo
    ) {
        System.out.println("[DR BLOQUEADO] " + politico.getNome()
                + " tentou solicitar Direito de Resposta durante as defesas.");
        System.out.println("Durante uma defesa de DR, novas solicitações são bloqueadas para evitar ciclo infinito.");
        Logger.getInstance().registerLog("DR bloqueado durante defesas para: " + politico.getNome());
    }

    @Override
    public void processarDireitosResposta(MediadorDebate debate, Configuracao config) {
        debate.executarDireitosRespostaConcedidos(config);
    }

    @Override
    public String getNomeEstado() {
        return "Defesa de Direito de Resposta";
    }
}
