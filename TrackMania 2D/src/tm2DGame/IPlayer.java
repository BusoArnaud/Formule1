package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Path2D;

import ia.ga.impl.car.KeyEventGame;

public interface IPlayer{

	KeyEventGame getAction();

	CarComponent getCar();

}
