package tm2DGame.terrain;

import javax.swing.ImageIcon;

public class Sable extends Terrain{

  private static double speedCoef= 0.9;
  
	public Sable(int startX, int startY) {
		super(startX, startY);
		ImageIcon iSable = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Sable10.jpg");
		image = iSable.getImage();
	}
	
	@Override
	public double getSpeedDecreaseCoef() {
	  return speedCoef; 
	}

}
