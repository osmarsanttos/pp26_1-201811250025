import java.util.LinkedList;
import java.util.Queue;

public class MediadorDebate implements Mediador {
    private InquiridorColaborador inquiridor;
    private InquiridoColaborador inquirido;

    private EstadoDebate estadoAtual;
    private Queue<SolicitacaoDireitoResposta> filaDireitoResposta;
    private Queue<SolicitacaoDireitoResposta> direitosConcedidos;
    private int contadorSolicitacoes;

    public MediadorDebate() {
        this.estadoAtual = new EstadoFluxoNormal();
        this.filaDireitoResposta = new LinkedList<>();
        this.direitosConcedidos = new LinkedList<>();
        this.contadorSolicitacoes = 0;
    }

    @Override
    public void debate(Configuracao config) {
        executarCicloNormal(config);
        estadoAtual.processarDireitosResposta(this, config);
    }

    public void debateComDemonstracaoDR(Configuracao config, Runnable simulacaoDuranteCiclo) {
        if (inquiridor == null || inquirido == null) {
            System.out.println("Erro: inquiridor ou inquirido não definido.");
            return;
        }

        System.out.println("\n========== CICLO NORMAL DO DEBATE ==========");

        Logger.getInstance().registerLog("Pergunta");
        System.out.println("\n--- PERGUNTA ---");
        inquiridor.falar(config.getPerguntaTempo());

        Logger.getInstance().registerLog("Resposta");
        System.out.println("\n--- RESPOSTA ---");
        inquirido.falar(config.getRespostaTempo());

        System.out.println("\n>>> SIMULAÇÃO: políticos acionam o botão DR durante o ciclo.");
        simulacaoDuranteCiclo.run();

        Logger.getInstance().registerLog("Réplica");
        System.out.println("\n--- RÉPLICA ---");
        inquiridor.falar(config.getReplicaTempo());

        Logger.getInstance().registerLog("Tréplica");
        System.out.println("\n--- TRÉPLICA ---");
        inquirido.falar(config.getTreplicaTempo());

        System.out.println("\n========== FIM DO CICLO: GERENTE ANALISA DIREITOS DE RESPOSTA ==========");
        estadoAtual.processarDireitosResposta(this, config);
    }

    private void executarCicloNormal(Configuracao config) {
        if (inquiridor == null || inquirido == null) {
            System.out.println("Erro: inquiridor ou inquirido não definido.");
            return;
        }

        System.out.println("\n========== CICLO NORMAL DO DEBATE ==========");

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

    public void solicitarDireitoResposta(PoliticoColaborador politico, String motivo) {
        estadoAtual.solicitarDireitoResposta(this, politico, motivo);
    }

    public void registrarSolicitacaoDireitoResposta(PoliticoColaborador politico, String motivo) {
        contadorSolicitacoes++;
        SolicitacaoDireitoResposta solicitacao = new SolicitacaoDireitoResposta(
                politico,
                contadorSolicitacoes,
                motivo
        );

        filaDireitoResposta.add(solicitacao);

        Logger.getInstance().registerLog("Solicitação de DR registrada: " + politico.getNome());
        System.out.println("[DR REGISTRADO] Ordem " + solicitacao.getOrdem()
                + " | Político: " + politico.getNome()
                + " | Motivo: " + motivo);
        System.out.println("A solicitação será analisada após pergunta-resposta-réplica-tréplica.");
    }

    public void analisarDireitosResposta(Configuracao config) {
        if (filaDireitoResposta.isEmpty()) {
            System.out.println("Nenhuma solicitação de Direito de Resposta para analisar.");
            return;
        }

        System.out.println("\n[GERENTE] Analisando solicitações de DR em ordem FIFO...");

        while (!filaDireitoResposta.isEmpty()) {
            SolicitacaoDireitoResposta solicitacao = filaDireitoResposta.poll();

            // Simulação: o gerente aceita todas para demonstrar a funcionalidade.
            solicitacao.conceder();
            direitosConcedidos.add(solicitacao);

            System.out.println("[DR CONCEDIDO] Ordem " + solicitacao.getOrdem()
                    + " | " + solicitacao.getPolitico().getNome());
            Logger.getInstance().registerLog("DR concedido para: " + solicitacao.getPolitico().getNome());
        }

        setEstado(new EstadoDefesaDireitoResposta());
        estadoAtual.processarDireitosResposta(this, config);
    }

    public void executarDireitosRespostaConcedidos(Configuracao config) {
        if (direitosConcedidos.isEmpty()) {
            setEstado(new EstadoFluxoNormal());
            return;
        }

        System.out.println("\n========== DIREITOS DE RESPOSTA CONCEDIDOS ==========");
        System.out.println("Fluxo normal interrompido. Novas solicitações de DR estão bloqueadas.");

        while (!direitosConcedidos.isEmpty()) {
            SolicitacaoDireitoResposta solicitacao = direitosConcedidos.poll();
            PoliticoColaborador politico = solicitacao.getPolitico();

            politico.getMicrofone().desabilitarDR();

            System.out.println("\n--- DEFESA POR DIREITO DE RESPOSTA ---");
            System.out.println("Político: " + politico.getNome());
            System.out.println("Motivo: " + solicitacao.getMotivo());

            // Demonstração do bloqueio: durante a defesa, o próprio político tenta acionar DR.
            if (solicitacao.getOrdem() == 1) {
                System.out.println("\n[TENTATIVA DE NOVO DR DURANTE DEFESA]");
                politico.solicitarDireitoResposta("Tentativa bloqueada durante a própria defesa");
            }

            politico.falarDireitoResposta(config.getDireitoRespostaTempo());
            politico.getMicrofone().habilitarDR();
        }

        System.out.println("\n========== FIM DAS DEFESAS ==========");
        System.out.println("Fluxo normal do debate retomado.");

        setEstado(new EstadoFluxoNormal());
        Logger.getInstance().registerLog("Debate retornou ao fluxo normal após DR.");
    }

    @Override
    public void setInquiridor(PoliticoColaborador politico) {
        this.inquiridor = new InquiridorColaborador(politico);
    }

    @Override
    public void setInquirido(PoliticoColaborador politico) {
        this.inquirido = new InquiridoColaborador(politico);
    }

    public void setEstado(EstadoDebate estadoAtual) {
        this.estadoAtual = estadoAtual;
        System.out.println("[STATE] Estado atual do debate: " + estadoAtual.getNomeEstado());
        Logger.getInstance().registerLog("Estado alterado para: " + estadoAtual.getNomeEstado());
    }

    public EstadoDebate getEstadoAtual() {
        return estadoAtual;
    }
}
