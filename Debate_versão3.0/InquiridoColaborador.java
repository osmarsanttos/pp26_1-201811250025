public class InquiridoColaborador extends PoliticoColaborador {
    private PoliticoColaborador politicoOriginal;

    public InquiridoColaborador(PoliticoColaborador politico) {
        super(politico.getNome(), politico.getPartido(), politico.getNumero(), politico.mediador);
        this.politicoOriginal = politico;
    }

    @Override
    public void falar(int tempo) {
        politicoOriginal.falar(tempo);
    }
}
