package tm2DGame;


import ia.ga.impl.car.KeyEventGame;

public class RealCarPlayer implements IPlayer{

	CarComponent car;

	public RealCarPlayer(CarComponent car){
		this.car = car;
	}

	@Override
	public KeyEventGame getAction() {
		return null;
	}

	@Override
	public CarComponent getCar() {
		return car;
	}

}
