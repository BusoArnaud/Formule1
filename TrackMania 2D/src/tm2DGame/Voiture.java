package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import tm2D.Constants;

public class Voiture implements Constants{
	public static final Double ROTATION = Math.PI/16;

	int aX = 0;
	int aY = 0;
	int angle = 0;
	double speed = 0;
	int maxSpeed; 
	double currentAngle = 0;
	int direction = 0;
	int rotateDirection = 0;
	boolean accelerate = false;
	boolean rotate = false;
	double vX = 0;
	double vY = 0;

	int pX;
	int pY;

	Image voiture;
	String keyUse = null;

	public Voiture(int startX, int startY, int maxSpeed) {
		pX = startX;
		pY = startY;
		this.maxSpeed = maxSpeed;

		ImageIcon iVoiture = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Voiture10.png");
		voiture = iVoiture.getImage();
	}

	public void reset(int x, int y ){
		this.setpX(x);
		this.setpY(y);
		this.setvX(0);
		this.setvY(0);
		this.setSpeed(0);
		this.setAngle(0);
		this.setCurrentAngle(0d);
	}

	public void applyActionAndRotate() {
		this.action();
		this.rotate();
	}

	public Rectangle getBounds() {
		Rectangle Box = new Rectangle(pX-5, pY-10, 8, 18);
		return Box;
	}

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void speedDecrease(double ratio ){
		this.setSpeed(speed * ratio);
		double min = accelerate ? 1*direction : 0;
		if(Math.abs(this.getSpeed()) < 1){
			this.setSpeed(min);
		}
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
		if(speed > maxSpeed){
			speed = maxSpeed;
		}
		if(speed < -maxSpeed/4){
			speed = -maxSpeed / 4;
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

	public int getpX() {
		return pX;
	}

	public int getpY() {
		return pY;
	}

	public void setpX(int newpX) {
		pX = newpX;
	}

	public void setpY(int newpY) {
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

	public int getaX() {
		return aX;
	}

	public int getaY() {
		return aY;
	}

	public void setaX(int newaX) {
		aX = newaX;
	}

	public void setaY(int newaY) {
		aY = newaY;
	}

	public Image getImage() {
		return voiture;
	}

	public void setKey(String newKey) {
		this.keyUse = newKey;
	}

	public void action() { 

		if (String.valueOf(KeyEvent.VK_UP).equals(keyUse)) {
			accelerate();
		} else if (String.valueOf(KeyEvent.VK_RIGHT).equals(keyUse)) {
			turn();
		} else if (String.valueOf(KeyEvent.VK_DOWN).equals(keyUse)) {
			accelerate();
		} else if (String.valueOf(KeyEvent.VK_LEFT).equals(keyUse)) {
			turn();
		}
	}

	public void accelerate() {
		angle += 0;
		this.setSpeed(this.getSpeed() + this.getDirection());
	}

	public void turn() {
		angle += rotateDirection;
	}

	public void speed() {
		vX = vX + aX;
		vY = vY + aY;
	}

	public void rotate() {
		currentAngle = angle * ROTATION;
	}

	public void move() {
		double moveX = - speed * Math.sin(currentAngle);
		double moveY = speed * Math.cos(currentAngle); 
		vX = - moveX;
		vY = - moveY;
	}

	public void position() {
		pX = pX + (int) Math.round(vX);
		pY = pY + (int) Math.round(vY);
	}
}
