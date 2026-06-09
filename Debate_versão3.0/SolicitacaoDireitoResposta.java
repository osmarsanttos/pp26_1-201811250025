public class SolicitacaoDireitoResposta {
    private PoliticoColaborador politico;
    private int ordem;
    private String motivo;
    private boolean concedida;

    public SolicitacaoDireitoResposta(
            PoliticoColaborador politico,
            int ordem,
            String motivo
    ) {
        this.politico = politico;
        this.ordem = ordem;
        this.motivo = motivo;
        this.concedida = false;
    }

    public PoliticoColaborador getPolitico() {
        return politico;
    }

    public int getOrdem() {
        return ordem;
    }

    public String getMotivo() {
        return motivo;
    }

    public void conceder() {
        concedida = true;
    }

    public void negar() {
        concedida = false;
    }

    public boolean isConcedida() {
        return concedida;
    }
}
