package tm2DGame.terrain;

import javax.swing.ImageIcon;

public class Herbe extends Terrain{

	public Herbe(int startX, int startY) {
		super(startX, startY);
		ImageIcon iHerbe = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Herbe10.jpg");
		image = iHerbe.getImage();
	}

}
