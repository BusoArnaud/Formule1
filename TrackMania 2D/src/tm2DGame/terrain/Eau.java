package tm2DGame.terrain;

import javax.swing.ImageIcon;

public class Eau extends Terrain{

  public Eau(int startX, int startY) {
		super(startX, startY);
		ImageIcon iEau = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Eau10.jpg");
		image = iEau.getImage();
	}

  @Override
  public double getSpeedDecreaseCoef() {
    return WATER_SPEED_COEF;
  }

}
