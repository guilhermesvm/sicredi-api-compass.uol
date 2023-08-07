package services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimulacaoService {
	private BaseRest rest;
	
	public SimulacaoService(BaseRest rest) {
		this.rest = rest;
	}

}
