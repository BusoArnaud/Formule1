package ia.ga.impl.car;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import ia.ga.core.FitnessCalc;
import ia.ga.core.Individual;
import tm2DGame.CarComponent;
import tm2DGame.GameBoard;
import tm2DGame.boards.SimulationBoard;
import tm2DGame.terrain.Terrain;

public class CarFitnessCalculator implements FitnessCalc<KeyEventGame> {

	private final GameBoard realBoard;

	private final SimulationBoard simulationBoard;

	public CarFitnessCalculator(GameBoard gameBoard) {
		this.simulationBoard = new SimulationBoard(gameBoard);
		this.realBoard = gameBoard;
	}

	// we could see what the kd algorithm is and how it is used;
	// it could be faster than this implementation
	@Override
	public Integer getFitness(Individual<KeyEventGame> individual) {
		final List<Terrain> astarPath = simulationBoard.getAstarPath();
		final CarComponent voiture = new CarComponent(realBoard.getVoiture());
		simulationBoard.setVoiture(voiture);
		// individual.getChromosome()[ACCRIGHT, ACCRIGHT, UP, ACCLEFT, NOTHING, UP]
//    List<KeyEventGame> upKeys = Arrays.asList(KeyEventGame.RIGHT,KeyEventGame.ACCLEFT,KeyEventGame.ACCLEFT,KeyEventGame.DOWNLEFT,KeyEventGame.DOWN);
		for (KeyEventGame play : individual.getChromosome()) {
			play.carBehavior.accept(voiture);
			if (simulationBoard.advance()) {
				return astarPath.size() + 1;
			}
		}
		Terrain carTerrain = simulationBoard.getCircuit().getTerrain(voiture.getpX(), voiture.getpY());
		double min = astarPath.get(0).getDistance(carTerrain);
		int index = 0;
		for (Terrain terrain : simulationBoard.getCircuit().getAdjacentTiles(carTerrain, 2)) {
			int temp;
			if ((temp = astarPath.indexOf(terrain)) > -1) {
				double distance = astarPath.get(temp).getDistance(carTerrain);
				if (distance < min) {
					min = distance;
					index = temp;
				}
			}
		}
		return index;
	}

	@Override
	public Integer getMaxFitness() {
		return realBoard.getAstarPath().size() + 1;
	}

}
