package tm2DGame;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;

import ia.ga.impl.car.CircuitSolution;
import ia.ga.impl.car.KeyEventGame;
import tm2DGame.boards.RealGameBoard;

public class IaCarPlayer implements IPlayer {

	CarComponent car;
	final int carIndex;
	private long count;
	private Queue<KeyEventGame> actions = new LinkedList<>();
	CircuitSolution gaAlgorithm;
	private KeyEventGame currentAction;
	private final int frequency = 10;

	public IaCarPlayer(CarComponent car, int carIndex) {
		this.carIndex = carIndex;
		this.car = car;
	}

	public void init(RealGameBoard gameBoard, int frame) {
		gaAlgorithm = new CircuitSolution(gameBoard, carIndex, frame);
		count = 0;
	}

	@Override
	public KeyEventGame getAction() {
		if (count % frequency == 0) {
			if (actions.isEmpty()) {
				try {
					long start = System.currentTimeMillis();
					actions.addAll(gaAlgorithm.call());
					System.out.println(System.currentTimeMillis() - start);
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			currentAction = actions.poll();
		}

		count++;
		return currentAction;
	}

	@Override
	public CarComponent getCar() {
		return car;
	}

}
