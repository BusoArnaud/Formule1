package tm2DGame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JPanel;

import tm2DGame.Terrain.Bordure;
import tm2DGame.Terrain.Damier;
import tm2DGame.Terrain.Eau;
import tm2DGame.Terrain.Herbe;
import tm2DGame.Terrain.Mur;
import tm2DGame.Terrain.Piste;
import tm2DGame.Terrain.Sable;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements KeyListener {

	String Game[][] = new String[80][60];
	private static ArrayList<Piste> Pistes;
	private static ArrayList<Herbe> Herbes;
	private static ArrayList<Bordure> Bordures;
	private static ArrayList<Sable> Sables;
	private static ArrayList<Mur> Murs;
	private static ArrayList<Eau> Eaus;
	private static ArrayList<Damier> Damiers;
	Piste piste;
	Herbe herbe;
	Bordure bordure;
	Sable sable;
	Mur mur;
	Eau eau;
	Damier damier;
	Voiture voiture;
	FileReader fr;

	public GameBoard() {

		ChargerCircuit();

		setFocusable(true);
		addKeyListener(this);
	}

	public void ChargerCircuit() {
		try {
			fr = new FileReader("Tracks/");
			int x = 0;
			int y = 0;
			int i = 0;

			Pistes = new ArrayList<Piste>();
			Herbes = new ArrayList<Herbe>();
			Bordures = new ArrayList<Bordure>();
			Sables = new ArrayList<Sable>();
			Murs = new ArrayList<Mur>();
			Eaus = new ArrayList<Eau>();
			Damiers = new ArrayList<Damier>();

			while ((i = fr.read()) != -1) {
				char strImg = (char) i;

				if (strImg == '+') {
					Game[x][y] = "PISTE";
					Terrain terrain = new Terrain();
					Terrain.Piste piste = terrain.new Piste(x * 10, y * 10);
					Pistes.add(piste);
				} else if (strImg == '¤') {
					Game[x][y] = "HERBE";
					Terrain terrain = new Terrain();
					Terrain.Herbe herbe = terrain.new Herbe(x * 10, y * 10);
					Herbes.add(herbe);
				} else if (strImg == 'b') {
					Game[x][y] = "BORDURE";
					Terrain terrain = new Terrain();
					Terrain.Bordure bordure = terrain.new Bordure(x * 10,
							y * 10);
					Bordures.add(bordure);
				} else if (strImg == '.') {
					Game[x][y] = "SABLE";
					Terrain terrain = new Terrain();
					Terrain.Sable sable = terrain.new Sable(x * 10, y * 10);
					Sables.add(sable);
				} else if (strImg == '#') {
					Game[x][y] = "MUR";
					Terrain terrain = new Terrain();
					Terrain.Mur mur = terrain.new Mur(x * 10, y * 10);
					Murs.add(mur);
				} else if (strImg == 'o') {
					Game[x][y] = "EAU";
					Terrain terrain = new Terrain();
					Terrain.Eau eau = terrain.new Eau(x * 10, y * 10);
					Eaus.add(eau);
				} else if (strImg == '$') {
					Game[x][y] = "DAMIER";
					Terrain terrain = new Terrain();
					Terrain.Damier damier = terrain.new Damier(x * 10, y * 10);
					Damiers.add(damier);
				} else if (strImg == ' ') {
					Game[x][y] = null;
				} else if (strImg == '\r' || strImg == '\n') {
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
		for (int i = 0; i < Eaus.size(); i++) {
			eau = (Eau) Eaus.get(i);
			g2d.drawImage(eau.getImage(), eau.getX(), eau.getY(), null);
		}
		for (int i = 0; i < Damiers.size(); i++) {
			damier = (Damier) Damiers.get(i);
			g2d.drawImage(damier.getImage(), damier.getX(), damier.getY(), null);
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
