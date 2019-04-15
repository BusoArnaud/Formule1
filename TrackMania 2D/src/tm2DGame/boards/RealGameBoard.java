package tm2DGame.boards;

import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import ia.ga.impl.car.CircuitSolution;
import ia.ga.impl.car.KeyEventGame;
import pathfinding.Astar;
import tm2DGame.CarComponent;
import tm2DGame.Circuit;
import tm2DGame.IPlayer;
import tm2DGame.IaCarPlayer;
import tm2DGame.PlayerCarComponent;

public class RealGameBoard extends AbstractBoard {

	IPlayer voiture1;
	IPlayer voiture2;

	Queue<KeyEventGame> actions;

	CircuitSolution gaAlgorithm;

	public RealGameBoard(List<IPlayer> playercars) {
		this.players = new ArrayList<>();
		this.voiture1 = playercars.get(0);
		this.voiture1.getCar().initPosition(55, 550);
		if (this.voiture1.getCar() instanceof PlayerCarComponent) {
			((PlayerCarComponent) this.voiture1.getCar()).setKeys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT,
					KeyEvent.VK_RIGHT);
		}
		this.players.add(this.voiture1);

		if (playercars.size() == 2) {
			this.voiture2 = playercars.get(1);
			this.voiture2.getCar().initPosition(40, 540);
			if (this.voiture2.getCar() instanceof PlayerCarComponent) {
				((PlayerCarComponent) this.voiture2.getCar()).setKeys(KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_Q,
						KeyEvent.VK_D);
			}
			this.players.add(voiture2);
		}

		loadTrack();
	}

	public void loadTrack() {
		getCars().forEach(CarComponent::initPosition);
		try {
			FileReader fr = new FileReader(RELATIVE_PATH_TRACKS + "Track" + level);

			circuit = new Circuit(fr, 5, 55);

			astarPath = new Astar(circuit).call();

			getPlayers().stream().filter(player -> player instanceof IaCarPlayer)
					.forEach(player -> ((IaCarPlayer) player).init(this, 40));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
