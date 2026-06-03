# Sistema de Debate Político - Builder + Prototype

## Autor

**Osmar dos Santos Filho**

---

## Descrição

Este projeto é uma evolução da versão 1.0 do GDP, o **Gerenciador de Debate entre Políticos**.

A versão anterior simulava um debate político via terminal utilizando padrões como **Facade**, **Singleton**, **Mediator** e **Observer**.

Nesta nova versão, o projeto foi refatorado para incluir os padrões **Builder** e **Prototype**.

Esses padrões foram usados para melhorar a criação e a reutilização das entidades principais do sistema:

- Político;
- Eleitor;
- Usuário operador do sistema.

---

## O que foi adicionado nesta versão

### Builder

O padrão Builder foi usado para construir objetos de forma organizada, evitando criação direta espalhada pelo código.

Classes adicionadas:

```text
PoliticoBuilder
EleitorBuilder
UsuarioOperadorBuilder
```

Exemplo:

```java
PoliticoColaborador politico = new PoliticoBuilder()
        .setNome("Joao")
        .setPartido("Partido A")
        .setNumero(45)
        .setMediador(mediador)
        .build();
```

---

### Prototype

O padrão Prototype foi usado para permitir a clonagem de objetos já existentes.

Interface adicionada:

```java
public interface Prototype<T> {
    T clonar();
}
```

Classes que implementam Prototype:

```text
PoliticoColaborador
Eleitor
UsuarioOperador
```

Exemplos pela Facade:

```java
facade.clonarPolitico("Joao", "Joao Clone");
facade.clonarEleitor("Ana", "Joao", "Ana Clone");
facade.clonarUsuarioOperador();
```

---

## Funcionalidades do Sistema

O sistema permite:

- Cadastrar usuário operador;
- Configurar tempos de pergunta, resposta, réplica e tréplica;
- Cadastrar políticos usando Builder;
- Cadastrar eleitores usando Builder;
- Clonar políticos usando Prototype;
- Clonar eleitores usando Prototype;
- Clonar usuário operador usando Prototype;
- Sortear ou escolher o político inquiridor;
- Escolher o político inquirido;
- Iniciar o debate;
- Ativar e desativar o microfone automaticamente;
- Notificar eleitores quando o candidato preferido estiver falando;
- Registrar logs das ações do sistema.

---

## Estrutura do Projeto

```text
ProjetoDebateBuilderPrototype/
│── Main.java
│── Facade.java
│── Prototype.java
│── PoliticoBuilder.java
│── EleitorBuilder.java
│── UsuarioOperadorBuilder.java
│── UsuarioOperador.java
│── ObservadorEleitor.java
│── Eleitor.java
│── PoliticoColaborador.java
│── InquiridorColaborador.java
│── InquiridoColaborador.java
│── Mediador.java
│── MediadorDebate.java
│── GerenciadorPoliticos.java
│── Configuracao.java
│── MicrofoneCronometro.java
│── Logger.java
│── README.md
```

---

## Como Executar

No terminal do VS Code, dentro da pasta do projeto:

```bash
javac *.java
java Main
```

---

## Exemplo de Uso pela Facade

```java
Facade facade = Facade.getInstance();

facade.cadastrarUsuarioOperador("Osmar dos Santos Filho", "osmar");
facade.configuracao(1, 1, 1, 1);

facade.cadastrarPolitico("Joao", "Partido A", 45);
facade.cadastrarPolitico("Maria", "Partido B", 13);

facade.cadastrarEleitor("Ana", "Joao");

facade.clonarPolitico("Joao", "Joao Clone");
facade.clonarEleitor("Ana", "Joao", "Ana Clone");

facade.escolherInquiridor("Joao");
facade.escolherInquirido("Maria");

facade.iniciarDebate();
facade.getLogs();
```

---

## Padrões de Projeto Utilizados

### Facade

A classe `Facade` centraliza o acesso ao sistema.

### Singleton

Usado nas classes `Facade` e `Logger`.

### Mediator

Usado na classe `MediadorDebate`, responsável por coordenar pergunta, resposta, réplica e tréplica.

### Observer

Usado para enviar notificações aos eleitores quando o candidato preferido estiver falando.

### Builder

Usado para criar objetos complexos com mais segurança e organização.

### Prototype

Usado para clonar objetos existentes, como políticos, eleitores e usuário operador.

---

## Considerações Finais

Esta versão refatora a versão 1.0 do sistema, adicionando Builder e Prototype sem remover as funcionalidades anteriores.

O projeto continua funcionando via terminal e mantém a `Facade` como principal ponto de controle.

---

**Desenvolvido por Osmar dos Santos Filho**
