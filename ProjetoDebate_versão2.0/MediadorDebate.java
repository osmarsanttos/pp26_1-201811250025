public class MediadorDebate implements Mediador {
    private InquiridorColaborador inquiridor;
    private InquiridoColaborador inquirido;

    @Override
    public void debate(Configuracao config) {
        if (inquiridor == null || inquirido == null) {
            System.out.println("Erro: inquiridor ou inquirido não definido.");
            return;
        }

        Logger.getInstance().registerLog("Pergunta");
        System.out.println("\n--- PERGUNTA ---");
        inquiridor.falar(config.getPerguntaTempo());

        Logger.getInstance().registerLog("Resposta");
        System.out.println("\n--- RESPOSTA ---");
        inquirido.falar(config.getRespostaTempo());

        Logger.getInstance().registerLog("Réplica");
        System.out.println("\n--- RÉPLICA ---");
        inquiridor.falar(config.getReplicaTempo());

        Logger.getInstance().registerLog("Tréplica");
        System.out.println("\n--- TRÉPLICA ---");
        inquirido.falar(config.getTreplicaTempo());
    }

    @Override
    public void setInquiridor(PoliticoColaborador politico) { this.inquiridor = new InquiridorColaborador(politico); }
    @Override
    public void setInquirido(PoliticoColaborador politico) { this.inquirido = new InquiridoColaborador(politico); }
}
