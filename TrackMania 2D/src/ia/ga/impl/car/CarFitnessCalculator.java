package ia.ga.impl.car;

import java.util.Collections;
import java.util.List;
import ia.ga.core.FitnessCalc;
import ia.ga.core.Individual;
import tm2DGame.CarComponent;
import tm2DGame.SimulationPlayer;
import tm2DGame.boards.AbstractBoard;
import tm2DGame.boards.SimulationBoard;
import tm2DGame.terrain.Terrain;

public class CarFitnessCalculator implements FitnessCalc<KeyEventGame> {

	private AbstractBoard realBoard;

	private final SimulationBoard simulationBoard;

	private final int frame;

	private int carIndex;
	
	private final int radius = 2;

	final List<Terrain> astarPath;

	public CarFitnessCalculator(AbstractBoard gameBoard, int carIndex, int frame) {
		this.frame = frame;
		this.carIndex = carIndex;
		this.simulationBoard = new SimulationBoard(gameBoard);
		this.realBoard = gameBoard;
		this.astarPath = gameBoard.getAstarPath();
	}

	// we could see what the kd algorithm is and how it is used;
	// it could be faster than this implementation
	@Override
	public Integer getFitness(Individual<KeyEventGame> individual) {
		// copy the original car
		CarComponent car = new CarComponent(realBoard.getCars().get(carIndex));
		final SimulationPlayer player = new SimulationPlayer(car);
		simulationBoard.setVoiture(Collections.singletonList(player));

		for (KeyEventGame play : individual.getChromosome()) {
			player.setAction(play);
			if (simulationBoard.advance(frame)) {
				return simulationBoard.getAstarPath().size() + 1;
			}
		}
		Terrain carTerrain = simulationBoard.getCircuit().getTerrain(car.getpX(), car.getpY());
		
		Integer min = radius + 1;
		Terrain minTerrain = null;
		for(Terrain terrain : simulationBoard.getCircuit().getAdjacentTiles(carTerrain, radius)) {
			if(simulationBoard.getAstarSet().contains(terrain)) {
				int dist = simulationBoard.getCircuit().getTileDistance(carTerrain, terrain);
				if(dist>0 && dist<min) {
					min = dist;
					minTerrain = terrain;
				}
			}
		}
		return minTerrain != null ? simulationBoard.getAstarPath().indexOf(minTerrain) : 0;
//		
//		Optional<Terrain> minTerrain = simulationBoard.getCircuit().getAdjacentTiles(carTerrain, 3).stream()
//				.filter(tile -> simulationBoard.getAstarSet().contains(tile))
//				.min((crt, nxt) -> crt.getDistance(carTerrain) < nxt.getDistance(carTerrain) ? -1 : 1);
//
//		return minTerrain.isPresent() ? simulationBoard.getAstarPath().indexOf(minTerrain.get()) : 0;
	}

	@Override
	public Integer getMaxFitness() {
		return realBoard.getAstarPath().size() + 1;
	}
	
	public void setBoard(AbstractBoard board) {
		this.realBoard =board;
	}

}
