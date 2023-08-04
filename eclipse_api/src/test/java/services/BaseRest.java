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
	
	public Response post(String endpoint, Object user) {
		return
		given()
			.body(user)
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
	
	public Response put(String endpoint, Object alteracao, String id) {
		return
		given()
			.body(alteracao)
			.pathParam("id", id)
		.when()
			.put(endpoint + "/" + "{id}")
		.then()
			.log().all()
			.body(is(not(nullValue())))
			.time(lessThan(3000L))
		.extract()
			.response()
		;
	}
		
	public Response delete(String endpoint, String id) {
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


