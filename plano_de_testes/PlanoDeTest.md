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
- Se o CPF do cliente tiver uma restrição, uma menságem deverá informá-lo;

###  **Casos de Teste**
- CT_001: Listar um CPF válido sem restrição;
- CT_002: Listar um CPF inválido (com string ou caracteres inválidos);
- CT_003: Listar um CPF fora dos padrões (menor ou maior que 11 dígitos);
- CT_004: Listar um CPF não existente no banco de dados;
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
- CPF é obrigatório. Texto informado deverá ser 99999999999 (11 dígitos inteiros no total) e não 999.999.999-99;
- Nome é obrigatório. Texto informado deverá ser o nome da pessoa;
- Email é obrigatório. Texto informado deverá ser um email válido;
- Valor é obrigatório. Valor inteiro (integer) informado deverá ser maior ou igual a R$1000.00 e menor ou igual a R$40000.00
- Parcela é obrigatório. Número de parcelas de pagamento inteiro (integer) deverá ser maior ou igual a 2 e menor ou igual a 48;
- Seguro é obrigatório. Booleano deverá ser true, com seguro, ou false, sem seguro.

### **Testes Candidatos à Automação**

###  **Casos de Teste**
- CT_001: Listar simulações;
- CT_001: Listar simulação por CPF existente sem restrição;
- CT_001: Listar simulação por CPF existente com restrição;
- CT_001: Listar simulação por CPF inexistente;
- CT_001: Listar simulação por CPF fora dos padrões;
- CT_001: Listar simulação por CPF com caracteres inválidos;
- CT_001: Criar simulação com TODOS os campos preenchidos corretamente;
- CT_001: Criar simulação com ALGUNS campos em branco;
- CT_001: Criar simulação com TODOS os campos em branco;
- CT_001: Criar simulação com ALGUNS campos vazios;
- CT_001: Criar simulação com TODOS os campos vazios;
- CT_001: Criar simulação utilizando caracteres inválidos (especiais) em ALGUNS/TODOS os campos;
- CT_001: Criar simulação com "CPF" inválido;
- CT_001: Criar simulação com "nome" inválido;
- CT_001: Criar simulação com "email" sem o '@', para verificar o padrão válido de email;
- CT_001: Criar simulação com "valor" inválido (menor que RS$1000.00/maior que R$40000.00);
- CT_001: Criar simulação com "parcela" inválido(menor que 2/maior que 48);
- CT_001: Criar simulação com "seguro" inválido (sendo uma string ou integer);
- CT_001: Alterar simulação com CPF existente (cadastrado);
- CT_001: Alterar simulação com CPF inexistente (não cadastrado);




# 5. Local dos Testes
Todos os testes foram criados e testados localmente no meu computador pessoal pelo endereço: http://localhost:8080, o qual emula o ambiente da API.

# 6. Recursos Necessários
- Infraestrutura:
    - Hardware: computador pessoal e periféricos;
    - Internet.

- Softwares:
    - Java 8+ e JDK;
	- Eclipse (Java IDE);
    - Maven: RestAssured, JUnit 5, Lombok, Jackson (Core/Databind), JavaFaker, JSONSchema Validator, Wiremock, GSON, Allure Reports;
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

