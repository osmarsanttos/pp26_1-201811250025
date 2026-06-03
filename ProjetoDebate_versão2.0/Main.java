public class Main {
    public static void main(String[] args) {
        Facade facade = Facade.getInstance();

        facade.cadastrarUsuarioOperador("Osmar dos Santos Filho", "osmar");
        facade.configuracao(1, 1, 1, 1);

        facade.cadastrarPolitico("Joao", "Partido A", 45);
        facade.cadastrarPolitico("Maria", "Partido B", 13);
        facade.cadastrarPolitico("Pedro", "Partido C", 22);

        facade.cadastrarEleitor("Ana", "Joao");
        facade.cadastrarEleitor("Carlos", "Joao");
        facade.cadastrarEleitor("Beatriz", "Maria");

        facade.listarPoliticos();
        facade.listarEleitoresPorCandidato("Joao");

        facade.clonarPolitico("Joao", "Joao Clone");
        facade.clonarEleitor("Ana", "Joao", "Ana Clone");

        facade.escolherInquiridor("Joao");
        facade.escolherInquirido("Maria");
        facade.iniciarDebate();

        System.out.println("\n===== LOGS =====");
        facade.getLogs();
    }
}
