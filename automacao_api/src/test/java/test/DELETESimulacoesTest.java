package test;

import static constants.Endpoints.SIMULACOES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import datafactory.DynamicFactory;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import model.Simulation;
import services.BaseTest;

@Tag("Simulações")
@Tag("DELETE")
@Epic("Testes DELETE do Endpoint /Restricoes")
public class DELETESimulacoesTest extends BaseTest{

	private static Simulation simulation = new Simulation();
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
		//assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/delete}/200.json"));
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
		//assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/delete}/200.json"));
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
		//assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/simulacoes/delete}/200.json"));
		
	}
}
