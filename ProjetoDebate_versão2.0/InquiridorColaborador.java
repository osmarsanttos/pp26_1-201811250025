public class InquiridorColaborador extends PoliticoColaborador {
    private PoliticoColaborador politicoOriginal;

    public InquiridorColaborador(PoliticoColaborador politico) {
        super(politico.getNome(), politico.getPartido(), politico.getNumero(), politico.mediador);
        this.politicoOriginal = politico;
    }

    public void escolherInquirido(PoliticoColaborador politico) {
        if (mediador != null) mediador.setInquirido(politico);
    }

    @Override
    public void falar(int tempo) { politicoOriginal.falar(tempo); }
}
