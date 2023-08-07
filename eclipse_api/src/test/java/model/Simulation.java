package model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Simulation {
	private String nome;
	private String cpf;
	private String email;
	
	@JsonFormat(shape = Shape.STRING)
	private Integer valor;
	
	@JsonFormat(shape = Shape.STRING)
	private Integer parcelas;
	
	@JsonFormat(shape = Shape.STRING)
	private boolean seguro;
	
	@JsonProperty(value = "id", access = Access.WRITE_ONLY )
	private Integer id; //É lido apenas na desserialização

}
