package ia.ga.core;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class GeneticAlgorithmTemplate. 
 *
 * @param <T> the generic type
 * @param <K> the implementation of Individual<T>
 */
//T here is the Gene, K is the individual that has the gene
public class GeneticAlgorithmTemplate<T, K extends Individual<T>> {

	private FitnessCalc<T> fitnessCalc;

	private EvolutionAlgorithm<T, K> evolutionAlgorithm;

	private final int maxNumberOfGeneration;

	private final int populationSize;

	private Class<K> clazz;

	public GeneticAlgorithmTemplate(FitnessCalc<T> fitnessCalc, EvolutionAlgorithm<T, K> evolutionAlgorithm,
			Class<K> clazz, int maxNumberOfGeneration, int populationSize) {
		this.fitnessCalc = fitnessCalc;
		this.evolutionAlgorithm = evolutionAlgorithm;
		this.clazz = clazz;
		this.maxNumberOfGeneration = maxNumberOfGeneration;
		this.populationSize = populationSize;
	}

	/**
	 * Gets the solution chromosome.
	 *
	 * @return the solution
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 */
	public List<T> getSolution() throws InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		Population<T, K> pop = initializePopulation(populationSize, clazz);
		// Evolve our population until we reach an optimum solution
		int generationCount = 0;

		K fittest = pop.getFittest();
		while (fittest.getFitness() < fitnessCalc.getMaxFitness() && generationCount < maxNumberOfGeneration) {
			generationCount++;
			pop = evolutionAlgorithm.evolvePopulation(pop);
			fittest = pop.getFittest();
		}
		return new LinkedList<>(fittest.getChromosome());
	}

	/**
	 * Initialize the population with a size and the implemented
	 *  randomizeIndividual function of individual
	 *
	 * @param size the size
	 * @param clazz the clazz
	 * @return the population
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 */
	protected Population<T, K> initializePopulation(int size, Class<K> clazz)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Population<T, K> pop = new Population<>(size, fitnessCalc);
		pop.initialize(clazz);
		return pop;
	}

}
