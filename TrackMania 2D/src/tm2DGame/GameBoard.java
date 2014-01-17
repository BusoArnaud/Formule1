package tm2DGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JPanel;

import tm2D.MenuEnd;
import tm2D.MenuMain;
import tm2DGame.Terrain.Bordure;
import tm2DGame.Terrain.Damier;
import tm2DGame.Terrain.Eau;
import tm2DGame.Terrain.Herbe;
import tm2DGame.Terrain.Mur;
import tm2DGame.Terrain.Piste;
import tm2DGame.Terrain.Sable;
import tm2DGame.Voiture;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements KeyListener {

	String game[][] = new String[80][60];

	int level = 1;
	int nombreCoup = 0;
	public static int nombreCoupT;

	private static ArrayList<Piste> Pistes;
	private static ArrayList<Herbe> Herbes;
	private static ArrayList<Bordure> Bordures;
	private static ArrayList<Sable> Sables;
	private static ArrayList<Mur> Murs;
	private static ArrayList<Eau> Eaux;
	private static ArrayList<Damier> Damiers;

	Piste piste;
	Herbe herbe;
	Bordure bordure;
	Sable sable;
	Mur mur;
	Eau eau;
	Damier damier;
	Voiture voiture = new Voiture(50, 550);

	FileReader fr;
	Font levelFont = new Font("SansSerif", Font.BOLD, 15);
	Frame gFrame;

	public GameBoard(Frame gF) {

		loadTrack();

		gFrame = gF;
		setFocusable(true);
		addKeyListener(this);
	}

	public void loadTrack() {

		try {
			fr = new FileReader("Tracks/Track" + level);

			int x = 0;
			int y = 0;
			int i = 0;

			Pistes = new ArrayList<Piste>();
			Herbes = new ArrayList<Herbe>();
			Bordures = new ArrayList<Bordure>();
			Sables = new ArrayList<Sable>();
			Murs = new ArrayList<Mur>();
			Eaux = new ArrayList<Eau>();
			Damiers = new ArrayList<Damier>();

			while ((i = fr.read()) != -1) {
				char txt = (char) i;

				if (txt == ' ') {
					game[x][y] = "PISTE";
					Terrain terrain = new Terrain();
					Terrain.Piste piste = terrain.new Piste(x * 10, y * 10);
					Pistes.add(piste);
				} else if (txt == '.') {
					game[x][y] = "HERBE";
					Terrain terrain = new Terrain();
					Terrain.Herbe herbe = terrain.new Herbe(x * 10, y * 10);
					Herbes.add(herbe);
				} else if (txt == 'B') {
					game[x][y] = "BORDURE";
					Terrain terrain = new Terrain();
					Terrain.Bordure bordure = terrain.new Bordure(x * 10,
							y * 10);
					Bordures.add(bordure);
				} else if (txt == 'S') {
					game[x][y] = "SABLE";
					Terrain terrain = new Terrain();
					Terrain.Sable sable = terrain.new Sable(x * 10, y * 10);
					Sables.add(sable);
				} else if (txt == '#') {
					game[x][y] = "MUR";
					Terrain terrain = new Terrain();
					Terrain.Mur mur = terrain.new Mur(x * 10, y * 10);
					Murs.add(mur);
				} else if (txt == 'o') {
					game[x][y] = "EAU";
					Terrain terrain = new Terrain();
					Terrain.Eau eau = terrain.new Eau(x * 10, y * 10);
					Eaux.add(eau);
				} else if (txt == 'D') {
					game[x][y] = "DAMIER";
					Terrain terrain = new Terrain();
					Terrain.Damier damier = terrain.new Damier(x * 10, y * 10);
					Damiers.add(damier);
				} else if (txt == '*') {
					game[x][y] = null;
				} else if (txt == '\r' || txt == '\n') {
					x--;
				}
				if (x == 79) {
					y++;
					x = 0;
				} else {
					x++;
				}
			}

		} catch (Exception ex) {
		}

		repaint();
	}

	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < Pistes.size(); i++) {
			piste = (Piste) Pistes.get(i);
			g2d.drawImage(piste.getImage(), piste.getX(), piste.getY(), null);
		}
		for (int i = 0; i < Herbes.size(); i++) {
			herbe = (Herbe) Herbes.get(i);
			g2d.drawImage(herbe.getImage(), herbe.getX(), herbe.getY(), null);
		}
		for (int i = 0; i < Bordures.size(); i++) {
			bordure = (Bordure) Bordures.get(i);
			g2d.drawImage(bordure.getImage(), bordure.getX(), bordure.getY(),
					null);
		}
		for (int i = 0; i < Sables.size(); i++) {
			sable = (Sable) Sables.get(i);
			g2d.drawImage(sable.getImage(), sable.getX(), sable.getY(), null);
		}
		for (int i = 0; i < Murs.size(); i++) {
			mur = (Mur) Murs.get(i);
			g2d.drawImage(mur.getImage(), mur.getX(), mur.getY(), null);
		}
		for (int i = 0; i < Eaux.size(); i++) {
			eau = (Eau) Eaux.get(i);
			g2d.drawImage(eau.getImage(), eau.getX(), eau.getY(), null);
		}
		for (int i = 0; i < Damiers.size(); i++) {
			damier = (Damier) Damiers.get(i);
			g2d.drawImage(damier.getImage(), damier.getX(), damier.getY(), null);
		}

		g.setColor(Color.BLACK);
		g.setFont(levelFont);
		g.drawString("Level : " + level, 15, 585);
		g.drawString("|| Coups : " + nombreCoup, 80, 585);

		if (Math.abs(voiture.getvX()) > 0 || Math.abs(voiture.getvY()) > 0) {
			g2d.rotate(
					(((Math.PI)) / 2)
							+ Math.atan2(voiture.getvY(), voiture.getvX()),
					voiture.getpX(), voiture.getpY());

		}

		g2d.drawImage(voiture.getImage(), voiture.getpX() - 5,
				voiture.getpY() - 10, null);
	}

	public void nextTrack() {

		Rectangle voitureRec;
		voitureRec = voiture.getBounds();

		for (int i = 0; i < Damiers.size(); i++) {
			damier = (Damier) Damiers.get(i);
			Rectangle damierRec;
			damierRec = damier.getBounds();
			if (voitureRec.intersects(damierRec)) {
				level++;
				nombreCoupT = +nombreCoup;
				nombreCoup = 0;
				loadTrack();

				if (level == 2) {

					@SuppressWarnings("unused")
					MenuEnd f = new MenuEnd();
					gFrame.dispose();
				}
			}
		}
	}

	public void collision() {

		Rectangle voitureRec;
		voitureRec = voiture.getBounds();

		for (int i = 0; i < Herbes.size(); i++) {
			herbe = (Herbe) Herbes.get(i);
			Rectangle herbeRec;
			herbeRec = herbe.getBounds();
			if (voitureRec.intersects(herbeRec)) {

				voiture.setvX(voiture.getvX() / 1.06);
				voiture.setvY(voiture.getvY() / 1.06);

			}

		}
		for (int i = 0; i < Sables.size(); i++) {
			sable = (Sable) Sables.get(i);
			Rectangle sableRec;
			sableRec = sable.getBounds();
			if (voitureRec.intersects(sableRec)) {

				voiture.setvX(voiture.getvX() / 1.2);
				voiture.setvY(voiture.getvY() / 1.2);

			}

		}

		for (int i = 0; i < Murs.size(); i++) {
			mur = (Mur) Murs.get(i);
			Rectangle murRec;
			murRec = mur.getBounds();
			if (voitureRec.intersects(murRec)) {
				voiture.setpX(50);
				voiture.setpY(550);
				voiture.setvX(0);
				voiture.setvY(0);
			}
		}

		for (int i = 0; i < Eaux.size(); i++) {
			eau = (Eau) Eaux.get(i);
			Rectangle eauRec;
			eauRec = eau.getBounds();
			if (voitureRec.intersects(eauRec)) {
				if ((Math.abs(voiture.getvX()) > 3 || Math.abs(voiture.getvY()) > 3)) {
					voiture.setaX(0);
					voiture.setaY(0);
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		int key = arg0.getKeyCode();

		if (key == KeyEvent.VK_T) {
			voiture.setKey("T");
			voiture.move();
			collision();
			voiture.speed();
			voiture.position();
			nombreCoup++;

		} else if (key == KeyEvent.VK_Y) {
			voiture.setKey("Y");
			voiture.move();
			collision();
			voiture.speed();
			voiture.position();
			nombreCoup++;

		} else if (key == KeyEvent.VK_U) {
			voiture.setKey("U");
			voiture.move();
			collision();
			voiture.speed();
			voiture.position();
			nombreCoup++;

		} else if (key == KeyEvent.VK_G) {
			voiture.setKey("G");
			voiture.move();
			collision();
			voiture.speed();
			voiture.position();
			nombreCoup++;

		} else if (key == KeyEvent.VK_H) {
			voiture.setKey("H");
			voiture.move();
			collision();
			voiture.speed();
			voiture.position();
			nombreCoup++;

		} else if (key == KeyEvent.VK_J) {
			voiture.setKey("J");
			voiture.move();
			collision();
			voiture.speed();
			voiture.position();
			nombreCoup++;

		} else if (key == KeyEvent.VK_V) {
			voiture.setKey("V");
			voiture.move();
			collision();
			voiture.speed();
			voiture.position();
			nombreCoup++;

		} else if (key == KeyEvent.VK_B) {
			voiture.setKey("B");
			voiture.move();
			collision();
			voiture.speed();
			voiture.position();
			nombreCoup++;

		} else if (key == KeyEvent.VK_N) {
			voiture.setKey("N");
			voiture.move();
			collision();
			voiture.speed();
			voiture.position();
			nombreCoup++;

		} else if (key == KeyEvent.VK_R) { // ne fonctionne pas
			voiture.setpX(50);
			voiture.setpY(550);
			voiture.setvX(0);
			voiture.setvY(0);
			nombreCoup = +10;

		} else if (key == KeyEvent.VK_ESCAPE) {
			@SuppressWarnings("unused")
			MenuMain f = new MenuMain();
			gFrame.dispose();
		}

		repaint();
		nextTrack();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
