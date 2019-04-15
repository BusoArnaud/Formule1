package tm2DGame.boards;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import tm2D.Constants;
import tm2DGame.Circuit;
import tm2DGame.IPlayer;
import tm2DGame.terrain.Mur;
import tm2DGame.terrain.Terrain;

public abstract class AbstractBoard implements Constants {

	protected List<IPlayer> players;

	protected Circuit circuit;

	protected List<Terrain> astarPath;

	int level = 1;

	public boolean advance(int frame) {

		for (IPlayer player : players) {
			CarComponent car = player.getCar();
			player.getAction().getCarBehavior().accept(car);
			car.accelerate(frame);

			if (car.isRotate()) {
				car.turn();
			}
			car.rotate(frame);
			car.move();
			car.position();
			car.calculateArea();
		}
		return collision();
	}

	public boolean collision() {
		for (IPlayer player : players) {
			CarComponent car = player.getCar();
			final List<Terrain> collisions = circuit.getCollisionTerrains(car);
			double[] speedCoefs = new double[collisions.size()];
			int i = 0;
			for (Terrain terrain : collisions) {
				if (terrain instanceof Mur) {
					car.initPosition();
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
				car.setSpeedDecreaseCoef(sum / speedCoefs.length);
			}
		}
		return false;
	}

	public List<CarComponent> getCars() {
		return Collections.unmodifiableList(players.stream().map(IPlayer::getCar).collect(Collectors.toList()));
	}

	public List<IPlayer> getPlayers() {
		return players;
	}

	public Circuit getCircuit() {
		return circuit;
	}

	public List<Terrain> getAstarPath() {
		return astarPath;
	}

	public void setVoiture(IPlayer car) {
		this.voiture = car;
	}

	/**
	 * @return the wallCollisionCount
	 */
	public int getWallCollisionCount() {
		return wallCollisionCount;
	}

	/**
	 * @param wallCollisionCount the wallCollisionCount to set
	 */
	public void setWallCollisionCount(int wallCollisionCount) {
		this.wallCollisionCount = wallCollisionCount;
	}
}
