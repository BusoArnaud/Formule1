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
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import tm2D.MenuEnd;
import tm2D.MenuMain;
import tm2DGame.terrain.*;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements KeyListener, ActionListener {

	Timer timer = new Timer(100, this);
	double currentTime=0;
	String game[][] = new String[80][60];

	int level = 1;
	int nombreCoup = 0;
	public static int nombreCoupT;

	private static ArrayList<Terrain> terrains;

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
			fr = new FileReader("TrackMania 2D" + File.separatorChar + "src" + File.separatorChar + "Tracks" + File.separatorChar + "Track" + level);

			int x = 0;
			int y = 0;
			int i = 0;

			terrains = new ArrayList<Terrain>();

			while ((i = fr.read()) != -1) {
				char txt = (char) i;
				TerrainBuilder terrainBuilder = new TerrainBuilder(txt, x, y);
				if (terrainBuilder.isTerrain()) {
					game[x][y] = terrainBuilder.getType();
					terrains.add(terrainBuilder.getTerrain());
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

		for (int i = 0; i < terrains.size(); i++) {
			Terrain terrain = (Terrain) terrains.get(i);
			g2d.drawImage(terrain.getImage(), terrain.getX(), terrain.getY(), null);
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

		for (int i = 0; i < terrains.size(); i++) {
			Terrain terrain = (Terrain) terrains.get(i);
			if(terrain instanceof Damier){
				Damier damier = (Damier) terrain;
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
	}

	public void collision() {
		Rectangle voitureRec = voiture.getBounds();
		for (int i = 0; i < terrains.size(); i++) {
			Terrain terrain = (Terrain) terrains.get(i);
			Rectangle rec = terrain.getBounds();
			if (voitureRec.intersects(rec)) {
				if (terrain instanceof Herbe) {
					voiture.speedDecrease(0.95);
				}
				if (terrain instanceof Sable) {
					voiture.speedDecrease(0.9);
				}
				if (terrain instanceof Eau) {
					voiture.speedDecrease(0.92);
				}
				if (terrain instanceof Mur) {
					voiture.reset(50, 550);
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
		if (key == KeyEvent.VK_RIGHT && voiture.getSpeed() != 0) {
			voiture.setRotateDirection(1);
			voiture.setRotate(true);
		}
		if (key == KeyEvent.VK_LEFT && voiture.getSpeed() != 0) {
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
