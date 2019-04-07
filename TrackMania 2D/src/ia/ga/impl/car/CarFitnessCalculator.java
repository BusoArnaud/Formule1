package ia.ga.impl.car;

import java.util.List;
import java.util.Optional;

import ia.ga.core.FitnessCalc;
import ia.ga.core.Individual;
import tm2DGame.CarComponent;
import tm2DGame.GameBoard;
import tm2DGame.boards.SimulationBoard;
import tm2DGame.terrain.Terrain;

public class CarFitnessCalculator implements FitnessCalc<KeyEventGame> {

	private final GameBoard realBoard;

	private final SimulationBoard simulationBoard;

	private CarComponent voiture;
	
	final List<Terrain> astarPath;

	public CarFitnessCalculator(GameBoard gameBoard, CarComponent car) {
		this.voiture = car;
		this.simulationBoard = new SimulationBoard(gameBoard);
		this.realBoard = gameBoard;
		this.astarPath = gameBoard.getAstarPath();
	}

	// we could see what the kd algorithm is and how it is used;
	// it could be faster than this implementation
	@Override
	public Integer getFitness(Individual<KeyEventGame> individual) {
		// copy the original car
		voiture = new CarComponent(realBoard.getVoiture().getCar());
		simulationBoard.setVoiture(voiture);

		for (KeyEventGame play : individual.getChromosome()) {
			play.carBehavior.accept(voiture);
			if (simulationBoard.advance()) {
				return simulationBoard.getAstarPath().size() + 1;
			}
		}
		Terrain carTerrain = simulationBoard.getCircuit().getTerrain(voiture.getpX(), voiture.getpY());

		Optional<Terrain> minTerrain = simulationBoard.getCircuit().getAdjacentTiles(carTerrain, 3).stream()
				.filter(tile -> simulationBoard.getAstarSet().contains(tile))
				.min((crt, nxt) -> crt.getDistance(carTerrain) < nxt.getDistance(carTerrain) ? -1 : 1);

		return minTerrain.isPresent() ? simulationBoard.getAstarPath().indexOf(minTerrain.get()) : 0;
	}

	@Override
	public Integer getMaxFitness() {
		return realBoard.getAstarPath().size() + 1;
	}

}
