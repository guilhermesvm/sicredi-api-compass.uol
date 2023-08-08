package test;

import static constants.Endpoints.SIMULACOES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import datafactory.DynamicFactory;
import io.restassured.response.Response;
import model.Simulation;
import services.BaseTest;

public class SimulacoesTest extends BaseTest{
	private static Faker faker = new Faker();
	private static Simulation simulation = new Simulation();
	private static Simulation alteracao = new Simulation();
	
	@Test
	public void listarSimulacoes() {
		Response response = rest.get(SIMULACOES);
		assertThat(response.statusCode(), is(200));
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
		assertThat(response.path("id"), is(instanceOf(ArrayList.class)));
		assertThat(response.path("nome"), is(instanceOf(ArrayList.class)));
		assertThat(response.path("cpf"), is(instanceOf(ArrayList.class)));
		assertThat(response.path("email"), is(instanceOf(ArrayList.class)));
		assertThat(response.path("valor"), is(instanceOf(ArrayList.class)));
		assertThat(response.path("parcelas"), is(instanceOf(ArrayList.class)));
		assertThat(response.path("seguro"), is(instanceOf(ArrayList.class)));
	}
	
	@Test
	public void listarSimulacaoPorCPF() {
		//Cria simulação
		simulation = DynamicFactory.generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		
		//Pega o cpf
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		
		//Pega o id
		Integer id = criar.path("id");
		simulation.setId(id);
		
		//Lista a simulação pelo CPF
		Response response = rest.getCPF(SIMULACOES, cpf);
		assertThat(response.statusCode(), is(200));
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
		assertThat(response.path("valor"), is(instanceOf(Float.class)));
		assertThat(response.path("parcelas"), is(instanceOf(Integer.class)));
		assertThat(response.path("seguro"), is(instanceOf(Boolean.class)));
		
		//Deleta a simulação
		rest.delete(SIMULACOES, id);
	}

	@Test
	public void naoListarSimulacaoPorCPF() {
		String cpf = "12345678911";
		Response response = rest.getCPF(SIMULACOES, cpf);
		assertThat(response.statusCode(), is(404));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		assertThat(response.path("mensagem"), is("CPF "+ cpf + " não encontrado"));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
	}
	
	@Test
	public void naoListarSimulacaoPorCPFMaiorQueOPadrao() {
		String cpf = "123451235344564563456";
		Response response = rest.getCPF(SIMULACOES, cpf);
		assertThat(response.statusCode(), is(404));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Impossível realizar busca com CPF fora do padrão."));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
	}
	
	@Test
	public void naoListarSimulacaoPorCPFMenorQueOPadrao() {
		String cpf = "123";
		Response response = rest.getCPF(SIMULACOES, cpf);
		assertThat(response.statusCode(), is(404));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Impossível realizar busca com CPF fora do padrão."));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
	}
	
	@Test
	public void naoListarSimulacaoPorCPFInvalido() {
		Response response = rest.getCPF(SIMULACOES, "abcd123♬♬♬♬1324sadd♬");
		assertThat(response.statusCode(), is(404));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Impossível realizar busca com CPF inválido."));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
	}
	
	@Test 
	public void criarSimulacao() {
		simulation = DynamicFactory.generateRandomSimulation(true);
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
		
		//Ajuda do Leo
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoCriarSimulacaoComTodosOsCamposEmBranco() {
		simulation.setNome(" ");
		simulation.setCpf(" ");
		simulation.setEmail(" ");
		simulation.setValorr(" ");
		simulation.setParcelass(" ");
		simulation.setSeguroo(" ");
		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(400));
		assertThat(response.asString(), containsString("erros"));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		assertThat(response.path("erros.valor"), is(not(nullValue())));
		assertThat(response.path("erros.parcelas"), is(not(nullValue())));
		//assertThat(response.path("erros.email"), is("não é um endereço de e-mail"));
		assertThat(response.path("erros.valor"), is("Valor não pode ser vazio"));
		assertThat(response.path("erros.parcelas"), is("Parcelas não pode ser vazio"));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.path("erros.valor"), is(instanceOf(String.class)));
		assertThat(response.path("erros.parcelas"), is(instanceOf(String.class)));
		
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
		//assertThat(response.asString(), containsString("erros"));
		//assertThat(response.path("erros"), is(not(nullValue())));
		//assertThat(response.path("erros.nome"), is(not(nullValue())));
		//assertThat(response.path("erros.cpf"), is(not(nullValue())));
		//assertThat(response.path("erros.nome"), is("Nome deve ser um nome válido"));
		//assertThat(response.path("erros.cpf"), is("CPF deve ser um CPF válido"));

		
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
		//assertThat(response.asString(), containsString("erros"));
		//assertThat(response.path("erros"), is(not(nullValue())));
		//assertThat(response.path("erros.cpf"), is(not(nullValue())));
		//assertThat(response.path("erros.cpf"), is("CPF deve ser um CPF válido"));
		
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoCriarSimulacaoComTodosCamposVazios() {
		simulation.setNome("");
		simulation.setCpf("");
		simulation.setEmail("");
		simulation.setValorr("");
		simulation.setParcelass("");
		simulation.setSeguroo("");
		
		Response response = rest.post(SIMULACOES, simulation);
		assertThat(response.statusCode(), is(400));
		assertThat(response.asString(), containsString("erros"));
		assertThat(response.path("erros"), is(not(nullValue())));
		assertThat(response.path("erros.email"), is(not(nullValue())));
		
		Integer id = response.path("id");
		simulation.setId(id);
		
	}
	
