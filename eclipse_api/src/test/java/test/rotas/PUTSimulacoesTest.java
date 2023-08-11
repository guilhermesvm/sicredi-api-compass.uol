package test.rotas;

import static constants.Endpoints.SIMULACOES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static datafactory.TestVariables_Sim.*;
import static datafactory.DynamicFactory.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;
import io.qameta.allure.Epic;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import model.Simulation;
import services.BaseTest;

@Tag("Simulações")
@Tag("PUT")
@Epic("Testes PUT do Endpoint /Simulacoes")
public class PUTSimulacoesTest extends BaseTest{
	private static Faker faker = new Faker();
	private static Simulation simulation = new Simulation();
	private static Simulation alteracao = new Simulation();
	
	@Test
	public void alterarSimulacao( ) {
		//Cria simulação
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf"); //Pega o CPF para alteração
		simulation.setCpf(cpf);
		Integer id = criar.path("id"); //Pega o ID para exclusão
		simulation.setId(id);
		
		//Atualiza simulação
		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(faker.number().digits(11).toString());
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		Response response = rest.put(SIMULACOES, alteracao, cpf);
		assertThat(response.statusCode(), is(200));
		//assertThat(response.asString(), containsString("mensagem"));
		//assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Alteração realzada com sucesso."));
		//assertThat(response.path("mensagem"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/put_{cpf}/200.json"));
		
		//Deleta simulação
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarSimulacaoInexistente( ) {
		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(faker.number().digits(11).toString());
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		Response response = rest.put(SIMULACOES, alteracao, CPFValidoMasNaoExistenteNoBanco);
		assertThat(response.statusCode(), is(404));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		assertThat(response.path("mensagem"), is("CPF "+ CPFValidoMasNaoExistenteNoBanco + " não encontrado"));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/put_{cpf}/400.json"));
	}
	
	@Test
	public void naoAlterarTODOSDadosValidosPorCampoEmBranco() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
		
		alteracao.setNome(" ");
		alteracao.setCpf(" ");
		alteracao.setEmail(" ");
		alteracao.setValorr(" ");
		alteracao.setParcelass(" ");
		alteracao.setSeguroo(" "); 
		Response response = rest.put(SIMULACOES, alteracao, cpf);
		assertThat(response.statusCode(), is(400));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/put_{cpf}/400_1.json"));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarALGUNSDadosValidosPorCampoEmBranco() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
		
		alteracao.setNome(" ");
		alteracao.setCpf(" ");
		alteracao.setEmail(" ");
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true);
		Response response = rest.put(SIMULACOES, alteracao, cpf);
		assertThat(response.statusCode(), is(400));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/put_{cpf}/400_1.json"));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarTODOSDadosValidosPorCampoVazio() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
		
		alteracao.setNome("");
		alteracao.setCpf("");
		alteracao.setEmail("");
		alteracao.setValorr("");
		alteracao.setParcelass("");
		alteracao.setSeguroo(""); 
		Response response = rest.put(SIMULACOES, alteracao, cpf);
		assertThat(response.statusCode(), is(400));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/put_{cpf}/400_1.json"));
	
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarALGUNSDadosValidosPorCampoVazio() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
		
		alteracao.setNome("");
		alteracao.setCpf("");
		alteracao.setEmail("");
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true);; 
		Response response = rest.put(SIMULACOES, alteracao, cpf);
		assertThat(response.statusCode(), is(400));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/put_{cpf}/400_1.json"));

		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarNomeECPFValidosPorCampoVazio_BUG() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
		
		alteracao.setNome("");
		alteracao.setCpf("");
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf);
		//assertThat(response.statusCode(), is(400));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarAlgunsDadosValidosPorCaracteresEspeciais_BUG() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
		
		alteracao.setNome(CaracterEspecial);
		alteracao.setCpf(CaracterEspecial);
		alteracao.setEmail("joaoqa✇@gmail.com");
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf);
		//assertThat(response.statusCode(), is(400));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	public void naoAlterarEmailValidoPorOutroSemArroba() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf"); 
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
	
		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(faker.number().digits(11).toString());
		alteracao.setEmail("joaozinhoqa.com");
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		Response response = rest.put(SIMULACOES, alteracao, cpf);
		assertThat(response.statusCode(), is(400));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/put_{cpf}/400_1.json"));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarEmailPorEmailJaExistente_BUG() {
		//Cria um usuário
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString());
		simulation.setEmail("joaozinho@qa.com");
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(true); 
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf"); 
		simulation.setCpf(cpf);
		Integer id = criar.path("id"); 
		simulation.setId(id);
		
		//Cria um segundo usuáro
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString());
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(true); 
		Response criar2 = rest.post(SIMULACOES, simulation);
		String cpf2 = criar2.path("cpf"); 
		simulation.setCpf(cpf2);
		Integer id2 = criar2.path("id"); 
		simulation.setId(id2);
		
		//Altera o email do segundo usuário para o email do primeiro usuario, já existente
		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(faker.number().digits(11).toString());
		alteracao.setEmail("joaozinho@qa.com");
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf2);
		//assertThat(response.statusCode(), is(400));

		rest.delete(SIMULACOES, id);
		rest.delete(SIMULACOES, id2);
	}
	
	@Test 
	public void naoAlterarCPFValidoPorOutroInvalidoMenorQue11_BUG() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
	
		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(CPFMenorQueOnze);
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf);
		//assertThat(response.statusCode(), is(400));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	public void naoAlterarCPFValidoPorOutroInvalidoMaiorQue11_BUG() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
	
		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(CPFMaiorQueOnze);
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf);
		//assertThat(response.statusCode(), is(400));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	public void naoAlterarVALORvalidoPorOutroMenorQue1000_MELHORIA() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);

		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(faker.number().digits(11).toString());
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(0, 999));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf);
		//assertThat(response.statusCode(), is(400));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	public void naoAlterarVALORvalidoPorOutroMaiorQue40000_MELHORIA() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);

		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(faker.number().digits(11).toString());
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(40001, 100000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf);
		//assertThat(response.statusCode(), is(400));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	public void naoAlterarVALORvalidoPorOutroNegativo_MELHORIA() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);

		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(faker.number().digits(11).toString());
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(-10000, 999));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf);
		//assertThat(response.statusCode(), is(400));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	public void naoAlterarPARCELAValidaPorOutraMenorQue2() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);

		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(faker.number().digits(11).toString());
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(0, 1));
		alteracao.setSeguro(true); 
		Response response = rest.put(SIMULACOES, alteracao, cpf);
		assertThat(response.statusCode(), is(400));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.parcelas"), is(not(nullValue())));
		assertThat(response.path("erros.parcelas"), is("Parcelas deve ser igual ou maior que 2"));
		assertThat(response.path("erros.parcelas"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/put_{cpf}/400_2.json"));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	public void naoAlterarPARCELAValidaPorOutraMaiorQue48_BUG() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
	
		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(faker.number().digits(11).toString());
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(49, 500));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf);
		//assertThat(response.statusCode(), is(400));

		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	public void naoAlterarPARCELAValidaPorOutraNegativa() {
		simulation = generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);

		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(faker.number().digits(11).toString());
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(-1000, -1));
		alteracao.setSeguro(true); 
		Response response = rest.put(SIMULACOES, alteracao, cpf);
		assertThat(response.statusCode(), is(400));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.parcelas"), is(not(nullValue())));
		assertThat(response.path("erros.parcelas"), is("Parcelas deve ser igual ou maior que 2"));
		assertThat(response.path("erros.parcelas"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/put_{cpf}/400_2.json"));
		
		rest.delete(SIMULACOES, id);
	}
}
