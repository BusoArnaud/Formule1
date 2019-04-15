package tm2DGame.boards;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import pathfinding.Astar;
import tm2DGame.CarComponent;
import tm2DGame.Circuit;
import tm2DGame.IaCarPlayer;
import tm2DGame.terrain.Mur;
import tm2DGame.terrain.Terrain;

public class LongSimulationBoard extends AbstractBoard {

	String trackName = "Track";

	public LongSimulationBoard(AbstractBoard board) {
		this.circuit = board.getCircuit();
		this.players = board.getPlayers();
		this.astarPath = board.getAstarPath();
	}
	@Override
	public boolean collision() {
		List<Double> speedCoefs = new ArrayList<>();
		List<Terrain> collisions = circuit.getCollisionTerrains(players.get(0).getCar());
		for (Terrain terrain : collisions) {
			if (terrain instanceof Mur) {
				players.get(0).getCar().initPosition();
				return false;
			} else if (terrain.isEnd()) {
				return true;
			} else {
				speedCoefs.add(terrain.getSpeedDecreaseCoef());
			}
		}
		if (!collisions.isEmpty()) {
			double speedCoef = speedCoefs.stream().mapToDouble(Double::doubleValue).sum() / speedCoefs.size();
			players.get(0).getCar().setSpeedDecreaseCoef(speedCoef);
		}
		return false;
	}
	

	public void loadTrack(String trackName) {
		getCars().forEach(CarComponent::initPosition);
		try {
			FileReader fr = new FileReader(RELATIVE_PATH_TRACKS + trackName);

			circuit = new Circuit(fr, 5, 55);

			astarPath = new Astar(circuit).call();

			getPlayers().stream().filter(player -> player instanceof IaCarPlayer)
					.forEach(player -> ((IaCarPlayer) player).init(this, 40));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public boolean advance(int frame) {
		if (super.advance(frame))
			return true;
	
		return false;
	}

}
