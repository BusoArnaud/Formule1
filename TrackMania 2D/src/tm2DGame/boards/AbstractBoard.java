package tm2DGame.boards;

import java.util.List;

import tm2D.Constants;
import tm2DGame.CarComponent;
import tm2DGame.Circuit;
import tm2DGame.IPlayer;
import tm2DGame.terrain.Mur;
import tm2DGame.terrain.Terrain;

public abstract class AbstractBoard implements Constants {

	protected static final int frame = 40;

	protected CarComponent voiture;

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
		final List<Terrain> collisions = circuit.getCollisionTerrains(voiture);
		double[] speedCoefs = new double[collisions.size()];
		int i = 0;
		for (Terrain terrain : collisions) {
			if (terrain instanceof Mur) {
				voiture.initPosition();
			} else if (terrain.isEnd()) {
				return true;
			} else {
				speedCoefs[i] = terrain.getSpeedDecreaseCoef();
				i++;
			}
		}
		if (!collisions.isEmpty()) {
			double sum = 0;
			for (int j = 0; j < speedCoefs.length; j++) {
				sum += speedCoefs[j];
			}
			voiture.setSpeedDecreaseCoef(sum / speedCoefs.length);
		}
		return false;
	}

	public CarComponent getVoiture() {
		return voiture;
	}

	public Circuit getCircuit() {
		return circuit;
	}

	public List<Terrain> getAstarPath() {
		return astarPath;
	}

}
