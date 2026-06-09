# GDP v3.0 - Direito de Resposta com State

## Autor

**Osmar dos Santos Filho**

## Descrição

Esta é a versão 3.0 do GDP, o Gerenciador de Debate entre Políticos.

A principal novidade desta versão é a implementação do **Direito de Resposta (DR)** durante o debate, utilizando o padrão de projeto **State**.

O sistema continua mantendo os padrões já existentes:

- Facade
- Singleton
- Mediator
- Observer
- Builder
- Prototype

E agora adiciona:

- State

## Funcionalidade principal da versão 3.0

Durante o debate, qualquer político participante pode acionar o botão **DR**, integrado ao microfone, para solicitar Direito de Resposta quando seu nome for citado ou associado a algum fato que possa prejudicar sua imagem.

As solicitações são registradas em uma fila FIFO, ou seja, são analisadas na ordem em que foram feitas.

Após o ciclo completo:

1. Pergunta
2. Resposta
3. Réplica
4. Tréplica

O gerente do debate analisa as solicitações.

Se o Direito de Resposta for concedido:

- o fluxo normal do debate é interrompido;
- o microfone do político solicitante é aberto;
- seus eleitores recebem notificação;
- novos pedidos de DR são bloqueados durante as defesas;
- após as defesas, o debate retorna ao fluxo normal.

## Padrão State

Foram adicionadas as classes:

```text
EstadoDebate
EstadoFluxoNormal
EstadoDefesaDireitoResposta
```

O `MediadorDebate` atua como o contexto do padrão State, controlando o estado atual do debate.

## Como executar

No terminal do VS Code, dentro da pasta do projeto:

```bash
javac *.java
java Main
```

## Demonstração no vídeo

O arquivo `Main.java` já está preparado para uma simulação curta.

Ele demonstra:

- cadastro de políticos;
- cadastro de eleitores;
- início do ciclo normal do debate;
- acionamento do botão DR por dois políticos;
- análise dos pedidos após o ciclo;
- concessão dos direitos de resposta;
- bloqueio de novos DR durante as defesas;
- notificação aos eleitores;
- retorno ao fluxo normal.

## Arquivos principais

```text
Main.java
Facade.java
MediadorDebate.java
EstadoDebate.java
EstadoFluxoNormal.java
EstadoDefesaDireitoResposta.java
SolicitacaoDireitoResposta.java
MicrofoneCronometro.java
PoliticoColaborador.java
Eleitor.java
GerenciadorPoliticos.java
Logger.java
```
