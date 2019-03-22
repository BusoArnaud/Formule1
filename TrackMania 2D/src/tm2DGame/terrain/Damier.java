package tm2DGame.terrain;

import javax.swing.ImageIcon;

public class Damier extends Terrain{

	public Damier(int startX, int startY) {
		super(startX, startY);
		ImageIcon iDamier = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Damier10.jpg");
		image = iDamier.getImage();
	}

}
