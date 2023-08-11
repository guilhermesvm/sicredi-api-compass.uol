<div align="center">
    <br>
    <h1 align="center"> Plano de Teste </h1>
    [[API] Sicredi - Simulação de Crédito](https://github.com/desafios-qa-automacao/desafio-sicredi/tree/master)
</div>


#  Sumário
<!--ts-->
   * [Resumo](#1-resumo)
   * [Pessoas Envolvidas](#2-pessoas-envolvidas)
   * [Passo a Passo](#3-passo-a-passo)
   * [Funcionalidade e Módulo à Ser Testado](#4-funcionalidades-e-módulos-à-serem-testados)
      * [⛔ US| Restrições](#41-⛔-restrições)
         * [DoR e DoD](#dor-e-dod)
	    * [Critérios de Aceitação](#critérios-de-aceitação)
	    * [Casos de Teste](#casos-de-teste)
        * [Testes Candidatos à Automação](#testes-candidatos-à-automação)
     * [🌀 US| Simulações](#42--🌀-simulações)
	    * [DoR e DoD](#dor-e-dod-1)
	    * [Critérios de Aceitação](#critc3a9rios-de-aceitac3a7c3a3o-1)
	    * [Casos de Teste](#casos-de-teste-1)
        * [Testes Candidatos à Automação](#testes-candidatos-c3a0-automac3a7c3a3o-1)
   * [Local dos Testes](#5-local-dos-testes)
   * [Recursos Necessários](#6-recursos-necessários)
   * [Cronograma](#7-cronograma)
   

<!--te-->


# 1. Resumo
- Trazer um projeto de automação de testes para as áreas "/Restrições" e "/Simulações", e seus respectivos verbos HTTP (GET, POST, PUT, DELETE) da [API Sicredi](https://github.com/desafios-qa-automacao/desafio-sicredi), os quais se encontram na documentação Swagger (a documentação só poderá ser acessada a partir do localhost:8080, ou seja, apenas localmente pela máquina de teste);
- O objetivo é garantir que um cliente consiga pesquisar se seu CPF possui restrições e simular empréstimos de crédito. As áreas descritas serão testadas para garantir um fluxo de maneira funcional, com o fim de listar de restrições e criar/alterar/deletar/listar simulações de crédito. Para isso, o plano foi baseado nos critérios de aceitação presentes nas User Stories (US): US|⛔ Restrições, US|🌀 Simulações.
- O projeto possui [códigos de Automação de Teste](https://gitlab.com/guilhermesm/compass-challenge-final/-/tree/main/eclipse_api?ref_type=heads) escritos em Java, na IDE Eclipse, [Mapa Mental](https://gitlab.com/guilhermesm/compass-challenge-final/-/tree/main/mapa_mental?ref_type=heads) e um [repositório de Bugs e Melhorias](https://guilhermesvmachado.atlassian.net/jira/software/projects/CF/boards/6) no Jira Software.

# 2. Pessoas Envolvidas
- Equipe Testadora: Guilherme Machado e demais membros do QA Studio Compass.uol;
- Público-alvo: Clientes da Cooperativa de Crédito Sicredi®.

# 3. Passo a Passo

## Configurando o Ambiente
- Procurar por "variáveis" na caixa de pesquisa do Windows e escolher a opção para Modificar as configurações das variáveis de ambiente do sistema. Clicar em Ajustes das Variáveis de Ambiente. Na seção de Variáveis do sistema, selecionar Adicionar.
- Na janela que aparecer, preencher os detalhes e clicar em Confirmar, seguindo a representação visual abaixo. Utilizar o caminho em seu PC onde o JDK foi instalado (não considerar a pasta 'bin').
Na janela que surgir, preencher os detalhes e clicar em Confirmar, seguindo a representação visual abaixo. Utilizar o caminho em seu PC onde o JDK foi instalado (não incluir a pasta 'bin').

- Encontrar e escolher a variável Rota (Path), e então clicar em Modificar. Na janela que se mostrar, clicar em Novo: 
Adicionar ambos os diretórios: C:\apache-maven-3.8.6\bin e C:\jdk-18.0.2\bin. Lembrar-se de que esses devem ser os caminhos de instalação em seu dispositivo!

- Clicar em Confirmar até fechar todas as janelas resultantes das configurações de variáveis!

 * Mais informações sobre o que foi utilizado para a criação dos códigos podem ser encontradas em [Recursos Necessários](#6-recursos-necessários).

## Como executar a API Localmente?
- Para iniciar a API localmente é possivel fazer o download dela clicando aqui: [Desafio QA - API Sicredi](https://github.com/desafios-qa-automacao/desafio-sicredi). 
- Alterar a versão do Lombok em: \desafio-sicredi\prova-tecnica-api\pom.xml.
Na linha 55, substitua a versão 1.18.8 pela 1.18.28.

- Na pasta raiz do projeto (\desafio-sicredi\prova-tecnica-api), abra o terminal (com CMD, GitBash....) e execute o comando a seguir:
`````
mvn clean spring-boot:run
`````

- Dessa forma, a API será executada por padrão na porta 8080. Caso haja necessidade de alterar a porta, utilize o comando a seguir:
(no lugar de {porta}, insira a porta desejada. Exemplo: ```-Dserver.port=3000``` )
`````
mvn clean spring-boot:run -Dserver.port={porta}
`````

- Após a API estar em execução, você pode acessar a [Documentação Swagger](http://localhost:8080/swagger-ui.html#).



## Como executar os Testes e ver seus Resultados?
Para executá-los e ver seus gráficos localmente, faça o download do repositório, abra a pasta raiz e execute os seguintes comandos:

**Para rodar os testes:**  `````mvn test -Denv=local`````

**Para criar relatórios dos testes realizados:** `````mvn allure:report`````



**Para iniciar uma página HTML com gráficos e demais informações sobre os testes:** `````mvn allure:serve`````


- Pronto! Agora você consegue analisar todos os testes criados para a API Sicredi - Simulação de Crédito.

# 4. Funcionalidades e Módulos à Serem Testados

## 4.1 ⛔ **[/Restrições]**
<div align="center">
    <p> Como um cliente da cooperativa de crédito Sicredi, gostaria de poder checar se meu CPF possui restrições ou não. </p>
</div>

### **DoR e DoD**
- DoR
    - Documentação acessível;
    - Banco de dados e infraestrutura para desenvolvimento disponibilizados;
    - Ambientes de testes configurados e funcionais;
    - Requisitos claros;
    - Rota /Simulações implementada;
    - Riscos encontrados e analisados.

- DoD
    - Verbo GET de restrições implementado;
    - Testes cobrindo o verbo;
    - Testes serem executados com sucesso;
    - Bom número de validações em cada teste; 
    - Automação de testes realizada;
    - Matriz de rastreabilidade atualizada;
    - Feedback de resultados;
    - Padrões de codificação e organização.

### **Critérios de Aceitação**
- Se o CPF do cliente tiver uma restrição, uma mensagem deverá informá-lo;

###  **Casos de Teste**
- CT_001: Listar um CPF válido sem restrição;
- CT_002: Listar um CPF inválido (com letra);
- CT_003: Listar um CPF fora dos padrões (menor que 11 dígitos);
- CT_004: Listar um CPF VÁLIDO, porém não existente no banco de dados;
- CT_005: Listar um CPF válido com restrição;
- CT_006: Listar um CPF inválido (caracteres especiais);
- CT_007: Listar um CPF fora dos padrões (maior que 11 dígitos);
- CT_008: Listar um CPF na forma padrão, porém inválido (ex: 000000000000);
- CT_009: Listar um CPF com espaços em branco;
- CT_010: Listar um CPF vazio;


### **Testes Candidatos à Automação**
- CT_001: Listar um CPF válido sem restrição; ✔️
- CT_002: Listar um CPF inválido (com letra); ✔️
- CT_003: Listar um CPF fora dos padrões (menor que 11 dígitos); ✔️ 
- CT_004: Listar um CPF VÁLIDO, porém não existente no banco de dados; ✔️
- CT_005: Listar um CPF válido com restrição; ✔️
- CT_006: Listar um CPF inválido (caracteres especiais); ✔️
- CT_007: Listar um CPF fora dos padrões (maior que 11 dígitos); ✔️
- CT_008: Listar um CPF na forma padrão, porém inválido (ex: 000000000000); ✔️
- CT_009: Listar um CPF com espaços em branco; ✔️
- CT_010: Listar um CPF vazio; ✔️

## 4.2 ** 🌀 [/Simulações]**
<div align="center">
    <p> Como um cliente da cooperativa de crédito Sicredi, gostaria de poder criar simulações de crédito e empréstimos. </p>
</div>

### **DoR e DoD**
- **DoR**
    - Análise de testes cobrindo a rota de /Restricoes  
    - Documentação acessível;
    - Banco de dados e infraestrutura para desenvolvimento disponibilizados;
    - Ambiente de testes disponibilizado;
    - Ambientes de testes configurados e funcionais;
    - Requisitos claros;
    - Riscos encontrados e analisados.

- **DoD**
    - CRUD (GET/POST/PUT/DELETE) de simulações implementados
    - Análise de testes cobrindo a rota de /Restricoes;
    - Testes serem executados com sucesso;
    - Bom número de validações em cada teste; 
    - Automação de testes realizada;
    - Matriz de rastreabilidade atualizada;
    - Feedback de resultados;
    - Padrões de codificação e organização.

###  **Critérios de Aceitação**
- CPF é obrigatório. Texto informado deverá ser 99999999999 (11 caracteres no total) e não 999.999.999-99;
- Nome é obrigatório. Texto informado deverá ser o nome da pessoa;
- Email é obrigatório. Texto informado deverá ser um email válido;
- Valor é obrigatório. Valor inteiro (integer) informado deverá ser maior ou igual a R$1000.00 e menor ou igual a R$40000.00
- Parcela é obrigatório. Número de parcelas de pagamento inteiro (integer) deverá ser maior ou igual a 2 e menor ou igual a 48;
- Seguro é obrigatório. Booleano deverá ser true, com seguro, ou false, sem seguro.
- Clientes que já possuem CPF com simulaçãocadastrada não poderão realizar outras simulações;

###  **Casos de Teste**
- CT_001: Listar simulações;
- CT_002: Listar simulação por CPF existente;
- CT_003: Listar simulação por CPF inexistente;
- CT_004: Listar simulação por CPF fora dos padrões (menor que 11 dígitos);
- CT_005: Listar simulação por CPF fora dos padrões (maior que 11 dígitos);
- CT_006: Listar simulação por CPF com caracteres inválidos (letra e/ou caracteres especiais);
- CT_007: Criar simulação com TODOS os campos preenchidos corretamente;
- CT_008: Criar simulação com TODOS campos em branco;
- CT_009: Criar simulação com ALGUNS campos em branco;
- CT_010: Criar simulação com "nome" e "cpf em branco;
- CT_011: Criar simulação com TODOS campos vazios;
- CT_012: Criar simulação com ALGUNS campos vazios;
- CT_013: Criar simulação com "nome" e "cpf" vazios;
- CT_014: Criar simulação utilizando caracteres inválidos (especiais) em ALGUNS dos campos;
- CT_015: Criar simulação com "email" sem o '@', para verificar o padrão válido de email;
- CT_016: Criar simulação com "valor" inválido (menor que RS$1000.00)
- CT_017: Criar simulação com "valor" inválido (maior que R$40000.00);
- CT_018: Criar simulação com "valor" negativo;
- CT_019: Criar simulação com "parcela" inválido (menor que 2);
- CT_020: Criar simulação com "parcela" inválido (negativa);
- CT_021: Criar simulação com "parcela" inválido (maior que 48);
- CT_022: Criar simulação com "seguro" inválido (sendo uma string ou integer);
- CT_023: Criar simulação com TODOS os campos preenchidos incorretamente;
- CT_024: Alterar simulação com CPF existente (cadastrado);
- CT_025: Alterar simulação com CPF inexistente (não cadastrado);
- CT_026: Alterar TODOS os dados válidos por um campo em branco;
- CT_027: Alterar ALGUNS os dados válidos por um campo em branco;
- CT_028: Alterar TODOS os dados válidos por um campo em vazios;
- CT_029: Alterar ALGUNS os dados válidos por um campo em vazios;
- CT_030: Alterar ALGUNS dados válidos por caracteres especiais;
- CT_031: Alterar email válido por um email sem '@';
- CT_032: Alterar email válido por um email já utilizado;
- CT_033: Alterar CPF válido por um inválido (maior que 11);
- CT_034: Alterar CPF válido por um inválido (menor que 11);
- CT_035: Alterar valor válido por um inválido (menor que RS$1000.00);
- CT_036: Alterar valor válido por um inválido (maior que R$40000.00); 
- CT_037: Alterar valor válido por um inválido (negativo);
- CT_038: Alterar parcela válido por uma inválida; (menor que 2);
- CT_039: Alterar parcela válido por uma inválida; (maior que 48);
- CT_040: Alterar parcela válido por uma inválida; (negativa);
- CT_041: Alterar seguro válido por um string/integer;
- CT_042: Excluir simulação com ID existente (cadastrado);
- CT_043: Excluir simulação com ID não existente (não cadastrado);
- CT_044: Excluir simulação previamente excluída;
- CT_045: Criar simulação com "cpf" como integer;
- CT_046: Criar simulação com "valor" e "parcelas como string;

### **Testes NÃO Candidatos à Automação**
- CT_046: Criar simulação com "valor" e "parcelas como string; ❌ 

# 5. Local dos Testes
Todos os testes foram criados e testados localmente no meu computador pessoal pelo endereço: http://localhost:8080, o qual emula o ambiente da API.

# 6. Recursos Necessários
- Infraestrutura:
    - Hardware: computador pessoal e periféricos;
    - Internet.

- Softwares:
    - Java 8+ e JDK;
	- Eclipse (Java IDE);
    - Apache Maven e suas dependências: RestAssured, JUnit 5, Project Lombok, Jackson (Core/Databind), JavaFaker, JSONSchema Validator, Wiremock, GSON, Allure Reports;
    - Postman;
    - GitLab;
    - Jira;
	- Xmind;
	- Swagger;


## 7. Cronograma
| Tipo de Teste      | Data de Início  | Data de Término  |
| ------------------ | --------------- | ---------------- |
| Planejamento       | 31/07/2023      | 04/08/2023
| Execução           | 07/08/2023      | 10/08/2023       |
| Avaliação          | 11/08/2023      | 11/08/2023       |
