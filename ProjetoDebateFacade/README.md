# Sistema de Debate Político com Notificações em Tempo Real

## Autor

**Osmar dos Santos Filho**

---

## Descrição do Projeto

Este projeto foi desenvolvido em **Java**, utilizando conceitos de **Programação Orientada a Objetos (POO)** e **Padrões de Projeto (Design Patterns)** para simular um **debate político via linha de comando (terminal)**.

O sistema permite o gerenciamento de candidatos políticos, sorteio de participantes do debate, controle do tempo de fala e envio de notificações automáticas para eleitores cadastrados.

A principal evolução do sistema foi a implementação de uma nova funcionalidade: **eleitores podem se cadastrar para receber notificações em tempo real quando seu candidato preferido estiver falando durante o debate**.

---

## Objetivo do Sistema

Simular um debate entre candidatos políticos onde:

- Existe um **mediador do debate**;
- Há um **candidato inquiridor** e um **candidato inquirido**;
- O sistema controla os tempos de:
  - Pergunta
  - Resposta
  - Réplica
  - Tréplica
- O microfone é ativado e desativado automaticamente;
- Os eleitores recebem notificações antes do candidato começar a falar.

---

## Funcionalidades Implementadas

### Cadastro de Políticos

Permite cadastrar candidatos para participarem do debate.

Exemplo:

```java
facade.cadastrarPolitico("Joao");
facade.cadastrarPolitico("Maria");
```

---

### Configuração dos Tempos do Debate

Permite configurar os tempos de fala para cada etapa.

Exemplo:

```java
facade.configuracao(1, 1, 1, 1);
```

Parâmetros:

```text
(pergunta, resposta, réplica, tréplica)
```

---

### Cadastro de Eleitores

Permite associar eleitores a um candidato específico para receber notificações.

Exemplo:

```java
facade.cadastrarEleitor("Ana", "Joao");
```

---

### Remoção de Eleitores

Possibilita remover um eleitor da lista de notificações.

Exemplo:

```java
facade.removerEleitor("Ana", "Joao");
```

---

### Sorteio do Inquiridor

Realiza o sorteio de um candidato para iniciar o debate.

Exemplo:

```java
facade.sortearInquiridor();
```

---

### Escolha do Inquirido

Define qual candidato responderá às perguntas.

Exemplo:

```java
facade.escolherInquirido("Maria");
```

---

### Início do Debate

Executa automaticamente:

1. Pergunta
2. Resposta
3. Réplica
4. Tréplica

Exemplo:

```java
facade.iniciarDebate();
```

---

### Sistema de Notificações

Antes do microfone ser ativado, os eleitores do candidato recebem uma notificação:

```text
[NOTIFICAÇÃO para Ana]
SEU CANDIDATO ESTÁ FALANDO:
Candidato Joao está falando
```

---

### Registro de Logs

O sistema registra todas as ações executadas.

Exemplo:

```java
facade.getLogs();
```

Saída:

```text
Fachada iniciada
Configuração de tempo realizada
Político cadastrado: Joao
Eleitor Ana cadastrado
Debate iniciado
Pergunta
Resposta
Réplica
Tréplica
```

---

## Padrões de Projeto Utilizados

### 1. Facade

Centraliza toda a comunicação do sistema por meio da classe `Facade`.

Responsável por:

- Cadastro de políticos
- Cadastro de eleitores
- Configuração do debate
- Início do debate
- Consulta de logs

---

### 2. Singleton

Utilizado em:

- `Facade`
- `Logger`

Objetivo:

Garantir apenas **uma instância** dessas classes durante toda a execução.

---

### 3. Mediator

Utilizado na classe:

```text
MediadorDebate
```

Responsável por coordenar o fluxo do debate entre:

- Inquiridor
- Inquirido

---

### 4. Observer (Nova Funcionalidade)

Implementado para permitir notificações em tempo real.

Estrutura:

```text
PoliticoColaborador → Subject
Eleitor → Observer
```

Funcionamento:

Quando um político recebe o direito de fala, ele notifica todos os eleitores cadastrados.

---

### 5. Decorator / Colaborador

Classes:

```text
InquiridorColaborador
InquiridoColaborador
```

Responsáveis por especializar o comportamento do político no debate.

---

## Estrutura do Projeto

```text
ProjetoDebateFacade/
│── Main.java
│── Facade.java
│── Mediador.java
│── MediadorDebate.java
│── PoliticoColaborador.java
│── InquiridorColaborador.java
│── InquiridoColaborador.java
│── GerenciadorPoliticos.java
│── Configuracao.java
│── MicrofoneCronometro.java
│── ObservadorEleitor.java
│── Eleitor.java
│── Logger.java
│── README.md
```

---

## Como Executar o Projeto

### 1. Abrir o projeto no VS Code

Abra a pasta do projeto.

---

### 2. Compilar o projeto

No terminal:

```bash
javac *.java
```

---

### 3. Executar

```bash
java Main
```

---

## Exemplo de Execução

```text
Político cadastrado: Joao
Político cadastrado: Maria

Eleitor Ana cadastrado para receber notificações de Joao

Inquiridor sorteado: Joao
Inquirido escolhido: Maria

--- PERGUNTA ---
[NOTIFICAÇÃO para Ana]
SEU CANDIDATO ESTÁ FALANDO:
Candidato Joao está falando

Microfone ativado.
Falando por 1 segundo(s)...
Microfone desativado.
```

---

## Considerações Finais

O projeto demonstra a aplicação prática de **POO**, **encapsulamento**, **associação entre objetos** e múltiplos **Design Patterns**, além de apresentar uma solução funcional para simulação de debates políticos com notificações em tempo real para eleitores.

---
**Desenvolvido por Osmar dos Santos Filho**
