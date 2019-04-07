package tm2DGame;

import tm2D.Constants;

public class PlayerCarComponent extends CarComponent implements Constants {

	int keyUp;
	int keyDown;
	int keyLeft;
	int keyRight;

	public PlayerCarComponent(Voiture voiture) {
		super(voiture);
	}

	public PlayerCarComponent(CarComponent voiture) {
		super((CarComponent) voiture);
	}

	public void keyPressed(int key) {
		if (key == this.keyUp) {
			this.setDirection(1);
			this.setAccelerate(true);
		}
		if (key == this.keyDown) {
			this.setDirection(-1);
			this.setAccelerate(true);
		}
		if (key == this.keyRight) {
			this.setRotateDirection(1);
			this.setRotate(true);
		}
		if (key == this.keyLeft) {
			this.setRotateDirection(-1);
			this.setRotate(true);
		}
	}

	public void keyReleased(int key) {
		if (key == this.keyUp || key == this.keyDown) {
			this.setAccelerate(false);
		}
		if (key == this.keyRight || key == this.keyLeft) {
			this.setRotate(false);
		}
	}

	public void setKeys(int up, int down, int left, int right) {
		this.keyUp = up;
		this.keyDown = down;
		this.keyLeft = left;
		this.keyRight = right;

	}
}
