package tm2DGame.terrain;

import javax.swing.ImageIcon;

public class Mur extends Terrain{

	public Mur(int startX, int startY) {
		super(startX, startY);
		ImageIcon iMur = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Mur10.jpg");
		image = iMur.getImage();
	}

}
