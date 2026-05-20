public class Facade {
    private static Facade instance;

    private MediadorDebate mediadorDebate;
    private Configuracao config;
    private GerenciadorPoliticos gerenciaPoliticos;
    private Logger logger;

    private Facade() {
        mediadorDebate = new MediadorDebate();
        config = new Configuracao();
        gerenciaPoliticos = new GerenciadorPoliticos();
        logger = Logger.getInstance();
        logger.registerLog("Fachada iniciada");
    }

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public void configuracao(int pergunta, int resposta, int replica, int treplica) {
        config.setPerguntaTempo(pergunta);
        config.setRespostaTempo(resposta);
        config.setReplicaTempo(replica);
        config.setTreplicaTempo(treplica);
        logger.registerLog("Configuração de tempo realizada");
        System.out.println("Tempos configurados com sucesso.");
    }

    public void cadastrarPolitico(String nome) {
        if (gerenciaPoliticos.obterPolitico(nome) != null) {
            System.out.println("Político já cadastrado: " + nome);
            return;
        }

        gerenciaPoliticos.criarPolitico(nome, mediadorDebate);
        logger.registerLog("Político cadastrado: " + nome);
        System.out.println("Político cadastrado: " + nome);
    }

    public void cadastrarEleitor(String nomeEleitor, String nomeCandidato) {
        PoliticoColaborador candidato = gerenciaPoliticos.obterPolitico(nomeCandidato);

        if (candidato == null) {
            System.out.println("Candidato não encontrado: " + nomeCandidato);
            return;
        }

        Eleitor eleitor = new Eleitor(nomeEleitor, candidato);
        candidato.adicionarEleitor(eleitor);

        logger.registerLog("Eleitor " + nomeEleitor + " cadastrado para o candidato " + nomeCandidato);
        System.out.println("Eleitor " + nomeEleitor + " cadastrado para receber notificações de " + nomeCandidato);
    }

    public void removerEleitor(String nomeEleitor, String nomeCandidato) {
        PoliticoColaborador candidato = gerenciaPoliticos.obterPolitico(nomeCandidato);

        if (candidato == null) {
            System.out.println("Candidato não encontrado: " + nomeCandidato);
            return;
        }

        boolean removido = candidato.removerEleitorPorNome(nomeEleitor);

        if (removido) {
            logger.registerLog("Eleitor removido: " + nomeEleitor + " do candidato " + nomeCandidato);
            System.out.println("Eleitor removido com sucesso.");
        } else {
            System.out.println("Eleitor não encontrado na lista desse candidato.");
        }
    }

    public void sortearInquiridor() {
        PoliticoColaborador politico = gerenciaPoliticos.sortear();

        if (politico == null) {
            System.out.println("Nenhum político cadastrado.");
            return;
        }

        mediadorDebate.setInquiridor(politico);
        logger.registerLog("Inquiridor sorteado: " + politico.getNome());
        System.out.println("Inquiridor sorteado: " + politico.getNome());
    }

    public void escolherInquirido(String nome) {
        PoliticoColaborador politico = gerenciaPoliticos.obterPolitico(nome);

        if (politico == null) {
            System.out.println("Político não encontrado: " + nome);
            return;
        }

        mediadorDebate.setInquirido(politico);
        logger.registerLog("Inquirido escolhido: " + politico.getNome());
        System.out.println("Inquirido escolhido: " + politico.getNome());
    }

    public void iniciarDebate() {
        logger.registerLog("Debate iniciado");
        mediadorDebate.debate(config);
    }

    public void listarPoliticos() {
        gerenciaPoliticos.listarPoliticos();
    }

    public void listarEleitoresPorCandidato(String nomeCandidato) {
        PoliticoColaborador candidato = gerenciaPoliticos.obterPolitico(nomeCandidato);

        if (candidato == null) {
            System.out.println("Candidato não encontrado: " + nomeCandidato);
            return;
        }

        candidato.listarEleitores();
    }

    public void getLogs() {
        logger.getAllLogs();
    }
}
