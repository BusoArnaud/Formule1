package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Path2D;

import ia.ga.impl.car.KeyEventGame;

public interface IPlayer{

	void initPosition();

	void initPosition(int x, int y);

	void setKeys(int up, int down, int left, int right);

	Rectangle getBounds();

	void calculateArea();

	Path2D getArea();

	KeyEventGame getKeyEventGame();

	double getCurrentAngle();

	double getpX();

	double getpY();

	int getImageY();

	int getImageX();

	Image getImage();

	void setSpeedDecreaseCoef(double speedCoef);

	void accelerate(int frame);

	boolean isRotate();

	void turn();

	void rotate(int frame);

	void move();

	void position();

	void setAccelerate(boolean b);

	void setRotate(boolean b);

	void setDirection(int i);

	void setRotateDirection(int i);

	int getKeyDown();

	int getKeyLeft();

	int getKeyRight();

	int getKeyUp();

	void keyPressed(int key);

	void keyReleased(int key);

}
