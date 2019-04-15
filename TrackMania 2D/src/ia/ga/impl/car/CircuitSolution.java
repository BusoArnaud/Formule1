package ia.ga.impl.car;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import ia.ga.core.EvolutionAlgorithm;
import ia.ga.core.GeneticAlgorithmTemplate;
import ia.ga.impl.SimpleEvolutionAlgorithm;
import tm2DGame.boards.AbstractBoard;
import tm2DGame.boards.RealGameBoard;

public class CircuitSolution {

	public final AbstractBoard gameBoard;

	private static final int ACTION_SIZE = 3;

	private static final int POPULATION_SIZE = 60;

	private static final int MAX_GENERATION = 30;

	private static final double CROSSOVER_RATE = 0.3;

	private static final double MUTATION_RATE = 0.5;

	private static final int SELECTION_SIZE = 10;

	private final GeneticAlgorithmTemplate<KeyEventGame, BehaviorIndividual> algorithm;
	
	private CarFitnessCalculator fitnessCalc; 

	public CircuitSolution(RealGameBoard gameBoard, int carIndex, int frame) {
		this.gameBoard = gameBoard;
		fitnessCalc = new CarFitnessCalculator(gameBoard, carIndex, frame);

		EvolutionAlgorithm<KeyEventGame, BehaviorIndividual> evolutionAlgo = new SimpleEvolutionAlgorithm<>(
				CROSSOVER_RATE, MUTATION_RATE, SELECTION_SIZE, fitnessCalc, BehaviorIndividual.class);
		algorithm = new GeneticAlgorithmTemplate<>(fitnessCalc, evolutionAlgo, BehaviorIndividual.class, MAX_GENERATION,
				POPULATION_SIZE);
	}

	public List<KeyEventGame> call()
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return algorithm.getSolution().subList(0, ACTION_SIZE);
	}
	
	public void setBoard(AbstractBoard board) {
		fitnessCalc.setBoard(board);
	}

}
