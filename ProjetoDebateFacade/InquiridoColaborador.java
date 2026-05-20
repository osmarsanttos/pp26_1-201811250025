public class InquiridoColaborador extends PoliticoColaborador {
    private PoliticoColaborador politicoOriginal;

    public InquiridoColaborador(PoliticoColaborador politico) {
        super(politico.getNome());
        this.politicoOriginal = politico;
        this.mediador = politico.mediador;
    }

    @Override
    public void falar(int tempo) {
        politicoOriginal.falar(tempo);
    }
}
