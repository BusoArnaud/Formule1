package tm2DGame.terrain;

import javax.swing.ImageIcon;

public class Sable extends Terrain{

	public Sable(int startX, int startY) {
		super(startX, startY);
		ImageIcon iSable = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Sable10.jpg");
		image = iSable.getImage();
	}

}
