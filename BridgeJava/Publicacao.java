public abstract class Publicacao {

    protected Implementador imp;

    public Publicacao(Implementador imp) {
        this.imp = imp;
        System.out.println("Publicacao construtor chamado");
    }

    public void obterDados(String tipo) {
        System.out.println("Publicacao.obterDados() chamado");
        imp.getDados(tipo);
    }

    public void getTitulo() {
        System.out.println("Publicacao.getTitulo() chamado");
    }

    public void getAutor(int id) {
        System.out.println("Publicacao.getAutor() chamado com id: " + id);
    }
}