public class MicrofoneCronometro {
    private boolean microfoneAtivo;
    private boolean drHabilitado;
    private MediadorDebate mediadorDebate;

    public MicrofoneCronometro() {
        this.microfoneAtivo = false;
        this.drHabilitado = true;
    }

    public void setMediadorDebate(MediadorDebate mediadorDebate) {
        this.mediadorDebate = mediadorDebate;
    }

    public void ativar() {
        microfoneAtivo = true;
        System.out.println("Microfone ativado.");
    }

    public void desativar() {
        microfoneAtivo = false;
        System.out.println("Microfone desativado.");
    }

    public void esperarTempo(int tempo) {
        System.out.println("Falando por " + tempo + " segundo(s)...");
        try {
            Thread.sleep(tempo * 1000L);
        } catch (InterruptedException e) {
            System.out.println("Erro no cronômetro.");
            Thread.currentThread().interrupt();
        }
    }

    public void acionarBotaoDR(PoliticoColaborador politico, String motivo) {
        if (!drHabilitado) {
            System.out.println("[BOTÃO DR BLOQUEADO] Solicitação de " + politico.getNome()
                    + " não foi registrada.");
            return;
        }

        if (mediadorDebate == null) {
            System.out.println("Erro: microfone não está ligado ao gerente do debate.");
            return;
        }

        System.out.println("[BOTÃO DR ACIONADO] " + politico.getNome() + " solicitou Direito de Resposta.");
        mediadorDebate.solicitarDireitoResposta(politico, motivo);
    }

    public void habilitarDR() {
        drHabilitado = true;
    }

    public void desabilitarDR() {
        drHabilitado = false;
    }

    public boolean isMicrofoneAtivo() {
        return microfoneAtivo;
    }
}
