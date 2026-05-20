public class Main {
    public static void main(String[] args) {
        Facade facade = Facade.getInstance();

        facade.configuracao(1, 1, 1, 1);

        facade.cadastrarPolitico("Joao");
        facade.cadastrarPolitico("Maria");

        facade.cadastrarEleitor("Ana", "Joao");
        facade.cadastrarEleitor("Carlos", "Joao");
        facade.cadastrarEleitor("Beatriz", "Maria");

        facade.escolherInquirido("Maria");
        facade.sortearInquiridor();

        facade.iniciarDebate();

        System.out.println("\n===== LOGS =====");
        facade.getLogs();
    }
}