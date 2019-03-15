package tm2DGame.terrain;

import javax.swing.ImageIcon;

public class Piste extends Terrain{

	public Piste(int startX, int startY) {
		super(startX, startY);
		ImageIcon iPiste = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Piste10.jpg");
		image = iPiste.getImage();
	}

}
