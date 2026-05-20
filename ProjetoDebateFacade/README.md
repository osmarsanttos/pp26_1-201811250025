# Projeto Debate - Java

## Como rodar

Abra a pasta no VS Code e rode no terminal:

```bash
javac *.java
java Main
```

## Comandos disponíveis pela Facade

```java
facade.configuracao(1, 1, 1, 1);
facade.cadastrarPolitico("Joao");
facade.cadastrarEleitor("Ana", "Joao");
facade.removerEleitor("Ana", "Joao");
facade.listarPoliticos();
facade.listarEleitoresPorCandidato("Joao");
facade.sortearInquiridor();
facade.escolherInquirido("Maria");
facade.iniciarDebate();
facade.getLogs();
```

## Padrões usados

- Facade: classe Facade controla o sistema.
- Singleton: Facade e Logger.
- Mediator: MediadorDebate controla a dinâmica do debate.
- Observer: PoliticoColaborador notifica Eleitor.
- Decorator/Colaborador: InquiridorColaborador e InquiridoColaborador encapsulam o político original.
