package tm2DGame.terrain;

import javax.swing.ImageIcon;

public class Herbe extends Terrain{

	public Herbe(int startX, int startY) {
		super(startX, startY);
		ImageIcon iHerbe = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Herbe10.jpg");
		image = iHerbe.getImage();
	}

}
