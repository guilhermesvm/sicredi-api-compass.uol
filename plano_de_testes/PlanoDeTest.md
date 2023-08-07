<div align="center">
    <h1> Plano de Teste | API Sicredi-  Challenge Final </h1>
</div>

# 1. Resumo
- Verificar e testar as áreas "/Restrições" e "/Simulações", e seus respectivos verbos HTTP (GET, POST, PUT, DELETE) da [API Sicredi](https://github.com/desafios-qa-automacao/desafio-sicredi), os quais se encontram na documentação Swagger (a documentação só poderá ser acessada a partir do localhost:8080, ou seja, apenas localmente pela máquina de teste). O objetivo é garantir que um cliente consiga pesquisar se seu CPF possui restrições e simular empréstimos de crédito. As áreas descritas acima serão testadas utilizando RestAssured no Java, para que o projeto consiga seguir seu fluxo de maneira funcional, com o fim delistar de restrições e criar/alterar/deletar/listar simulações de crédito. Para isso, o plano foi baseado nos critérios de aceitação presentes nas User Stories (US): US|⛔ Restrições, US|🌀 Simulações.

# 2. Pessoas Envolvidas
- Equipe Testadora: Guilherme Machado e demais membros do QA Studio Compass.uol;
- Público-alvo: Clientes da Cooperativa de Crédito Sicredi®.

# 3. Passo a Passo

# 4. Funcionalidades e Módulos à Serem Testados

## 4.1 ⛔ **[/Restrições]**

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
- CT_002: Listar um CPF inválido (com letra ou caracteres inválidos);
- CT_003: Listar um CPF fora dos padrões (menor ou maior que 11 dígitos);
- CT_004: Listar um CPF VÁLIDO, porém não existente no banco de dados;
- CT_005: Listar um CPF válido com restrição;


### **Testes Candidatos à Automação**
- N/A.


## 4.2 ** 🌀 [/Simulações]**


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
- CT_004: Listar simulação por CPF inexistente;
- CT_005: Listar simulação por CPF fora dos padrões (menor ou maior que 11 dígitos);
- CT_006: Listar simulação por CPF com caracteres inválidos (letra ou caracteres especiais);
- CT_007: Criar simulação com TODOS os campos preenchidos corretamente;
- CT_008: Criar simulação com ALGUNS campos em branco;
- CT_008: Criar simulação com "nome" e "cpf" em branco;
- CT_009: Criar simulação com ALGUNS campos vazios;
- CT_008: Criar simulação com "nome" e "cpf" vazios;
- CT_010: Criar simulação utilizando caracteres inválidos (especiais) em ALGUNS os campos;
- CT_010: Criar simulação com CPF inválido (string e com caracteres especiais);
- CT_013: Criar simulação com "email" sem o '@', para verificar o padrão válido de email;
- CT_014: Criar simulação com "valor" inválido (menor que RS$1000.00)
- CT_014: Criar simulação com "valor" inválido (maior que R$40000.00);
- CT_015: Criar simulação com "parcela" inválido (menor que 2)
- CT_015: Criar simulação com "parcela" inválido (maior que 48);
- CT_016: Criar simulação com "seguro" inválido (sendo uma string ou integer); *
- CT_016: Criar simulação com TODOS os campos preenchidos incorretamente;
- CT_017: Alterar simulação com CPF existente (cadastrado);
- CT_018: Alterar simulação com CPF inexistente (não cadastrado);
- CT_019: Alterar ALGUNS/TODOS os dados válidos por um campo em branco;
- CT_020: Alterar ALGUNS/TODOS os dados válidos por um campo vazio;
- CT_021: Alterar ALGUNS/TODOS os dados válidos por caracteres inválidos;
- CT_022: Alterar email válido por um email sem '@';
- CT_023: Alterar email válido por um email já utilizado;
- CT_024: Alterar email válido por um email não existente;
- CT_025: Excluir simulação com ID existente (cadastrado);
- CT_026: Excluir simulação com ID não existente (não cadastrado);
- CT_027: Excluir simulação previamente excluída;

### **Testes Candidatos à Automação**




# 5. Local dos Testes
Todos os testes foram criados e testados localmente no meu computador pessoal pelo endereço: http://localhost:8080, o qual emula o ambiente da API.

# 6. Recursos Necessários
- Infraestrutura:
    - Hardware: computador pessoal e periféricos;
    - Internet.

- Softwares:
    - Java 8+ e JDK;
	- Eclipse (Java IDE);
    - Maven: RestAssured, JUnit 5, Project Lombok, Jackson (Core/Databind), JavaFaker, JSONSchema Validator, Wiremock, GSON, Allure Reports;
    - Postman;
    - GitLab;
    - Jira;
	- Xmind;
	- Swagger;
    - Docker;

## 7. Riscos


## 8. Cobertura de Testes




## 9. Cronograma
| Tipo de Teste      | Data de Início  | Data de Término  |
| ------------------ | --------------- | ---------------- |
| Planejamento       | 31/07/2023      | 04/08/2023
| Execução           | 07/08/2023      | 10/08/2023       |
| Avaliação          | 11/08/2023      | 11/08/2023       |

