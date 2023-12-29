package test;

import static constants.Endpoints.SIMULACOES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static datafactory.TestVariables_Sim.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import datafactory.DynamicFactory;
import io.qameta.allure.Epic;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import model.Simulation;
import services.BaseTest;

@Tag("Simulações")
@Tag("GET")
@Epic("Testes do Endpoint /Simulacoes")
public class GETSimulacoesTest extends BaseTest{
	private static Simulation simulation = new Simulation();
	
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
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/get/200.json"));
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
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/get_{cpf}/200.json"));
		
		//Deleta a simulação
		rest.delete(SIMULACOES, id);
	}

	@Test
	public void naoListarSimulacaoPorCPF() {
		Response response = rest.getCPF(SIMULACOES, CPFValidoMasNaoExistenteNoBanco);
		assertThat(response.statusCode(), is(404));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		assertThat(response.path("mensagem"), is("CPF "+ CPFValidoMasNaoExistenteNoBanco + " não encontrado"));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/get_{cpf}/400.json"));
	}
	
	@Test
	public void naoListarSimulacaoPorCPFMaiorQueOPadrao() {
		Response response = rest.getCPF(SIMULACOES, CPFMaiorQueOnze);
		assertThat(response.statusCode(), is(404));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Impossível realizar busca com CPF fora do padrão."));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/get_{cpf}/400.json"));
	}
	
	@Test
	public void naoListarSimulacaoPorCPFMenorQueOPadrao() {
		Response response = rest.getCPF(SIMULACOES, CPFMenorQueOnze);
		assertThat(response.statusCode(), is(404));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Impossível realizar busca com CPF fora do padrão."));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/get_{cpf}/400.json"));
	}
	
	@Test
	public void naoListarSimulacaoPorCPFComCaracterEspecial() {
		Response response = rest.getCPF(SIMULACOES, CPFComCaracterEspecial);
		assertThat(response.statusCode(), is(404));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		//assertThat(response.path("mensagem"), is("Impossível realizar busca com CPF inválido."));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/get_{cpf}/400.json"));
	}
}