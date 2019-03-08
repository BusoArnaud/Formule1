package tm2DGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

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
public class GameBoard extends JPanel implements KeyListener, ActionListener {

	Timer timer = new Timer(100, this);
	double currentTime=0;
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
	Voiture voiture = new Voiture(50, 550, 15);
	

	FileReader fr;
	Font levelFont = new Font("SansSerif", Font.BOLD, 15);
	Frame gFrame;

	public GameBoard(Frame gF) {

		loadTrack();

		gFrame = gF;
		setFocusable(true);
		addKeyListener(this);
		timer.start();
	}

	public void loadTrack() {

		try {
			fr = new FileReader("TrackMania 2D/src/Tracks/Track" + level);

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
					Terrain.Bordure bordure = terrain.new Bordure(x * 10, y * 10);
					Bordures.add(bordure);
				} else if (txt == 'S') {
					game[x][y] = "SABLE";
					Terrain terrain = new Terrain();
					Terrain.Sable sable = terrain.new Sable(x * 10, y * 10);
					Sables.add(sable);
				} else if (txt == 'x') {
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
				}
				 if (txt == '*') {
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
			System.out.println(ex);
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
		StringBuilder legends = new StringBuilder();
		legends.append("Level : ");
		legends.append(level);
		legends.append("|| time : " );
		legends.append(Math.floor(currentTime));
		legends.append("|| vitesse : " );
		legends.append(Math.floor(voiture.getSpeed()));
		
		g.drawString(legends.toString(), 15, 585);
		g2d.setColor(Color.black);	
		g2d.rotate(voiture.getCurrentAngle(),
				voiture.getpX(), voiture.getpY());
		g2d.drawRect(voiture.getpX()-5, voiture.getpY()-10, 10, 20);
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

		Rectangle voitureRec = voiture.getBounds();
		for (int i = 0; i < Herbes.size(); i++) {
			herbe = (Herbe) Herbes.get(i);
			Rectangle herbeRec;
			herbeRec = herbe.getBounds();
			if (voitureRec.intersects(herbeRec)) {
				voiture.speedDecrease(0.8);
			}

		}
		for (int i = 0; i < Sables.size(); i++) {
			sable = (Sable) Sables.get(i);
			Rectangle sableRec;
			sableRec = sable.getBounds();
			if (voitureRec.intersects(sableRec)) {
				voiture.speedDecrease(0.7);

			}

		}

		for (int i = 0; i < Murs.size(); i++) {
			mur = (Mur) Murs.get(i);
			Rectangle murRec;
			murRec = mur.getBounds();
			if (voitureRec.intersects(murRec)) {
				voiture.reset(50, 550);
			}
		}

		for (int i = 0; i < Eaux.size(); i++) {
			eau = (Eau) Eaux.get(i);
			Rectangle eauRec;
			eauRec = eau.getBounds();
			if (voitureRec.intersects(eauRec)) {
				if ((Math.abs(voiture.getvX()) > 3 || Math.abs(voiture.getvY()) > 3)) {
					voiture.speedDecrease(0.9);
				}
			}
		}
	}

	public void actionPerformed(ActionEvent ev) {
		currentTime += 0.1;
		if (!voiture.isAccelerate() && voiture.speed != 0) {
			voiture.setDirection(0);
			voiture.speedDecrease(0.9);
		}
		voiture.accelerate();

		if (voiture.isRotate()) {
			voiture.turn();
		}
		collision();
		voiture.rotate();
		voiture.move();
		voiture.position();
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			voiture.setDirection(1);
			voiture.setAccelerate(true);
		}
		if (key == KeyEvent.VK_DOWN) {
			voiture.setDirection(-1);
			voiture.setAccelerate(true);
		}
		if (key == KeyEvent.VK_RIGHT && voiture.getSpeed() > 0) {
			voiture.setRotateDirection(1);
			voiture.setRotate(true);
		}
		if (key == KeyEvent.VK_LEFT && voiture.getSpeed() > 0) {
			voiture.setRotateDirection(-1);
			voiture.setRotate(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		int key = arg0.getKeyCode();
		
		
		if (key == KeyEvent.VK_R) { 
			voiture.reset(50,550);
			nombreCoup += 10;

		} else if (key == KeyEvent.VK_ESCAPE) {
			@SuppressWarnings("unused")
			MenuMain f = new MenuMain();
			gFrame.dispose();
		}
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
			voiture.setAccelerate(false);
		} 
		if (key == KeyEvent.VK_RIGHT  || key == KeyEvent.VK_LEFT) {
			voiture.setRotate(false);
		}
		

		repaint();
		nextTrack();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
