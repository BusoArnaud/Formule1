package tm2DGame.boards;

import java.util.ArrayList;
import java.util.List;

import tm2DGame.GameBoard;
import tm2DGame.terrain.Mur;
import tm2DGame.terrain.Terrain;

public class LongSimulationBoard extends AbstractBoard {


	public LongSimulationBoard(GameBoard board) {
		this.circuit = board.getCircuit();
		this.voiture = board.getVoiture();
		this.astarPath = board.getAstarPath();
	}
	@Override
	public boolean collision() {
		List<Double> speedCoefs = new ArrayList<>();
		List<Terrain> collisions = circuit.getCollisionTerrains(voiture);
		for (Terrain terrain : collisions) {
			if (terrain instanceof Mur) {
				voiture.initPosition();
				wallCollisionCount++;
				return false;
			} else if (terrain.isEnd()) {
				return true;
			} else {
				speedCoefs.add(terrain.getSpeedDecreaseCoef());
			}
		}
		if (!collisions.isEmpty()) {
			double speedCoef = speedCoefs.stream().mapToDouble(Double::doubleValue).sum() / speedCoefs.size();
			voiture.setSpeedDecreaseCoef(speedCoef);
		}
		return false;
	}
	
	@Override
	public boolean advance() {
		if (super.advance())
			return true;
	
		return false;
	}

}
