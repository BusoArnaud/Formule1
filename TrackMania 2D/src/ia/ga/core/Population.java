package ia.ga.core;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * The population keeps all the individuals and their sorted fitness.
 *
 * @param <T> the generic type
 * @param <K> the key type
 */
public class Population<T, K extends Individual<T>> {

	private List<K> individuals;

	private int populationSize;

	private final FitnessCalc<T> fitnessCalc;

	private boolean fitnessCalculated = false;
	
	private K fittest;

	/*
	 * Constructors
	 */
	// Create a population
	public Population(int populationSize, FitnessCalc<T> fitnessCalc) {
		individuals = new ArrayList<>(populationSize);
		this.populationSize = populationSize;
		this.fitnessCalc = fitnessCalc;
	}

	public final void initialize(Class<K> clazz)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// Loop and create individuals
		for (int i = 0; i < populationSize; i++) {
			K individual = clazz.getDeclaredConstructor().newInstance();
			individual.randomizeIndividual();
			add(individual);
		}
	}

	public void add(K individual) {
		individuals.add(individual);
	}

	public final int size() {
		return individuals.size();
	}

	public K getFittest() {
		if (!fitnessCalculated) {
			fittest = individuals.stream()
					.min((crnt, next) -> next.getFitness(fitnessCalc) - crnt.getFitness(fitnessCalc)).get();
			fitnessCalculated = true;
		}

		return fittest;
	}

	public K getElement(int index) {
		return individuals.get(index);
	}

}