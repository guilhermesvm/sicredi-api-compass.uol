<div align="center">
    <img src="Img/sprintbanner6.png" width="700px" height="260px">
    <h1> Sprint 6 - Docker, CI e Challenge Final
 </h1>
</div>
Olá! Este será um repositório dedicado para alocar o projeto de automação de testes da [API Sicredi](https://github.com/desafios-qa-automacao/desafio-sicredi). <br>
- Docker; <br>
- CI/CD com GitLab; <br>
- Execução de testes em containers;<br>
- Execução de testes em ambiente de integração contínua; <br>
- Implementação do Challenge Final: [API Sicredi](https://github.com/desafios-qa-automacao/desafio-sicredi).<br>

# Challenge Final - API Simulação de Crédito Sicredi
- [Branch Main](https://gitlab.com/guilhermesm/compass-challenge-final) - Projeto base; <br>
- [Branch Dev](https://gitlab.com/guilhermesm/compass-challenge-final/-/tree/dev?ref_type=heads) - Commits e atualizações.

# Passo a Passo

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

##  Agradecimentos
Gostaria de agradecer ao colega **Higor Milani**, o qual ajudou de uma forma mais constante na resolução do Challenge Final sobre melhora nas assertivas, clean code e resolução de vários bugs (dos mais simples até complexos). Aos demais colegas agradecer por terem tirado várias dúvidas durante Dailys e principalmente no grupo do WhatsApp.  Em suma, agradecer as mentorias Rafael e aulas do Jacques, e a Compass.Uol pela oportunidade de ter participado do Programa de Bolsas Compass.Uol - Automação de Testes em Java Backend.
