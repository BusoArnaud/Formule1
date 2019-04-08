package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Path2D;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import ia.ga.impl.car.CircuitSolution;
import ia.ga.impl.car.KeyEventGame;

public class IaCarPlayer implements IPlayer{

	CarComponent car;
	private int count;
	private LinkedList<KeyEventGame> actions;
	CircuitSolution gaAlgorithm;

	public IaCarPlayer(CarComponent car, GameBoard gameBoard) {
		this.car = car;
		count = 0;
		gaAlgorithm = new CircuitSolution(gameBoard, car);
	}

	public void  updateAlgorithm(GameBoard gameBoard){
		gaAlgorithm = new CircuitSolution(gameBoard, car);
	}

	@Override
	public KeyEventGame getAction() {

		if (count == 0) {
			if (actions == null || actions.isEmpty()) {
			
				try {
					long start = System.currentTimeMillis();
					actions = new LinkedList<>(gaAlgorithm.call().subList(0, 3));
					System.out.println(System.currentTimeMillis() - start);
				} catch (InstantiationException | IllegalAccessException |
					InvocationTargetException
					| NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//actions.poll().getCarBehavior().accept(car);
			// astar.setCarTerrain(circuit.getTerrain(voiture1.getpX(),
			// voiture1.getpY()));
			// new Thread(()-> path = astar.call()).start();
		}
		
		count = (count + 1) % actions.size();
		return actions.poll();
	}

	@Override
	public CarComponent getCar() {
		return car;
	}

}
