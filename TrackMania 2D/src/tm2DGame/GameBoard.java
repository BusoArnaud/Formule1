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

import javax.swing.JPanel;
import javax.swing.Timer;

import tm2D.Constants;
import tm2D.MenuEnd;
import tm2D.MenuMain;
import tm2DGame.terrain.Mur;
import tm2DGame.terrain.Terrain;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements KeyListener, ActionListener, Constants {
	int frame = 40;
	Timer timer = new Timer(frame, this);
	double currentTime=0;

	int level = 1;
	int nombreCoup = 0;
	
	public static int nombreCoupT;

	private Circuit circuit;
	
	Voiture voiture ;
	
	Font levelFont = new Font("SansSerif", Font.BOLD, 15);
	Frame gFrame;

	public GameBoard(Frame gF, Voiture car) {
		voiture = car;
		voiture.initPosition(50, 550);

		loadTrack();

		gFrame = gF;
		setFocusable(true);
		addKeyListener(this);
		timer.start();
	}

	public void loadTrack() {

		try {
			FileReader fr = new FileReader(RELATIVE_PATH_TRACKS + "Track" + level);

			circuit = new Circuit(fr);
		  
		} catch (Exception ex) {
			System.out.println(ex);
		}

		repaint();
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		circuit.paint(g2d);

		g.setColor(Color.BLACK);
		g.setFont(levelFont);
		StringBuilder legends = new StringBuilder();
		legends.append("Level : ");
		legends.append(level);
		legends.append("|| time : " );
		legends.append(Math.floor(currentTime));
		legends.append("|| vitesse : ");
		legends.append(Math.floor(voiture.getSpeed()));
		legends.append("|| angle : ");
		legends.append(Math.floor(voiture.getCurrentAngle()*100.0));
		
		g.drawString(legends.toString(), 15, 585);
		g2d.setColor(Color.black);	
		g2d.rotate(voiture.getCurrentAngle(),
				voiture.getpX(), voiture.getpY());

		g2d.drawImage(voiture.getImage(), voiture.getImageX(),
				voiture.getImageY(), null);
	}

	public void nextTrack() {

		Rectangle voitureRec;
		voitureRec = voiture.getBounds();

		for (Terrain terrain : circuit.getEndTerrains()) {
			if (voitureRec.intersects(terrain.getBounds())) {
				level++;
				nombreCoupT = +nombreCoup;
				nombreCoup = 0;
				loadTrack();

				if (level == 2) {

					@SuppressWarnings("unused")
					MenuEnd f = new MenuEnd();
					gFrame.dispose();
				}
				break;
			}
		}
	}

	public void collision() {
		for (Terrain terrain : circuit.getCollisionTerrains(voiture.getBounds())) {
			if (terrain instanceof Mur) {
				voiture.initPosition(50, 550);
			} else {
				voiture.setSpeedDecreaseCoef(terrain.getSpeedDecreaseCoef());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		currentTime += frame/1000;
		voiture.accelerate(frame);

		if (voiture.isRotate()) {
			voiture.turn();
		}
		collision();
		voiture.rotate(frame);
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
		if (key == KeyEvent.VK_RIGHT) {
			voiture.setRotateDirection(1);
			voiture.setRotate(true);
		}
		if (key == KeyEvent.VK_LEFT) {
			voiture.setRotateDirection(-1);
			voiture.setRotate(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		int key = arg0.getKeyCode();
		
		
		if (key == KeyEvent.VK_R) { 
			voiture.initPosition(50,550);
			nombreCoup += 10;

		} else if (key == KeyEvent.VK_ESCAPE) {
			@SuppressWarnings("unused")
			MenuMain f = new MenuMain();
			timer.stop();
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
