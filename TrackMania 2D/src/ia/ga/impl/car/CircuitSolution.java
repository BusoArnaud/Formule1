package ia.ga.impl.car;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import ia.ga.core.EvolutionAlgorithm;
import ia.ga.core.GeneticAlgorithmTemplate;
import ia.ga.impl.SimpleEvolutionAlgorithm;
import tm2DGame.CarComponent;
import tm2DGame.GameBoard;
import tm2DGame.boards.SimulationBoard;

public class CircuitSolution {

	public final GameBoard gameBoard;

	private CarComponent car;

	private static final double CROSSOVER_RATE = 0.3;

	private static final double MUTATION_RATE = 0.1;

	private static final int SELECTION_SIZE = 10;

	public CircuitSolution(GameBoard gameBoard, CarComponent car) {
		this.car = new CarComponent(car);
		this.gameBoard = gameBoard;
	}

	public List<KeyEventGame> call()
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		CarFitnessCalculator fitnessCalc = new CarFitnessCalculator(gameBoard, car);

		EvolutionAlgorithm<KeyEventGame, BehaviorIndividual> evolutionAlgo = new SimpleEvolutionAlgorithm<>(
				CROSSOVER_RATE, MUTATION_RATE, SELECTION_SIZE, fitnessCalc, BehaviorIndividual.class);

		GeneticAlgorithmTemplate<KeyEventGame, BehaviorIndividual> algorithm = new GeneticAlgorithmTemplate<>(
				fitnessCalc, evolutionAlgo, BehaviorIndividual.class, 50, 30);

		return algorithm.getSolution();
	}

}
