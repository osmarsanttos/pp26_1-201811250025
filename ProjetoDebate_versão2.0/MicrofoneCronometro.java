public class MicrofoneCronometro {
    private boolean microfoneAtivo;

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
        }
    }

    public boolean isMicrofoneAtivo() { return microfoneAtivo; }
}
