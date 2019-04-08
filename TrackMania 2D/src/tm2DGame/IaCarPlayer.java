package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Path2D;

import ia.ga.impl.car.KeyEventGame;

public class IaCarPlayer implements IPlayer{

	CarComponent car;

	public IaCarPlayer(CarComponent car){
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
