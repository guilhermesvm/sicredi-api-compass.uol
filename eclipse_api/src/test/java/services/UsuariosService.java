package services;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuariosService {
	private BaseRest rest;
	
	public UsuariosService(BaseRest rest) {
		this.rest = rest;
	}

}
