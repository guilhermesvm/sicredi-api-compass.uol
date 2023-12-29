package fluxo;
import static constants.Endpoints.RESTRICOES;
import static constants.Endpoints.SIMULACOES;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;
import datafactory.DynamicFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import model.Simulation;
import services.BaseTest;


@Tag("Fluxo")
@Epic("Fluxo de Teste da API Sicredi")
public class FluxoCompletoAPISicredi extends BaseTest{
	private static Faker faker = new Faker();
	private static Simulation simulation = new Simulation();
	private static Simulation alteracao = new Simulation();
	
	
	@Test
	@Description("Deve checar se o fluxo completo é funcional")
	public void fluxoFelizCompleto( ) {
		//Cria simulação
		simulation = DynamicFactory.generateRandomSimulation(true);
		Response criar = rest.post(SIMULACOES, simulation);
		
		String cpf = criar.path("cpf"); //Pega o CPF para alteração
		simulation.setCpf(cpf);
		
		Integer id = criar.path("id"); //Pega o ID para exclusão
		simulation.setId(id);
		
		//Listar simulação criada
		rest.getCPF(SIMULACOES, cpf);
		
		//Atualiza simulação
		alteracao.setNome(faker.name().fullName());
		alteracao.setEmail(faker.internet().emailAddress());
		alteracao.setValor(faker.random().nextInt(1000, 40000));
		alteracao.setParcelas(faker.random().nextInt(2, 48));
		alteracao.setSeguro(true); 
		rest.put(SIMULACOES, alteracao, cpf);
		
		//Listar novamente para verificar se os dados foram alterados
		rest.getCPF(SIMULACOES, cpf);
		
		//Checa se seu CPF possui restrição
		rest.getCPF(RESTRICOES, cpf);
			
		//Deleta simulação
		rest.delete(SIMULACOES, id);
	}
}
