package tm2DGame;

import ia.ga.impl.car.KeyEventGame;

public class SimulationPlayer implements IPlayer {

	private final CarComponent car;

	private KeyEventGame action = null;

	public SimulationPlayer(CarComponent car) {
		this.car = car;
	}

	@Override
	public KeyEventGame getAction() {
		return action;
	}

	@Override
	public CarComponent getCar() {
		return car;
	}

	public void setAction(KeyEventGame action) {
		this.action = action;
	}

}
