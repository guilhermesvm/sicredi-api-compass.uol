package datafactory;

import java.util.Locale;
import com.github.javafaker.Faker;
import model.Simulation;

public class DynamicFactory {

	private static Faker faker = new Faker(Locale.ENGLISH);
	private static Simulation simulation = new Simulation();

	public static Simulation generateRandomSimulation(boolean possuiSeguro) {
		simulation.setNome(faker.name().fullName());
		simulation.setCpf(faker.number().digits(11).toString());
		simulation.setEmail(faker.internet().emailAddress());
		simulation.setValor(faker.random().nextInt(1000, 40000));
		simulation.setParcelas(faker.random().nextInt(2, 48));
		simulation.setSeguro(possuiSeguro);
		return simulation;
	}
}
