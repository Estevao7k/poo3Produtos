# Sistema de Gerenciamento de Produtos com JavaFX (Projeto Universitário)

Este repositório contém o código-fonte de um sistema de desktop para gerenciamento de produtos, desenvolvido como um projeto para a disciplina de Programação Orientada a Objetos. A aplicação foi construída em Java, utilizando a biblioteca JavaFX para a interface gráfica e o Maven para gerenciamento de dependências.

O projeto demonstra a aplicação prática de conceitos fundamentais de POO, manipulação de arquivos, processamento de coleções com a Stream API e a construção de interfaces gráficas modernas com o padrão MVC.

## Principais Tecnologias
- **Linguagem:** Java 17+
- **Interface Gráfica:** JavaFX 17+
- **Build Tool:** Apache Maven
- **Persistência:** Arquivo CSV

## Como Executar o Projeto

1.  **Pré-requisitos:**
    - Garanta que você tenha o Java JDK 17 (ou superior) instalado e configurado.
    - Tenha o JavaFX SDK 17 (ou superior) baixado em seu computador.
    - Tenha o Apache Maven instalado (geralmente já vem integrado em IDEs como IntelliJ e Eclipse).

2.  **Configuração da IDE:**
    - Abra o projeto em sua IDE de preferência. O Maven deverá baixar as dependências do JavaFX automaticamente.

3.  **Execução:**
    - Execute. A aplicação será iniciada.
    - O arquivo de dados `produtos.csv` será criado na raiz do projeto ao salvar o primeiro produto.

<br>

---

<details>
<summary><strong>📄 Relatório Detalhado de Implementação e Design (Clique para expandir)</strong></summary>

### Relatório de Implementação - Sistema de Gerenciamento de Produtos

#### 1. Introdução
Este relatório descreve as funcionalidades implementadas e as decisões de arquitetura e design tomadas durante o desenvolvimento do Sistema de Gerenciamento de Produtos, um aplicativo de desktop construído com Java e JavaFX. O objetivo do projeto foi criar uma solução robusta para o controle de cadastro de produtos, aplicando boas práticas de programação e recursos modernos da plataforma Java.

#### 2. Funcionalidades Implementadas
O sistema atende a todos os requisitos funcionais solicitados:

* **Operações CRUD:** O sistema permite `Cadastrar` um novo produto através de um formulário, `Consultar` um produto específico (lógica implementada no backend), `Listar` todos os produtos em uma tabela principal e `Excluir` um produto existente com uma confirmação de segurança. A funcionalidade de `Editar` também foi implementada.
* **Persistência em CSV:** Os dados dos produtos e suas respectivas categorias são salvos em um arquivo `produtos.csv` separado por ponto-e-vírgula. O sistema carrega os dados deste arquivo ao iniciar e salva as alterações (adição, edição, exclusão) de volta no arquivo, garantindo a persistência dos dados entre as sessões.
* **Relatórios com Stream API:** Foram implementados relatórios de negócio utilizando recursos da Stream API para um processamento de dados declarativo e eficiente, incluindo:
    * Filtragem de produtos com estoque baixo (inferior a 10 unidades).
    * Agrupamento de produtos por setor de categoria.
    * Cálculo da margem de lucro média para cada categoria.

#### 3. Decisões de Design e Arquitetura

* **Padrão Model-View-Controller (MVC):** O projeto foi estruturado para separar as responsabilidades:
    * **Model (`model`):** As classes `Produto`, `Categoria` e `GerenciadorProdutos` representam os dados e a lógica de negócio, sem nenhum acoplamento com a interface.
    * **View (`resources`):** Os arquivos FXML definem a estrutura visual das telas de forma declarativa, permitindo que o design seja alterado sem modificar o código Java.
    * **Controller (`controller`):** As classes `MainViewController` e `ProdutoDialogController` fazem a ponte entre a View e o Model, tratando as interações do usuário.
* **Encapsulamento:** Os atributos das classes de modelo (`Produto`, `Categoria`) foram declarados como `private`, com acesso controlado por meio de métodos `getters` públicos. Isso protege a integridade dos dados e segue as boas práticas de POO.
* **Composição:** A relação entre `Produto` e `Categoria` foi implementada como composição (`Produto` *tem uma* `Categoria`), permitindo que um objeto `Produto` tenha acesso a todas as informações de sua `Categoria`, o que é essencial para a geração de relatórios.
* **JavaFX com FXML:** A escolha de usar FXML para a View permitiu uma clara separação entre a aparência e o comportamento da UI. Isso torna o código mais limpo e facilita a manutenção e o design das telas.
* **API de Streams:** Para os relatórios, a Stream API foi utilizada em vez de loops tradicionais (`for`). Essa abordagem resulta em um código mais conciso, legível e expressivo, demonstrando o uso de recursos modernos do Java para processamento de coleções.
* **Tipos de Dados Específicos:** Foram utilizados `BigDecimal` para valores monetários, garantindo precisão nos cálculos financeiros, e `LocalDate` para manipulação de datas, aproveitando a API moderna de Data e Hora do Java.

#### 4. Conclusão
O projeto foi concluído com sucesso, atendendo a todos os requisitos funcionais e técnicos. A arquitetura escolhida se provou eficaz, resultando em um código organizado, manutenível e que demonstra a aplicação prática de conceitos fundamentais e avançados da linguagem Java.

</details>