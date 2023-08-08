package services;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRest {
	
	public Response get(String endpoint) {
		return
		given()
		.when()
			.get(endpoint)
		.then()
			.log().all()
			.body(is(not(nullValue())))
			.time(lessThan(3000L))
		.extract()
			.response()
				;
		}
	
	public Response getCPF(String endpoint, String cpf) {
		return
		given()
			.pathParam("cpf", cpf)
		.when()
			.get(endpoint + "/" + "{cpf}")
		.then()
			.log().all()
			.body(is(not(nullValue())))
			.time(lessThan(3000L))
		.extract()
			.response()
		;		
	}
	
	public Response post(String endpoint, Object sim) {
		return
		given()
			.body(sim)
		.when()
			.post(endpoint)
		.then()
			.log().all()
			.body(is(not(nullValue())))
			.time(lessThan(3000L))
		.extract()
			.response()
				;
		}
	
	public Response put(String endpoint, Object alteracao, String cpf) {
		return
		given()
			.body(alteracao)
			.pathParam("cpf", cpf)
		.when()
			.put(endpoint + "/" + "{cpf}")
		.then()
			.log().all()
			.body(is(not(nullValue())))
			.time(lessThan(3000L))
		.extract()
			.response()
		;
	}
		
	public Response delete(String endpoint, Integer id) {
		return
		given()
			.pathParam("id", id)
		.when()
			.delete(endpoint + "/" + "{id}")
		.then()
			.log().all()
			.body(is(not(nullValue())))
			.time(lessThan(3000L))
		.extract()
			.response();
		}
	
	
	}


