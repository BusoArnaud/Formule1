package tm2DGame;

import java.awt.Image;
import javax.swing.ImageIcon;

import tm2D.Constants;

public class Voiture implements Constants{
	
	int rotation;
	int maxSpeed; 
	double accelerationRate;

	Image voiture;
	ImageIcon iVoiture;

	public Voiture(int maxSpeed,int rotation,double accelerationRate, String image) {
		this.maxSpeed = maxSpeed;
		this.rotation = rotation;
		this.accelerationRate = accelerationRate;
		this.iVoiture = new ImageIcon(image);
		this.voiture = iVoiture.getImage();
	}

	public Image getImage() {
		return voiture;
	}

	/**
	 * @return the accelerationRate
	 */
	public double getAccelerationRate() {
		return accelerationRate;
	}
	/**
	 * @return the maxSpeed
	 */
	public int getMaxSpeed() {
		return maxSpeed;
	}
	/**
	 * @return the rotation
	 */
	public double getRotationRadian() {
		return Math.PI / rotation * 1.0;
	}

	public int getRotation() {
		return rotation;
	}
	/**
	 * @return the voiture
	 */
	public Image getVoiture() {
		return voiture;
	}
	/**
	 * @return the iVoiture
	 */
	public ImageIcon getiVoiture() {
		return iVoiture;
	}

}
