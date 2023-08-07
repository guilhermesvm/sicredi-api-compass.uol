package services;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import helper.EnvConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseTest {
	protected BaseRest rest;
	protected SimulacaoService simulacaoService;
	
	@BeforeAll
	public static void setupEnvironment() {
		baseURI = "http://localhost/";
		port = 8080;
		
		requestSpecification = new RequestSpecBuilder()
				.setBaseUri(EnvConfig.getProperty("url", ""))
				.setContentType(JSON)
				.setAccept(JSON)
				.build();
			
		responseSpecification = new ResponseSpecBuilder()
				.log(LogDetail.ALL)
				.expectBody(is(not(nullValue())))
				.expectResponseTime(lessThan(3000L))
				.build();
	}
	
	@BeforeEach
	public void initializeService() {
		rest = new BaseRest();
		simulacaoService = new SimulacaoService(rest);
	}

}
