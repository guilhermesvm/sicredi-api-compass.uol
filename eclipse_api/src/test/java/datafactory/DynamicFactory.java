package datafactory;


import java.util.Locale;
import com.github.javafaker.Faker;
import model.Simulation;

public class DynamicFactory {
	private static Faker faker = new Faker(Locale.ENGLISH);
	private static Simulation simulation = new Simulation();

	public static Simulation generateRandomSimulation(boolean possuiSeguro) {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString()); //Ajuda do Matheus
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(possuiSeguro);
		return simulation;
	}
	
	public static Simulation generateBlankSimulation() {
		simulation.setNome("  ");
		simulation.setCpf("  ");
		simulation.setEmail("  ");
		simulation.setValorr("  ");
		simulation.setParcelass("  ");
		simulation.setSeguroo("  ");
		return simulation;
	}
	
	public static Simulation generateHalfBlankSimulation(boolean possuiSeguro) {
		simulation.setNome("  ");
		simulation.setCpf("  ");
		simulation.setEmail("  ");
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(possuiSeguro);
		return simulation;
	}
	
	public static Simulation generateEmptySimulation() {
		simulation.setNome("");
		simulation.setCpf("");
		simulation.setEmail("");
		simulation.setValorr("");
		simulation.setParcelass("");
		simulation.setSeguroo("");
		return simulation;
	}
	
	public static Simulation generateHalfEmptySimulation(boolean possuiSeguro) {
		simulation.setNome("");
		simulation.setCpf("");
		simulation.setEmail("");
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(possuiSeguro);
		return simulation;
	}
}
