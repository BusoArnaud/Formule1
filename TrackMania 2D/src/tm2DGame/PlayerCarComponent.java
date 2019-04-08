package tm2DGame;

import ia.ga.impl.car.KeyEventGame;
import tm2D.Constants;

public class PlayerCarComponent extends CarComponent implements Constants {

	public PlayerCarComponent(Voiture voiture) {
		super(voiture);
	}

	public PlayerCarComponent(IPlayer voiture) {
		super((CarComponent) voiture);
	}

	@Override
	public void initPosition(int startX, int startY) {
		super.initPosition(startX, startY);
	}

	public void keyPressed(int key) {
		if (key == this.getKeyUp()) {
			this.setDirection(1);
			this.setAccelerate(true);
		}
		if (key == this.getKeyDown()) {
			this.setDirection(-1);
			this.setAccelerate(true);
		}
		if (key == this.getKeyRight()) {
			this.setRotateDirection(1);
			this.setRotate(true);
		}
		if (key == this.getKeyLeft()) {
			this.setRotateDirection(-1);
			this.setRotate(true);
		}
	}

	public void keyReleased(int key) {

		if (key == this.getKeyUp() || key == this.getKeyDown()) {
			this.setAccelerate(false);
		}
		if (key == this.getKeyRight() || key == this.getKeyLeft()) {
			this.setRotate(false);
		}
	}
}
