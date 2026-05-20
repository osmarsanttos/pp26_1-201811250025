public class InquiridorColaborador extends PoliticoColaborador {
    private PoliticoColaborador politicoOriginal;

    public InquiridorColaborador(PoliticoColaborador politico) {
        super(politico.getNome());
        this.politicoOriginal = politico;
        this.mediador = politico.mediador;
    }

    public void escolherInquirido(PoliticoColaborador politico) {
        if (mediador != null) {
            mediador.setInquirido(politico);
        }
    }

    @Override
    public void falar(int tempo) {
        politicoOriginal.falar(tempo);
    }
}
