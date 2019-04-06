package ia.ga.impl.CarSubject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import ia.ga.core.EvolutionAlgorithm;
import ia.ga.core.GeneticAlgorithmTemplate;
import ia.ga.impl.SimpleEvolutionAlgorithm;
import ia.subject.GeneComplex;
import tm2DGame.GameBoard;

public class Solution {

	public final GameBoard gameBoard;

	private static final double CROSSOVER_RATE = 0.3;

	private static final double MUTATION_RATE = 0.1;

	private static final int SELECTION_SIZE = 10;

	public Solution(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}

	public List<GeneComplex> call()
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		SubjectFitnessCalc fitnessCalc = new SubjectFitnessCalc(gameBoard);

		EvolutionAlgorithm<GeneComplex, BehaviorSubject> evolutionAlgo = new SimpleEvolutionAlgorithm<>(CROSSOVER_RATE,
				MUTATION_RATE, SELECTION_SIZE, fitnessCalc, BehaviorSubject.class);

		GeneticAlgorithmTemplate<GeneComplex, BehaviorSubject> algorithm = new GeneticAlgorithmTemplate<>(fitnessCalc,
				evolutionAlgo, BehaviorSubject.class, 50, 30);

		return algorithm.getSolution();
	}
}
