package tm2DGame.boards;

<<<<<<< HEAD
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tm2DGame.IPlayer;
import tm2DGame.terrain.Terrain;
=======
import tm2DGame.GameBoard;
>>>>>>> solution implementation with multiple track for training, fitness not relevant

public class SimulationBoard extends AbstractBoard {

	public final int numberOfTicksInAThirdSecond = 10;

	public final Set<Terrain> astarSet;

	public SimulationBoard(AbstractBoard board) {
		this.circuit = board.getCircuit();
		this.players = board.getPlayers();
		this.astarPath = board.getAstarPath();
		this.astarSet = new HashSet<>(this.astarPath);
	}

<<<<<<< HEAD
	public void setVoiture(List<IPlayer> cars) {
		this.players = cars;
	}

=======
>>>>>>> solution implementation with multiple track for training, fitness not relevant
	@Override
	public boolean advance(int frame) {
		for (int i = 0; i < numberOfTicksInAThirdSecond; i++) {
			if (super.advance(frame))
				return true;
		}
		return false;
	}

	public Set<Terrain> getAstarSet() {
		return this.astarSet;
	}

}
