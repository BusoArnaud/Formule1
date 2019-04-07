package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import javax.swing.ImageIcon;

import ia.ga.impl.car.KeyEventGame;
import tm2D.Constants;

public class CarComponent implements Constants{

	Voiture voiture;
	
	double vX = 0;
	double vY = 0;
	double currentAngle = 0;
	int direction = 0;
	int rotateDirection = 0;
	boolean accelerate = false;
	boolean rotate = false;
	int angle = 0;
	double speed = 0;
	double speedDecreaseCoef = 1d;
	double pX;
	double pY;
	double startX;
	double startY;
	
	Path2D area;

	String keyUse = null;

	public CarComponent(Voiture voiture) {
		this.voiture = voiture;
	}
	
  public CarComponent(CarComponent voiture) {
    this.pX = voiture.pX;
    this.pY = voiture.pY;
    this.speed = voiture.speed;
    this.speedDecreaseCoef = voiture.speedDecreaseCoef;
    this.accelerate = voiture.accelerate;
    this.angle = voiture.angle;
    this.currentAngle = voiture.currentAngle;
    this.direction = voiture.direction;
    this.keyUse = voiture.keyUse;
    this.rotate = voiture.rotate;
    this.rotateDirection = voiture.rotateDirection;
    this.voiture = voiture.voiture;
    this.vX = voiture.vX;
    this.vY = voiture.vY;
    this.startX = voiture.startX;
    this.startY = voiture.startY;
  }

	public void initPosition(int startX, int startY) {
		this.startX = startX;
		this.startY = startY;
		pX = startX;
		pY = startY;
		currentAngle = 0;
		direction = 0;
		rotateDirection = 0;
		accelerate = false;
		rotate = false;
		angle = 0;
		speed = 0;
		keyUse = null;
		speedDecreaseCoef = 1d;
	}

	public void initPosition() {
		pX = startX;
		pY = startY;
		currentAngle = 0;
		direction = 0;
		rotateDirection = 0;
		accelerate = false;
		rotate = false;
		angle = 0;
		speed = 0;
		keyUse = null;
		speedDecreaseCoef = 1d;
	}

	public Rectangle getBounds() {
		return this.getArea().getBounds();
	}

	public void calculateArea() {
		AffineTransform at = new AffineTransform();
		at.rotate(currentAngle, pX, pY);
		Path2D path2D = new Path2D.Double();
		path2D.append(new Rectangle(this.getImageX(), this.getImageY(), voiture.getiVoiture().getIconWidth(),
				voiture.getiVoiture().getIconHeight()).getBounds2D().getPathIterator(at), true);
		area = path2D;
	}
	
	public Path2D getArea(){
		return this.area;
	}

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void setSpeedDecreaseCoef(double coef){
		this.speedDecreaseCoef = coef;
	}


	public int getRotateDirection() {
		return this.rotateDirection;
	}

	public void setRotateDirection(int rotateDirection) {
		this.rotateDirection = rotateDirection;
	}

	public boolean isAccelerate() {
		return this.accelerate;
	}

	public void setRotate(boolean rotate) {
		this.rotate = rotate;
	}

	public boolean isRotate() {
		return this.rotate;
	}

	public void setAccelerate(boolean accelerate) {
		this.accelerate = accelerate;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		if(speed > voiture.getMaxSpeed()){
			speed = voiture.getMaxSpeed();
		}
		if(speed < -voiture.getMaxSpeed() / 4){
			speed = -voiture.getMaxSpeed() / 4;
		}
		this.speed = speed;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public void setCurrentAngle(double currentAngle) {
		this.currentAngle = currentAngle;
	}

	public double getCurrentAngle() {
		return currentAngle;
	}

	public double getpX() {
		return pX;
	}

	public double getpY() {
		return pY;
	}

	public void setpX(double newpX) {
		pX = newpX;
	}

	public void setpY(double newpY) {
		pY = newpY;
	}

	public double getvX() {
		return vX;
	}

	public double getvY() {
		return vY;
	}

	public void setvX(double newvX) {
		vX = newvX;
	}

	public void setvY(double newvY) {
		vY = newvY;
	}

	public Image getImage() {
		return voiture.getImage();
	}

	public ImageIcon getImageIcon() {
		return voiture.getiVoiture();
	}

	public int getImageX() {
		return (int) (this.pX - this.voiture.getiVoiture().getIconWidth() / 2.0);
	}

	public int getImageY() {
		return (int) (this.pY - this.voiture.getiVoiture().getIconHeight() / 2.0);
	}

	public double getSpeedFrame(int frame){
		return voiture.getMaxSpeed() * frame / 1000.0; 
	}

	public void accelerate(int frame) {
		double currentDirection = this.getDirection();

		if(this.isAccelerate()){
			currentDirection =  currentDirection * voiture.getAccelerationRate() * 1.0;
		}
		if(this.getSpeed() > voiture.getMaxSpeed()*speedDecreaseCoef){
			currentDirection = -1;
		}
		double newSpeed = this.getSpeed() + currentDirection * getSpeedFrame(frame) * (1.0 + 1.0 - speedDecreaseCoef);
		
		if (!this.isAccelerate() && Math.abs(newSpeed) < 1) {
			newSpeed = 0;
		} else if (!this.isAccelerate() && Math.abs(newSpeed) > 1) {
			newSpeed = this.getSpeed() * 0.95 * (0.5 + speedDecreaseCoef / 2.0) ;
		} 
		this.setSpeed(newSpeed);
	}

	public void turn() {
		if(Math.abs(this.getSpeed()) > 0.5){
			angle += rotateDirection;
		}
	}

	public void rotate(int frame) {
		currentAngle = angle * voiture.getRotationRadian() * frame / 100.0;
	}

	public void move() {
		double moveX = - speed * Math.sin(currentAngle);
		double moveY = speed * Math.cos(currentAngle); 
		vX = - moveX;
		vY = - moveY;
	}

	public void position() {
		pX = pX + vX;
		pY = pY + vY;
	}


	public KeyEventGame getKeyEventGame() {
		return KeyEventGame.find(accelerate, rotate, direction, rotateDirection);
	}
	
}
