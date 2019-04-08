package ia.ga.impl.CarSubject;

import java.lang.reflect.InvocationTargetException;

import ia.ga.core.EvolutionAlgorithm;
import ia.ga.core.FitnessCalc;
import ia.ga.core.GeneticAlgorithmTemplate;
import ia.ga.core.Population;
import ia.subject.GeneComplex;

/**
 * This class is a simple implementation of the evolution Algorithm that uses a
 * tournamenet selection.
 *
 * @param <T> the generic type
 * @param <K> the key type
 */
public class SubjectAlgorithmTemplate extends GeneticAlgorithmTemplate<GeneComplex, BehaviorSubject> {

	Population<GeneComplex, BehaviorSubject> pop = null;

	public SubjectAlgorithmTemplate(FitnessCalc<GeneComplex> fitnessCalc, EvolutionAlgorithm<GeneComplex,BehaviorSubject> evolutionAlgorithm,
	Class<BehaviorSubject> clazz,
	int maxNumberOfGeneration,
	int populationSize, Population<GeneComplex, BehaviorSubject> pop) {
		super(fitnessCalc, evolutionAlgorithm, clazz, maxNumberOfGeneration, populationSize);
		this.pop = pop;
	}

	@Override
	protected Population<GeneComplex, BehaviorSubject> initializePopulation(int size, Class<BehaviorSubject> clazz)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
				
		if(pop == null){
			pop= new Population<>(size, fitnessCalc);
			
			for (int i = 0; i < this.populationSize; i++) {
				BehaviorSubject individual = clazz.getDeclaredConstructor().newInstance();
				individual.randomizeIndividual();
				pop.add(individual);
			}
		}else{
			this.evolutionAlgorithm.evolvePopulation(pop);
		}
		return pop;
	}

	/**
	 * @return the pop
	 */
	public Population<GeneComplex, BehaviorSubject> getPop() {
		return pop;
	}

}
