package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Terrain {
	public static final String RELATIVE_PATH = "TrackMania 2D/";

	public class Piste {

		int x, y;
		Image Piste;

		public Piste(int startX, int startY) {
			x = startX;
			y = startY;
			ImageIcon iPiste = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Piste10.jpg");
			Piste = iPiste.getImage();
		}

		public Rectangle getBounds() {
			Rectangle Box = new Rectangle(x, y, 10, 10);
			return Box;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public Image getImage() {
			return Piste;
		}
	}

	public class Bordure {

		int x, y;
		Image Bordure;

		public Bordure(int startX, int startY) {
			x = startX;
			y = startY;
			ImageIcon iBordure = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Bordure10.jpg");
			Bordure = iBordure.getImage();
		}

		public Rectangle getBounds() {
			Rectangle Box = new Rectangle(x, y, 10, 10);
			return Box;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public Image getImage() {
			return Bordure;
		}
	}

	public class Herbe {

		int x, y;
		Image Herbe;

		public Herbe(int startX, int startY) {
			x = startX;
			y = startY;
			ImageIcon iHerbe = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Herbe10.jpg");
			Herbe = iHerbe.getImage();
		}

		public Rectangle getBounds() {
			Rectangle Box = new Rectangle(x, y, 10, 10);
			return Box;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public Image getImage() {
			return Herbe;
		}
	}

	public class Sable {
		int x, y;
		Image Sable;

		public Sable(int startX, int startY) {
			x = startX;
			y = startY;
			ImageIcon iSable = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Sable10.jpg");
			Sable = iSable.getImage();
		}

		public Rectangle getBounds() {
			Rectangle Box = new Rectangle(x, y, 10, 10);
			return Box;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public Image getImage() {
			return Sable;
		}
	}

	public class Mur {

		int x, y;
		Image Mur;

		public Mur(int startX, int startY) {
			x = startX;
			y = startY;
			ImageIcon iMur = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Mur10.jpg");
			Mur = iMur.getImage();
		}

		public Rectangle getBounds() {
			Rectangle Box = new Rectangle(x, y, 10, 10);
			return Box;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public Image getImage() {
			return Mur;
		}
	}

	public class Eau {
		int x, y;
		Image Eau;

		public Eau(int startX, int startY) {
			x = startX;
			y = startY;
			ImageIcon iEau = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Eau10.jpg");
			Eau = iEau.getImage();
		}

		public Rectangle getBounds() {
			Rectangle Box = new Rectangle(x, y, 10, 10);
			return Box;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public Image getImage() {
			return Eau;
		}
	}

	public class Damier {
		int x, y;
		Image Damier;

		public Damier(int startX, int startY) {
			x = startX;
			y = startY;
			ImageIcon iDamier = new ImageIcon(RELATIVE_PATH + "ImagesCircuit/Damier10.jpg");
			Damier = iDamier.getImage();
		}

		public Rectangle getBounds() {
			Rectangle Box = new Rectangle(x, y, 10, 10);
			return Box;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public Image getImage() {
			return Damier;
		}
	}
}
