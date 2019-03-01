package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Voiture {
	public static final String RELATIVE_PATH = "TrackMania 2D/";

	int aX = 0;
	int aY = 0;

	double vX = 0;
	double vY = 0;

	int pX;
	int pY;

	Image Voiture;
	String keyUse = null;

	public Voiture(int startX, int startY) {
		pX = startX;
		pY = startY;

		ImageIcon iVoiture = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Voiture10.png");
		Voiture = iVoiture.getImage();
	}

	public Rectangle getBounds() {
		Rectangle Box = new Rectangle(pX, pY, 5, 10);
		return Box;
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
		return Voiture;
	}

	public void setKey(String newKey) {
		this.keyUse = newKey;
	}

	public void move() { 
						
		if (keyUse == "T") {
			aX = -1;
			aY = -1;
		} else if (keyUse == "Y") {
			aX = +0;
			aY = -1;
		} else if (keyUse == "U") {
			aX = +1;
			aY = -1;
		} else if (keyUse == "G") {
			aX = -1;
			aY = +0;
		} else if (keyUse == "H") {
			aX = +0;
			aY = +0;
		} else if (keyUse == "J") {
			aX = +1;
			aY = +0;
		} else if (keyUse == "V") {
			aX = -1;
			aY = +1;
		} else if (keyUse == "B") {
			aX = 0;
			aY = +1;
		} else if (keyUse == "N") {
			aX = +1;
			aY = +1;
		}

	}

	public void speed() {
		vX = vX + aX;
		vY = vY + aY;
	}

	public void position() {

		pX = pX + (int) vX;
		pY = pY + (int) vY;
	}
}
