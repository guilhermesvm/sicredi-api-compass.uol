package test;

import static constants.Endpoints.RESTRICOES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static datafactory.TestVariables_Rest.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Epic;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import services.BaseTest;

@Tag("Restrições")
@Tag("GET")
@Epic("Testes GET do Endpoint /Restricoes")
public class GETRestricoesTest extends BaseTest{
 
	@Test
	public void listarCPFSemRestricao() {
		Response response = rest.getCPF(RESTRICOES, CPFSemRestricao);
		assertThat(response.statusCode(), is(204));	
	}
	
	@Test
	public void naoListarCPFInvalidoComLetra() {
		Response response = rest.getCPF(RESTRICOES, CPFComLetra);
		assertThat(response.statusCode(), is(404));
	}
	
	@Test
	public void naoListarCPFInvalidoComCaracteresEspeciais() {
		Response response = rest.getCPF(RESTRICOES, CPFComCaracterEspecial);
		assertThat(response.statusCode(), is(404));
	}
	
	@Test
	public void naoListarCPFInvalidoComCaracterEmBranco() {
		Response response = rest.getCPF(RESTRICOES, CPFComEspacoEmBranco);
		assertThat(response.statusCode(), is(404));
	}
	
	@Test
	public void naoListarCPFForaDosPadroesMenorQue11Digitos() {
		Response response = rest.getCPF(RESTRICOES, CPFMenorQueOnze);
		assertThat(response.statusCode(), is(404));
	}
	@Test
	public void naoListarCPFForaDosPadroesMaiorQue11Digitos() {
		Response response = rest.getCPF(RESTRICOES, CPFMaiorQueOnze);
		assertThat(response.statusCode(), is(404));
	}
	
	@Test
	public void naoListarCPFNaoExistenteNoBancoDeDados() {
		Response response = rest.getCPF(RESTRICOES, CPFValidoMasNaoExistenteNoBanco);
		assertThat(response.statusCode(), is(404));	
	}
	
	@Test
	public void naoListarCPFNaFormaPadraoMasInvalido() {
		Response response = rest.getCPF(RESTRICOES, CPFDentroDoPadroesMasInvalido);
		assertThat(response.statusCode(), is(404));	
	}
	
	@Test
	public void naoListarCPFVazio() {
		Response response = rest.getCPF(RESTRICOES, CPFVazio);
		assertThat(response.statusCode(), is(404));	
	}
	
	@Test
	public void listarCPFComRestricao() {
		Response response = rest.getCPF(RESTRICOES, CPFComRestricao);
		assertThat(response.statusCode(), is(200));
		assertThat(response.asString(), containsString("mensagem"));
		assertThat(response.path("mensagem"), is(not(nullValue())));
		assertThat(response.path("mensagem").toString(), is("O CPF 97093236014 tem problema"));
		assertThat(response.path("mensagem"), is(instanceOf(String.class)));
		assertThat(response.asString(), JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/restricoes/get_{cpf}/200.json"));
	}
}
