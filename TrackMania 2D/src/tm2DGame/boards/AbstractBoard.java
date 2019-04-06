package tm2DGame.boards;

import java.util.ArrayList;
import java.util.List;

import tm2D.Constants;
import tm2DGame.CarComponent;
import tm2DGame.Circuit;
import tm2DGame.IPlayer;
import tm2DGame.terrain.Mur;
import tm2DGame.terrain.Terrain;

public abstract class AbstractBoard implements Constants {

	protected static final int frame = 40;

	protected IPlayer voiture;

	protected Circuit circuit;

	protected List<Terrain> astarPath;

	int level = 1;

	public boolean advance() {
		voiture.accelerate(frame);

		if (voiture.isRotate()) {
			voiture.turn();
		}
		voiture.rotate(frame);
		voiture.move();
		voiture.position();
		voiture.calculateArea();
		return collision();
	}

	public boolean collision() {
		List<Double> speedCoefs = new ArrayList<>();
		List<Terrain> collisions = circuit.getCollisionTerrains(voiture);
		for (Terrain terrain : collisions) {
			if (terrain instanceof Mur) {
				voiture.initPosition();
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

	public IPlayer getVoiture() {
		return voiture;
	}

	public Circuit getCircuit() {
		return circuit;
	}

	public List<Terrain> getAstarPath() {
		return astarPath;
	}

}
