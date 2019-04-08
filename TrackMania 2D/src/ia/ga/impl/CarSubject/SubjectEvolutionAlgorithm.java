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
		newPopulation.add(pop.getFittest());

		return newPopulation;
	}

	@Override
	protected BehaviorSubject crossover(BehaviorSubject parent1, BehaviorSubject parent2) {

		try {
			BehaviorSubject newSol = type.getConstructor().newInstance();
			newSol.add(new GeneComplex());
			 
			if (rand.nextDouble() >= crossoverRate) {
				newSol.getGene(0).setDistanceEval(parent1.getGene(0).getDistanceEval());
			} else {
				newSol.getGene(0).setDistanceEval(parent2.getGene(0).getDistanceEval());
			}

			parent1.getGene(0).getChromosomes().entrySet().forEach(entry -> {
				if (rand.nextDouble() >= crossoverRate) {
					newSol.getGene(0).getChromosomes().put(entry.getKey(), entry.getValue());
				}
			});
			parent2.getGene(0).getChromosomes().entrySet().forEach(entry -> {
				if (rand.nextDouble() < crossoverRate) {
					newSol.getGene(0).getChromosomes().put(entry.getKey(), entry.getValue());
				}
			});

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
