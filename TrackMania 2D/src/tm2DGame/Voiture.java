package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Voiture {

	int aX = 0;
	int aY = 0;

	int vX = 0;
	int vY = 0;

	int pX;
	int pY;

	Image Voiture;
	String keyUse = null;

	public Voiture(int startX, int startY) {
		pX = startX;
		pY = startY;

		ImageIcon iVoiture = new ImageIcon("ImagesCircuit/Voiture10.png");
		Voiture = iVoiture.getImage();
	}

	public Rectangle getBounds() {
		Rectangle Box = new Rectangle(pX, pY, 10, 20);
		return Box;
	}

	public int getX() {
		return pX;
	}

	public int getY() {
		return pY;
	}

	public int getvX() {
		return vX;
	}

	public int getvY() {
		return vY;
	}

	public void setvX(int newvX) {
		vX = newvX;
	}

	public void setvY(int newvY) {
		vY = newvY;
	}
	
	public void setaX(int newaX){
		aX=newaX;
	}
	
	public void setaY(int newaY){
		aY=newaY;
	}

	public Image getImage() {
		return Voiture;
	}

	public void setKey(String newKey) {
		this.keyUse = newKey;
	}

	/*
	 * public void move() { if (keyUse == "T") { if (aX > 0) { aX = -1; } else {
	 * aX = +0; } aY = +1; } else if (keyUse == "Y") { aX = +0; aY = +1; } else
	 * if (keyUse == "U") { aX = +1; aY = +1; } else if (keyUse == "G") { if (aX
	 * > 0) { aX = -1; } else { aX = +0; } aY = +0; } else if (keyUse == "H") {
	 * aX = +0; aY = +0; } else if (keyUse == "J") { aX = +1; aY = +0; } else if
	 * (keyUse == "V") { if (aX > 0) { aX = -1; } else { aX = +0; } if (aY > 0)
	 * { aY = -1; } else { aY = +0; } } else if (keyUse == "B") { aX = +0; if
	 * (aY > 0) { aY = -1; } else { aY = +0; } } else if (keyUse == "N") { aX =
	 * +1; if (aY > 0) { aY = -1; } else { aY = +0; } }
	 * 
	 * vX = vX + aX; vY = vY + aY;
	 * 
	 * pX = pX + vX; pY = pY + vY;
	 * 
	 * }
	 */

	public void move() { // provisoire en attendant de faire tourner les axes de
							// direction pour la voiture, à voir
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

		vX = vX + aX;
		vY = vY + aY;

		pX = pX + vX;
		pY = pY + vY;

	}
}
