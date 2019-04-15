package tm2DGame;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

import ia.ga.impl.car.CircuitSolution;
import ia.ga.impl.car.KeyEventGame;
import tm2DGame.boards.RealGameBoard;
import tm2DGame.boards.SimulationBoard;

public class IaCarPlayer implements IPlayer {

	CarComponent car;
	final int carIndex;
	private long count;
	private Queue<KeyEventGame> actions = new LinkedList<>();
	CircuitSolution gaAlgorithm;
	private KeyEventGame currentAction;
	private final int frequency = 10;
	private boolean ended = false;
	private SimulationPlayer simulationPlayer;
	
	private ReentrantLock lock = new ReentrantLock();
	private SimulationBoard simulationBoard;

	public IaCarPlayer(CarComponent car, int carIndex) {
		this.carIndex = carIndex;
		this.car = car;
	}

	public void init(RealGameBoard gameBoard, int frame) {
		ended = false;
		currentAction = null;
		actions = new LinkedList<>();
		gaAlgorithm = new CircuitSolution(gameBoard, 0, frame);
		count = 0;
		simulationPlayer = new SimulationPlayer(new CarComponent(gameBoard.getCars().get(carIndex)));
		
		simulationBoard = new SimulationBoard(gameBoard);
		simulationBoard.setVoiture(Collections.singletonList(simulationPlayer));
		gaAlgorithm.setBoard(simulationBoard);
	}

	@Override
	public KeyEventGame getAction() {
		if (count % frequency == 0) {
			if (!ended) {
				new Thread(() -> {
					if (!lock.isLocked()) {
						lock.lock();
						List<KeyEventGame> tempActions;
						try {
							tempActions = gaAlgorithm.call();
							actions.addAll(tempActions);
							for (KeyEventGame action : tempActions) {
								simulationPlayer.setAction(action);
								ended = simulationBoard.advance(40);
							}
						} catch (InstantiationException | IllegalAccessException | InvocationTargetException
								| NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						lock.unlock();
					}
				}).start();
			}
			currentAction = !actions.isEmpty() ? actions.poll() : KeyEventGame.NOTHING;
		}
<<<<<<< HEAD

		count++;
		return currentAction;
=======
		
		count = (count + 1) % 4;
		return action;
>>>>>>> count --
	}

	@Override
	public CarComponent getCar() {
		return car;
	}

}
