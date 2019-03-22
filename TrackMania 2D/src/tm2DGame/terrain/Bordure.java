package tm2DGame.terrain;

import javax.swing.ImageIcon;

public class Bordure extends Terrain{

	public Bordure(int startX, int startY) {
		super(startX, startY);
		ImageIcon iBordure = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Bordure10.jpg");
		image = iBordure.getImage();
	}

}
