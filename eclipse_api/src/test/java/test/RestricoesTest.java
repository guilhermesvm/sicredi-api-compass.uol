package test;

import static constants.Endpoints.RESTRICOES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import services.BaseTest;



public class RestricoesTest extends BaseTest{
 
	@Test
	public void listarCPFSemRestricao() {
		Response response = rest.getCPF(RESTRICOES, "66414919004");
		assertThat(response.statusCode(), is(204));	
	}
	
	@Test
	public void naoListarCPFInvalidoMELHORIA() {
		Response response = rest.getCPF(RESTRICOES, "ABVX45462DF54524FCV");
		assertThat(response.statusCode(), is(204));
	}
	
	@Test
	public void naoListarCPFForaDosPadroesMELHORIA() {
		Response response = rest.getCPF(RESTRICOES, "664149190045256475687453465756856745345");
		assertThat(response.statusCode(), is(204));
	}
	
	@Test
	public void naoListarCPFNaoExistenteNoBancoDeDados() {
		Response response = rest.getCPF(RESTRICOES, "04912851086");
		assertThat(response.statusCode(), is(204));	
	}
	
	@Test
	public void listarCPFComRestricaoBase() {
		Response response = rest.getCPF(RESTRICOES, "97093236014");
		assertThat(response.statusCode(), is(200));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		assertThat(response.path("mensagem").toString(), is("O CPF 97093236014 tem problema"));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
		//assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/login/post/200.json"));
	}

}
