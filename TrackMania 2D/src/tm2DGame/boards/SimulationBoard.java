package tm2DGame.boards;

import java.util.HashSet;
import java.util.Set;

import tm2DGame.CarComponent;
import tm2DGame.GameBoard;
import tm2DGame.terrain.Terrain;

public class SimulationBoard extends AbstractBoard {

	public final int numberOfTicksInAThirdSecond = 10;

	public final Set<Terrain> astarSet;
	
	public SimulationBoard(GameBoard board) {
		this.circuit = board.getCircuit();
		this.voiture = board.getVoiture().getCar();
		this.astarPath = board.getAstarPath();
		this.astarSet = new HashSet<>(this.astarPath);
	}

	public void setVoiture(CarComponent voiture) {
		this.voiture = voiture;
	}

	@Override
	public boolean advance() {
		for (int i = 0; i < numberOfTicksInAThirdSecond; i++) {
			if (super.advance())
				return true;
		}
		return false;
	}
	
	public Set<Terrain> getAstarSet(){
		return this.astarSet;
	}

}
