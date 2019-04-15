package ia.ga.impl.CarSubject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import ia.ga.core.EvolutionAlgorithm;
import ia.ga.core.Population;
import ia.subject.GeneComplex;
import tm2DGame.boards.LongSimulationBoard;

public class Solution {

	public final LongSimulationBoard gameBoard;

	private Population<GeneComplex, BehaviorSubject> pop = null;

	private static final double CROSSOVER_RATE = 0.5;

	private static final double MUTATION_RATE = 0.1;

	private static final int SELECTION_SIZE = 10;

	

	public Solution(LongSimulationBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public List<GeneComplex> call()
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		System.out.println("try to create fitnessCalc");
		SubjectFitnessCalc fitnessCalc = new SubjectFitnessCalc(gameBoard);

		System.out.println("try to create evolutionAlgo");
		EvolutionAlgorithm<GeneComplex, BehaviorSubject> evolutionAlgo = new SubjectEvolutionAlgorithm<>(CROSSOVER_RATE, MUTATION_RATE, SELECTION_SIZE, fitnessCalc, BehaviorSubject.class);

		SubjectAlgorithmTemplate algorithm = new SubjectAlgorithmTemplate(fitnessCalc, evolutionAlgo, BehaviorSubject.class, 100, 100, pop);
		
		System.out.println("return solutions");
		List<GeneComplex> solutions = algorithm.getSolution();
		this.pop = algorithm.getPop();
		return solutions;
	}
}
