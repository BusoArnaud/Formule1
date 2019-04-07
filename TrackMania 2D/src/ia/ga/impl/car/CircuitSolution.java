package ia.ga.impl.car;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import ia.ga.core.EvolutionAlgorithm;
import ia.ga.core.GeneticAlgorithmTemplate;
import ia.ga.impl.SimpleEvolutionAlgorithm;
import tm2DGame.CarComponent;
import tm2DGame.GameBoard;

public class CircuitSolution {

	private static final int ACTION_SIZE = 3;

  private static final int POPULATION_SIZE = 100;

  private static final int MAX_GENERATION = 30;

  public final GameBoard gameBoard;

	private CarComponent car;

	private static final double CROSSOVER_RATE = 0.3;

	private static final double MUTATION_RATE = 0.1;

	private static final int SELECTION_SIZE = 10;
	
	private final GeneticAlgorithmTemplate<KeyEventGame, BehaviorIndividual> algorithm;

	public CircuitSolution(GameBoard gameBoard, CarComponent car) {
		this.car = new CarComponent(car);
		this.gameBoard = gameBoard;
		CarFitnessCalculator fitnessCalc = new CarFitnessCalculator(gameBoard, car);
		EvolutionAlgorithm<KeyEventGame, BehaviorIndividual> evolutionAlgo = new SimpleEvolutionAlgorithm<>(CROSSOVER_RATE,
        MUTATION_RATE, SELECTION_SIZE, fitnessCalc, BehaviorIndividual.class);
		algorithm = new GeneticAlgorithmTemplate<>(fitnessCalc, evolutionAlgo , BehaviorIndividual.class, MAX_GENERATION,
				POPULATION_SIZE);
	}

	public List<KeyEventGame> call()
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

	  return algorithm.getSolution().subList(0, ACTION_SIZE);
	}

}
