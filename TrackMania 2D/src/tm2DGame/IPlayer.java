package tm2DGame;

import ia.ga.impl.car.KeyEventGame;

public interface IPlayer{

	KeyEventGame getAction();

	CarComponent getCar();

}
