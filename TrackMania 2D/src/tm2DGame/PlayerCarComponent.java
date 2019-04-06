package tm2DGame;

import tm2D.Constants;

public class PlayerCarComponent extends CarComponent implements Constants, IPlayer {


	public PlayerCarComponent(Voiture voiture) {
		super(voiture);
	}

	public PlayerCarComponent(IPlayer voiture) {
		super((CarComponent)voiture);
	}

	@Override
	public void initPosition(int startX, int startY) {
		super.initPosition(startX, startY);
	}
	@Override
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

	@Override
	public void keyReleased(int key) {
		
		if (key == this.getKeyUp() || key == this.getKeyDown()) {
			this.setAccelerate(false);
		}
		if (key == this.getKeyRight() || key == this.getKeyLeft()) {
			this.setRotate(false);
		}
	}
	
}