	@Test
	public void naoCriarSimulacaoComNomeCPFVazios_BUG() {
		simulation.setNome(" ");
		simulation.setCpf(" ");
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(true);
		Response response = rest.post(SIMULACOES, simulation);
		//assertThat(response.statusCode(), is(400));
		//assertThat(response.asString(), containsString("erros"));
		//assertThat(response.path("erros"), is(not(nullValue())));
		//assertThat(response.path("erros.nome"), is(not(nullValue())));
		//assertThat(response.path("erros.cpf"), is(not(nullValue())));
		//assertThat(response.path("erros.nome"), is("Nome deve ser um nome válido"));
		//assertThat(response.path("erros.cpf"), is("CPF deve ser um CPF válido"));
		
		Integer id = response.path("id");
		simulation.setId(id);
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoCriarSimulacaoComCaracteresInvalidosEmAlgunsCampos_BUG() {
		simulation.setNome("Eduardo Silv✂✂✂✂");
		simulation.setCpf("423423✂✂✂✂✂");
		simulation.setEmail("qa✂✂✂@gmail.com");
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(true);
		Response response = rest.post(SIMULACOES, simulation);
		//assertThat(response.statusCode(), is(400));
		//assertThat(response.asString(), containsString("erros"));
		//assertThat(response.path("erros"), is(not(nullValue())));
		//assertThat(response.path("erros.nome"), is(not(nullValue())));
		//assertThat(response.path("erros.cpf"), is(not(nullValue())));
		//assertThat(response.path("erros.nome"), is("Nome deve ser um nome válido"));
		//assertThat(response.path("erros.cpf"), is("CPF deve ser um CPF válido"));
		
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
		//assertThat(response.path("erros.email"), is("não é um endereço de e-mail"));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
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
		//assertThat(response.asString(), containsString("erros"));
		//assertThat(response.path("erros"), is(not(nullValue())));
		//assertThat(response.path("erros.valor"), is(not(nullValue())));
		//assertThat(response.path("erros.valor"), is("Valor deve ser maior ou igual a R$ 1.000"));
		
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
		//assertThat(response.asString(), containsString("erros"));
		//assertThat(response.path("erros"), is(not(nullValue())));
		//assertThat(response.path("erros.parcelas"), is(not(nullValue())));
		//assertThat(response.path("erros.parcelas"), is("Parcelas deve ser igual ou menor que 48"));
		
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
		//assertThat(response.path("erros.email"), is("E-mail deve ser um e-mail válido"))
		assertThat(response.path("erros.valor"), is("Valor deve ser menor ou igual a R$ 40.000"));
		assertThat(response.path("erros.parcelas"), is("Parcelas deve ser igual ou maior que 2"));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		assertThat(response.path("erros.valor"), is(instanceOf(String.class)));
		assertThat(response.path("erros.parcelas"), is(instanceOf(String.class)));
	}
	
	@Test
	public void alterarSimulacao( ) {
		//Cria simulação
		simulation = DynamicFactory.generateRandomSimulation(true);
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
		
		//Deleta simulação
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarSimulacaoInexistente( ) {
		String cpf = "049128412904";
		
		alteracao.setNome(faker.name().fullName());
		alteracao.setCpf(faker.number().digits(11).toString());
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		Response response = rest.put(SIMULACOES, alteracao, cpf);
		assertThat(response.statusCode(), is(404));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		assertThat(response.path("mensagem"), is("CPF "+ cpf + " não encontrado"));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
	}
	
	@Test
	public void naoAlterarTodosDadosValidosPorCampoEmBranco() {
		simulation = DynamicFactory.generateRandomSimulation(true);
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
		//assertThat(response.path("erros.nome"), is(not(nullValue())));
		//assertThat(response.path("erros.cpf"), is(not(nullValue())));
		//assertThat(response.path("erros.valor"), is(not(nullValue())));
		//assertThat(response.path("erros.parcelas"), is(not(nullValue())));
		//assertThat(response.path("erros.seguro"), is(not(nullValue())));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarNomeECPFValidosPorCampoEmBranco_BUG() {
		simulation = DynamicFactory.generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
		
		alteracao.setNome(" ");
		alteracao.setCpf(" ");
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf);
		//assertThat(response.statusCode(), is(400));
		//assertThat(response.path("erros"), is(not(nullValue())));
		//assertThat(response.path("erros.email"), is(not(nullValue())));
		//assertThat(response.path("erros.nome"), is(not(nullValue())));
		//assertThat(response.path("erros.cpf"), is(not(nullValue())));
	
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarTodosDadosValidosPorCampoVazio() {
		simulation = DynamicFactory.generateRandomSimulation(true);
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
		//assertThat(response.path("erros.nome"), is(not(nullValue())));
		//assertThat(response.path("erros.cpf"), is(not(nullValue())));
		//assertThat(response.path("erros.valor"), is(not(nullValue())));
		//assertThat(response.path("erros.parcelas"), is(not(nullValue())));
		//assertThat(response.path("erros.seguro"), is(not(nullValue())));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarNomeECPFValidosPorCampoVazio_BUG() {
		simulation = DynamicFactory.generateRandomSimulation(true);
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
		//assertThat(response.path("erros"), is(not(nullValue())));
		//assertThat(response.path("erros.nome"), is(not(nullValue())));
		//assertThat(response.path("erros.cpf"), is(not(nullValue())));
		
		rest.delete(SIMULACOES, id);
	}
	
	@Test
	public void naoAlterarAlgunsDadosValidosPorCaracteresInvalidos_BUG() {
		simulation = DynamicFactory.generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf");
		simulation.setCpf(cpf);
		Integer id = criar.path("id");
		simulation.setId(id);
		
		alteracao.setNome("✇");
		alteracao.setCpf("✇");
		alteracao.setEmail("joaoqa✇@gmail.com");
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf);
		//assertThat(response.statusCode(), is(400));
		//assertThat(response.path("erros"), is(not(nullValue())));
		//assertThat(response.path("erros.email"), is(not(nullValue())));
		//assertThat(response.path("erros.nome"), is(not(nullValue())));
		//assertThat(response.path("erros.cpf"), is(not(nullValue())));
		//assertThat(response.path("erros.valor"), is(not(nullValue())));
		//assertThat(response.path("erros.parcelas"), is(not(nullValue())));
		//assertThat(response.path("erros.seguro"), is(not(nullValue())));
		
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
		//assertThat(response.asString(), containsString("mensagem"));
		//assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Registro excluído com sucesso."));	

		
		rest.delete(SIMULACOES, id);
		rest.delete(SIMULACOES, id2);
	}
	
	@Test 
	public void naoAlterarEmailValidoPorOutroSemArroba() {
		simulation = DynamicFactory.generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		String cpf = criar.path("cpf"); //Pega o CPF para alteração
		simulation.setCpf(cpf);
		Integer id = criar.path("id"); //Pega o ID para exclusão
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
		//assertThat(response.path("erros.email"), is("não é um endereço de e-mail"));
		assertThat(response.path("erros.email"), is(instanceOf(String.class)));
		
	}
	@Test
	public void excluirSimulacao() {
		simulation = DynamicFactory.generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		Integer id = criar.path("id");
		simulation.setId(id);
		
		Response response = rest.delete(SIMULACOES, id);
		assertThat(response.statusCode(), is(200));
		//assertThat(response.asString(), containsString("mensagem"));
		//assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Registro excluído com sucesso."));
		//assertThat(response.path("mensagem"), is(instanceOf(String.class)));
	}
	
	@Test
	public void excluirSimulacaoNaoExistente() {
		Integer id_nao_existente = 234234233;
		
		Response response = rest.delete(SIMULACOES, id_nao_existente);
		assertThat(response.statusCode(), is(200));
		//assertThat(response.asString(), containsString("mensagem"));
		//assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Nenhum registro excluído."));	
		//assertThat(response.path("mensagem"), is(instanceOf(String.class)));
	}
	
	@Test
	public void excluirSimulacaoJaExcluida() {
		simulation = DynamicFactory.generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		Integer id = criar.path("id");
		simulation.setId(id);
		
		Response response = rest.delete(SIMULACOES, id);
		assertThat(response.statusCode(), is(200));
		
		Response response2 = rest.delete(SIMULACOES, id);
		assertThat(response2.statusCode(), is(200));
		//assertThat(response.asString(), containsString("mensagem"));
		//assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Nenhum registro excluído."));
		//assertThat(response.path("mensagem"), is(instanceOf(String.class)));
		
	}
}
