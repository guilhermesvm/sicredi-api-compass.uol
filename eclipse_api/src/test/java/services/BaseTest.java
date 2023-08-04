package services;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.http.ContentType.JSON;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import io.restassured.builder.RequestSpecBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseTest {
	protected BaseRest rest;
	protected UsuariosService usuariosService;
	
	@BeforeAll
	public static void setupEnvironment() {
		baseURI = "http://localhost/";
		port = 8080;
		
		requestSpecification = new RequestSpecBuilder()
				.setContentType(JSON)
				.setAccept(JSON)
				.build();
			
		/*responseSpecification = new ResponseSpecBuilder()
				.log(LogDetail.ALL)
				.expectBody(is(not(nullValue())))
				.expectResponseTime(lessThan(3000L))
				.build();*/
	}
	
	@BeforeEach
	public void initializeService() {
		rest = new BaseRest();
		usuariosService = new UsuariosService(rest);
	}

}
