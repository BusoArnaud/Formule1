package tm2DGame.terrain;

import javax.swing.ImageIcon;

public class Herbe extends Terrain{

  private static double speedCoef= 0.9;
  
	public Herbe(int startX, int startY) {
		super(startX, startY);
		ImageIcon iHerbe = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Herbe10.jpg");
		image = iHerbe.getImage();
	}
	
	 @Override
	  public double getSpeedDecreaseCoef() {
	    return speedCoef; 
	  }

}
