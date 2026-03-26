public class Cliente {

    public static void main(String[] args) {
        System.out.println("=== Cliente do Padrão Bridge ===");
        System.out.println();

        Implementador implementadorBD = new PublicacaoImplBD();
        Implementador implementadorXML = new PublicacaoImplXML();

        Livro livro = new Livro(implementadorBD);
        Revista revista = new Revista(implementadorXML);

        System.out.println();
        System.out.println("=== Testando Livro com implementacao BD ===");
        livro.obterDados("Livro");
        livro.getTitulo();
        livro.getAutor(1);
        livro.getISBN();

        System.out.println();
        System.out.println("=== Testando Revista com implementacao XML ===");
        revista.obterDados("Revista");
        revista.getTitulo();
        revista.getAutor(2);
        revista.getArtigo();

        System.out.println();
        System.out.println("=== Trocando implementacao para demonstrar Bridge ===");
        Livro livroXML = new Livro(implementadorXML);
        livroXML.obterDados("Livro");
        livroXML.getTitulo();
        livroXML.getAutor(3);
        livroXML.getISBN();
    }
}