public class Main {
    public static void main(String[] args) {
        Facade facade = Facade.getInstance();

        System.out.println("==============================================");
        System.out.println(" GDP v3.0 - Simulação via Terminal Shell");
        System.out.println(" Ênfase: Direito de Resposta com padrão State");
        System.out.println("==============================================\n");

        facade.cadastrarUsuarioOperador("Osmar dos Santos Filho", "osmar");

        // Tempos curtos para facilitar a gravação do vídeo.
        facade.configuracao(1, 1, 1, 1, 1);

        facade.cadastrarPolitico("Joao", "Partido A", 45);
        facade.cadastrarPolitico("Maria", "Partido B", 13);
        facade.cadastrarPolitico("Pedro", "Partido C", 22);
        facade.cadastrarPolitico("Carlos", "Partido D", 40);

        facade.cadastrarEleitor("Ana", "Joao");
        facade.cadastrarEleitor("Beatriz", "Maria");
        facade.cadastrarEleitor("Lucas", "Pedro");
        facade.cadastrarEleitor("Fernanda", "Carlos");

        facade.listarPoliticos();

        facade.escolherInquiridor("Joao");
        facade.escolherInquirido("Maria");

        System.out.println("\n>>> Agora será iniciado um ciclo de debate.");
        System.out.println(">>> Durante o ciclo, Pedro e Carlos vão acionar o botão DR.");
        System.out.println(">>> O gerente só analisará os pedidos após pergunta-resposta-réplica-tréplica.\n");

        facade.demonstrarDireitoResposta();

        System.out.println("\n===== LOGS DO SISTEMA =====");
        facade.getLogs();
    }
}
