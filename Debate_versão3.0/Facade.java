public class Facade {
    private static Facade instance;

    private MediadorDebate mediadorDebate;
    private Configuracao config;
    private GerenciadorPoliticos gerenciaPoliticos;
    private Logger logger;
    private UsuarioOperador usuarioOperador;

    private Facade() {
        mediadorDebate = new MediadorDebate();
        config = new Configuracao();
        gerenciaPoliticos = new GerenciadorPoliticos(mediadorDebate);
        logger = Logger.getInstance();
        logger.registerLog("Fachada iniciada");
    }

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    public void cadastrarUsuarioOperador(String nome, String login) {
        usuarioOperador = new UsuarioOperadorBuilder()
                .setNome(nome)
                .setLogin(login)
                .build();

        logger.registerLog("Usuário operador cadastrado: " + usuarioOperador.getNome());
        System.out.println("Usuário operador cadastrado: " + usuarioOperador.getNome());
    }

    public void configuracao(int pergunta, int resposta, int replica, int treplica, int direitoResposta) {
        config.setPerguntaTempo(pergunta);
        config.setRespostaTempo(resposta);
        config.setReplicaTempo(replica);
        config.setTreplicaTempo(treplica);
        config.setDireitoRespostaTempo(direitoResposta);

        logger.registerLog("Configuração de tempo realizada");
        System.out.println("Tempos configurados com sucesso.");
    }

    public void cadastrarPolitico(String nome, String partido, int numero) {
        if (gerenciaPoliticos.obterPolitico(nome) != null) {
            System.out.println("Político já cadastrado: " + nome);
            return;
        }

        gerenciaPoliticos.criarPolitico(nome, partido, numero, mediadorDebate);
        logger.registerLog("Político cadastrado via Builder: " + nome);
        System.out.println("Político cadastrado via Builder: " + nome);
    }

    public void cadastrarEleitor(String nomeEleitor, String nomeCandidato) {
        PoliticoColaborador candidato = gerenciaPoliticos.obterPolitico(nomeCandidato);

        if (candidato == null) {
            System.out.println("Candidato não encontrado: " + nomeCandidato);
            return;
        }

        Eleitor eleitor = new EleitorBuilder()
                .setNome(nomeEleitor)
                .setCandidatoPreferido(candidato)
                .build();

        candidato.adicionarEleitor(eleitor);

        logger.registerLog("Eleitor cadastrado via Builder: " + nomeEleitor + " para " + nomeCandidato);
        System.out.println("Eleitor cadastrado via Builder: " + nomeEleitor + " -> " + nomeCandidato);
    }

    public void clonarPolitico(String nomePoliticoBase, String novoNome) {
        PoliticoColaborador base = gerenciaPoliticos.obterPolitico(nomePoliticoBase);

        if (base == null) {
            System.out.println("Político base não encontrado: " + nomePoliticoBase);
            return;
        }

        PoliticoColaborador clone = base.clonarComNovoNome(novoNome);
        gerenciaPoliticos.adicionarPolitico(clone);

        logger.registerLog("Político clonado via Prototype: " + nomePoliticoBase + " -> " + novoNome);
        System.out.println("Político clonado via Prototype: " + novoNome);
    }

    public void clonarEleitor(String nomeEleitorBase, String nomeCandidato, String novoNomeEleitor) {
        PoliticoColaborador candidato = gerenciaPoliticos.obterPolitico(nomeCandidato);

        if (candidato == null) {
            System.out.println("Candidato não encontrado: " + nomeCandidato);
            return;
        }

        Eleitor eleitorBase = candidato.obterEleitorPorNome(nomeEleitorBase);

        if (eleitorBase == null) {
            System.out.println("Eleitor base não encontrado para esse candidato.");
            return;
        }

        Eleitor clone = eleitorBase.clonarComNovoNome(novoNomeEleitor);
        candidato.adicionarEleitor(clone);

        logger.registerLog("Eleitor clonado via Prototype: " + nomeEleitorBase + " -> " + novoNomeEleitor);
        System.out.println("Eleitor clonado via Prototype: " + novoNomeEleitor);
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

    public void escolherInquiridor(String nome) {
        PoliticoColaborador politico = gerenciaPoliticos.obterPolitico(nome);

        if (politico == null) {
            System.out.println("Político não encontrado: " + nome);
            return;
        }

        mediadorDebate.setInquiridor(politico);
        logger.registerLog("Inquiridor escolhido manualmente: " + politico.getNome());
        System.out.println("Inquiridor escolhido manualmente: " + politico.getNome());
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

    public void solicitarDireitoResposta(String nomePolitico, String motivo) {
        PoliticoColaborador politico = gerenciaPoliticos.obterPolitico(nomePolitico);

        if (politico == null) {
            System.out.println("Político não encontrado: " + nomePolitico);
            return;
        }

        politico.solicitarDireitoResposta(motivo);
    }

    public void iniciarDebate() {
        logger.registerLog("Debate iniciado");
        mediadorDebate.debate(config);
    }

    public void demonstrarDireitoResposta() {
        logger.registerLog("Demonstração de Direito de Resposta iniciada");

        mediadorDebate.debateComDemonstracaoDR(config, () -> {
            solicitarDireitoResposta("Pedro", "Meu nome foi citado de forma ofensiva.");
            solicitarDireitoResposta("Carlos", "Fui relacionado a um fato que prejudica minha imagem.");
        });
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
