package ia.ga.impl.CarSubject;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

import ia.ga.core.EvolutionAlgorithm;
import ia.ga.core.FitnessCalc;
import ia.ga.core.Individual;
import ia.ga.core.Population;
import ia.subject.GeneComplex;

/**
 * This class is a simple implementation of the evolution Algorithm that uses a
 * tournamenet selection.
 *
 * @param <T> the generic type
 * @param <K> the key type
 */
public class SubjectEvolutionAlgorithm<T, K extends Individual<GeneComplex>> extends EvolutionAlgorithm<GeneComplex, BehaviorSubject> {

	Logger log = Logger.getLogger(SubjectEvolutionAlgorithm.class.getName());

	private static Random rand = new Random();
	private final Class<BehaviorSubject> type;


	private final int selectionSize;

	public SubjectEvolutionAlgorithm(double crossoverRate, double mutationRate, int selectionSize,
			FitnessCalc<GeneComplex> fitnessCalc, Class<BehaviorSubject> type) {
		super(crossoverRate, mutationRate, fitnessCalc);
		this.type = type;
		this.selectionSize = selectionSize;
	}

	@Override
	public Population<GeneComplex, BehaviorSubject> evolvePopulation(Population<GeneComplex, BehaviorSubject> pop) {

		Population<GeneComplex, BehaviorSubject> newPopulation = new Population<>(pop.size(), fitnessCalc);

		newPopulation.add(pop.getFittest());
		// Crossover population
		for (int i = 1; i < pop.size(); i++) {
			// ooo a baby how cute!!!!
			BehaviorSubject newIndividual = crossover(selection(pop), selection(pop));
			// oh no i dropped it in the radioactive pool
			newIndividual.mutate(mutationRate);
			// well that will be our next generation with all the radioactive babies
			newPopulation.add(newIndividual);
		}

		return newPopulation;
	}

	@Override
	protected BehaviorSubject crossover(BehaviorSubject indiv1, BehaviorSubject indiv2) {

		try {
			BehaviorSubject newSol = type.getConstructor().newInstance();
			// Preparing the baby
			for (int i = 0; i < indiv1.size(); i++) {
				// DNA mixing
				if (rand.nextDouble() <= crossoverRate) {
					newSol.add(indiv1.getGene(i));
				} else {
					newSol.add(indiv2.getGene(i));
				}
			}
			return newSol;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			log.severe(Arrays.toString(e.getStackTrace()));
		}
		return null;
	}

	@Override
	protected BehaviorSubject selection(Population<GeneComplex, BehaviorSubject> pop) {

		Population<GeneComplex, BehaviorSubject> tournament = new Population<>(pop.size(), fitnessCalc);

		// For each place in the tournament get a random individual
		for (int i = 0; i < selectionSize; i++) {
			tournament.add(pop.getElement(rand.nextInt(pop.size())));
		}

		// Get the fittest
		return tournament.getFittest();
	}

}
