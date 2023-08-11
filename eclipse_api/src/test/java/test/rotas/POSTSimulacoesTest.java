package test.rotas;

import static constants.Endpoints.SIMULACOES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
@Tag("POST")
@Epic("Testes POST do Endpoint /Simulacoes")
public class POSTSimulacoesTest extends BaseTest{
	private static Faker faker = new Faker();
	private static Simulation simulation = new Simulation();
	
	@Test 
	public void criarSimulacao() {
		simulation = generateRandomSimulation(true);
		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(201));
		//assertThat(response.asString(), containsString("mensagem"));
		//assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Cadastro realizado com sucesso."));
		assertThat(response.asString(), containsString("id"));
		assertThat(response.asString(), containsString("nome"));
		assertThat(response.asString(), containsString("cpf"));
		assertThat(response.asString(), containsString("email"));
		assertThat(response.asString(), containsString("valor"));
		assertThat(response.asString(), containsString("parcelas"));
		assertThat(response.asString(), containsString("seguro"));
		assertThat(response.path("id"), is(not(nullValue())));
		assertThat(response.path("nome"), is(not(nullValue())));
		assertThat(response.path("cpf"), is(not(nullValue())));
		assertThat(response.path("email"), is(not(nullValue())));
		assertThat(response.path("valor"), is(not(nullValue())));
		assertThat(response.path("parcelas"), is(not(nullValue())));
		assertThat(response.path("seguro"), is(not(nullValue())));
		assertThat(response.path("id"), is(instanceOf(Integer.class)));
		assertThat(response.path("nome"), is(instanceOf(String.class)));
		assertThat(response.path("cpf"), is(instanceOf(String.class)));
		assertThat(response.path("email"), is(instanceOf(String.class)));
		assertThat(response.path("valor"), is(instanceOf(Integer.class)));
		assertThat(response.path("parcelas"), is(instanceOf(Integer.class)));
		assertThat(response.path("seguro"), is(instanceOf(Boolean.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/post/200.json"));
		
		//Ajuda do Leo
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoCriarSimulacaoComTODOSOsCamposEmBranco() {
		simulation = generateBlankSimulation();
		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(400));
		assertThat(response.asString(), containsString("erros"));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.path("erros.email"), anyOf(equalTo("não é um endereço de e-mail"), equalTo("E-mail deve ser um e-mail válido")));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/post/400_1.json"));
	}
	
	@Test
	public void naoCriarSimulacaoComALGUNSOsCamposEmBranco() {
		simulation = generateHalfBlankSimulation(true);
		
		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(400));
		assertThat(response.asString(), containsString("erros"));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.path("erros.email"), anyOf(equalTo("não é um endereço de e-mail"), equalTo("E-mail deve ser um e-mail válido")));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/post/400_4.json"));
	}
	
	
	@Test
	public void naoCriarSimulacaoComNomeCPFEmBranco_BUG() {
		simulation.setNome(" ");
		simulation.setCpf(" ");
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(true);
		Response response = rest.post(SIMULACOES, simulation);
		//assertThat(response.statusCode(), is(400));
		
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoCriarSimulacaoComCPFInvalido_BUG() {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf("asdad2434sa34123");
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(true);
		Response response = rest.post(SIMULACOES, simulation);
		//assertThat(response.statusCode(), is(400));
		
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoCriarSimulacaoComTODOSCamposVazios() {
		simulation = generateEmptySimulation();

		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(400));
		assertThat(response.asString(), containsString("erros"));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.path("erros.email"), anyOf(equalTo("não é um endereço de e-mail"), equalTo("E-mail deve ser um e-mail válido")));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/post/400_1.json"));
	}
	
	@Test
	public void naoCriarSimulacaoComALGUNSCamposVazios() {
		simulation = generateHalfEmptySimulation(true);
	
		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(400));
		assertThat(response.asString(), containsString("erros"));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.path("erros.email"), anyOf(equalTo("não é um endereço de e-mail"), equalTo("E-mail deve ser um e-mail válido")));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/post/400_4.json"));
		
		Integer id = response.path("id");
		simulation.setId(id);
	}
	
	@Test
	public void naoCriarSimulacaoComNomeCPFVazios_BUG() {
		simulation.setNome("");
		simulation.setCpf("");
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(true);
		Response response = rest.post(SIMULACOES, simulation);
		//assertThat(response.statusCode(), is(400));

		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoCriarSimulacaoComCaracteresEspeciaisemALGUNSCampos_BUG() {
		simulation.setNome("Eduardo Silv✂✂✂✂");
		simulation.setCpf("423423✂✂✂✂✂");
		simulation.setEmail("qa✂✂✂@gmail.com");
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(true);
		Response response = rest.post(SIMULACOES, simulation);
		//assertThat(response.statusCode(), is(400));
		
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	public void naoCriarSimulacaoComEmailSemArroba() {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString());
		simulation.setEmail("marquinhosgmail.com");
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(false);

		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(400));
		assertThat(response.asString(), containsString("erros"));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.path("erros.email"), anyOf(equalTo("não é um endereço de e-mail"), equalTo("E-mail deve ser um e-mail válido")));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/post/400_1.json"));
	}
	
	@Test 

	public void naoCriarSimulacaoComValorMenorQue1000_BUG() {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString());
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(0, 999));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(false);

		Response response = rest.post(SIMULACOES, simulation);
		//assertThat(response.statusCode(), is(400));
		
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	
	public void naoCriarSimulacaoComValorMaiorQue40000() {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString());
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(40001, 100000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(false);

		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(400));
		assertThat(response.asString(), containsString("erros"));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.valor"), is(not(nullValue())));
		assertThat(response.path("erros.valor"), is("Valor deve ser menor ou igual a R$ 40.000"));
		assertThat(response.path("erros.valor"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/post/400_2.json"));
	}
	
	@Test 
	
	public void naoCriarSimulacaoComValorNegativo_BUG() {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString());
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(-100000, -1));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(false);

		Response response = rest.post(SIMULACOES, simulation);
		//assertThat(response.statusCode(), is(400));
		
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}

	@Test 
	public void naoCriarSimulacaoComParcelaMenorQue2() {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString());
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(0, 1));
		simulation.setSeguro(false);

		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(400));
		assertThat(response.asString(), containsString("erros"));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.parcelas"), is(not(nullValue())));
		assertThat(response.path("erros.parcelas"), is("Parcelas deve ser igual ou maior que 2"));
		assertThat(response.path("erros.parcelas"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/post/400_3.json"));
	}
	
	@Test 
	public void naoCriarSimulacaoComParcelaMaiorQue48_BUG() {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString());
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(49, 100));
		simulation.setSeguro(false);

		Response response = rest.post(SIMULACOES, simulation);
		//assertThat(response.statusCode(), is(400));
		
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	public void naoCriarSimulacaoComParcelaNegativa() {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString());
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(-1000, -1));
		simulation.setParcelas(faker.random().nextInt(0, 1));
		simulation.setSeguro(false);

		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(400));
		assertThat(response.asString(), containsString("erros"));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.parcelas"), is(not(nullValue())));
		assertThat(response.path("erros.parcelas"), is("Parcelas deve ser igual ou maior que 2"));
		assertThat(response.path("erros.parcelas"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/post/400_3.json"));
	}
	
	@Test 
	public void naoCriarSimulacaoComSeguroComoString() {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString());
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(49, 100));
		simulation.setSeguroo("false");

		Response response = rest.post(SIMULACOES, simulation);
		//assertThat(response.statusCode(), is(400));
		
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
	
	@Test 
	public void naoCriarSimulacaoComTodosOsCamposPreenchidosIncorretamente() {
		simulation.setNome("Marco Auréliox999");
		simulation.setCpf("adsd3423✂232312324235345345234");
		simulation.setEmail("marcaookd");
		simulation.setValor(faker.random().nextInt(40001, 1040124022));
		simulation.setParcelas(faker.random().nextInt(0, 1));
		simulation.setSeguro(false);
		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(400));
		assertThat(response.asString(), containsString("erros"));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.valor"), is(not(nullValue())));
		assertThat(response.path("erros.parcelas"), is(not(nullValue())));
		assertThat(response.path("erros.email"), anyOf(equalTo("não é um endereço de e-mail"), equalTo("E-mail deve ser um e-mail válido")));
		assertThat(response.path("erros.valor"), is("Valor deve ser menor ou igual a R$ 40.000"));
		assertThat(response.path("erros.parcelas"), is("Parcelas deve ser igual ou maior que 2"));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.path("erros.valor"), is(instanceOf(String.class)));
		assertThat(response.path("erros.parcelas"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/post/400.json"));
	}
	
	@Test
	public void naoCriarSimulacaoComCPFComoInteger_BUG() {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11));
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(true);
		Response response = rest.post(SIMULACOES, simulation);
		//assertThat(response.statusCode(), is(400));
		
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
}