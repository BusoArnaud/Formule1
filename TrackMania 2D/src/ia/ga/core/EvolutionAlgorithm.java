package ia.ga.core;

/**
 * The Class EvolutionAlgorithm is an abstract class that define the evolution
 * algorithm of a population.
 *
 * @param <T> the generic type
 * @param <K> the key type
 */
public abstract class EvolutionAlgorithm<T, K extends Individual<T>> {

	/* evolution parameters */
	protected final double crossoverRate;
	protected final double mutationRate;
	protected final FitnessCalc<T> fitnessCalc;

	public EvolutionAlgorithm(double crossoverRate, double mutationRate, FitnessCalc<T> fitnessCalc) {
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
		this.fitnessCalc = fitnessCalc;
	}

	/* Public methods */

	// Evolve a population
	public Population<T, K> evolvePopulation(Population<T, K> pop) {

		Population<T, K> newPopulation = new Population<>(pop.size(), fitnessCalc);

		newPopulation.add(pop.getFittest());
		// Crossover population
		for (int i = 1; i < pop.size(); i++) {
			// ooo a baby how cute!!!!
			K newIndividual = crossover(selection(pop), selection(pop));
			// oh no i dropped it in the radioactive pool
			newIndividual.mutate(mutationRate);
			// well that will be our next generation with all the radioactive babies
			newPopulation.add(newIndividual);
		}

		return newPopulation;
	}

//  _,-----,____g===;,
//  <'.._____,-------g  ;
//                     \   \,
//                       q   q,
//                        q    q,
//                       [='     q
//                         `;  O  p
//                           k  O  p -{0
//                            l  O  p -o
//                            ,i     p
//                             P  O   |
//                           q:|   O| |BD
//                              [   | P b
//                              |   |  |____________
//                            [ |   |  |\         /
//                            | '   |  P :       ;
//                            | [   0   Q:  ( )  ;
//                             [ P  ( )  |;  ( ) ;
//                             :Q  ( )  V        p
//                              \   [           ;
//                               \',     O    /
//                                 ' ; _ . 
	protected abstract K crossover(K indiv1, K indiv2);

	// Hey beautiful
	protected abstract K selection(Population<T, K> pop);

}