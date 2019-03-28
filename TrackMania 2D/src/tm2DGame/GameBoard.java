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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.io.FileReader;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import pathfinding.Astar;
import tm2D.Constants;
import tm2D.MenuEnd;
import tm2D.MenuMain;
import tm2DGame.terrain.Mur;
import tm2DGame.terrain.Terrain;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements KeyListener, ActionListener, MouseMotionListener, Constants {
	
	Timer timer = new Timer(100, this);
	double currentTime=0;

	int level = 1;
	int nombreCoup = 0;
	
	public static int nombreCoupT;

	private Circuit circuit;
	
	Voiture voiture = new Voiture(50, 550, 15);
	
	Font levelFont = new Font("SansSerif", Font.BOLD, 15);
	Frame gFrame;
	
	List<Terrain> path;

	public GameBoard(Frame gF) {

		loadTrack();

		gFrame = gF;
		setFocusable(true);
		addKeyListener(this);
		addMouseMotionListener(this);
		timer.start();
	}

	public void loadTrack() {

		try {			FileReader fr = new FileReader(RELATIVE_PATH_TRACKS + "Track" + level);

			circuit = new Circuit(fr);
			
			path = new Astar(circuit).runAstar();
		  
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		repaint();
	}
	
	int mouseX = 0;
	int mouseY = 0;
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
		legends.append("|| vitesse : " );
		legends.append(Math.floor(voiture.getSpeed()));
		legends.append("Mouse X: " + mouseX);
		legends.append("Mouse Y: " + mouseY);
		
		g.drawString(legends.toString(), 15, 585);
		g.setColor(Color.cyan);
		path.stream().forEach(terr->g.drawRect(terr.getX(), terr.getY(), 10, 10));
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
				voiture.reset(50, 550);
			} else if (terrain.getSpeedDecreaseCoef() < 1d) {
				voiture.speedDecrease(terrain.getSpeedDecreaseCoef());
			}
		}
	}

	@Override
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

  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
  }

}
