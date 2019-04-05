package ia.ga.core;

public interface FitnessCalc<T> {

	/**
	 * Gets the fitness. of an individual. The fitness is a score upon which the
	 * algorithm will base the score of an individual. The more the fitness, the
	 * more probability you have to be selected in the evolution process
	 *
	 * @param individual the individual
	 * @return the fitness
	 */
	Integer getFitness(Individual<T> individual);

	/**
	 * Gets the max fitness which is a solution to the problem.
	 *
	 * @return the max fitness
	 */
	Integer getMaxFitness();

}