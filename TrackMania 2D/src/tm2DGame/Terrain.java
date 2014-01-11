package tm2DGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Terrain {

	public class Piste {

		int x, y;
		Image Piste;

		public Piste(int Startx, int Starty) {
			x = Startx;
			y = Starty;
			ImageIcon iPiste = new ImageIcon("ImagesCircuit/Piste10.jpg");
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

		public Bordure(int Startx, int Starty) {
			x = Startx;
			y = Starty;
			ImageIcon iBordure = new ImageIcon("ImagesCircuit/Bordure10.jpg");
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

		public Herbe(int Startx, int Starty) {
			x = Startx;
			y = Starty;
			ImageIcon iHerbe = new ImageIcon("ImagesCircuit/Herbe10.jpg");
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

		public Sable(int Startx, int Starty) {
			x = Startx;
			y = Starty;
			ImageIcon iSable = new ImageIcon("ImagesCircuit/Sable10.jpg");
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

		public Mur(int Startx, int Starty) {
			x = Startx;
			y = Starty;
			ImageIcon iMur = new ImageIcon("ImagesCircuit/Mur10.jpg");
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

		public Eau(int Startx, int Starty) {
			x = Startx;
			y = Starty;
			ImageIcon iEau = new ImageIcon("ImagesCircuit/Eau10.jpg");
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

		public Damier(int Startx, int Starty) {
			x = Startx;
			y = Starty;
			ImageIcon iDamier = new ImageIcon("ImagesCircuit/Damier10.jpg");
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
