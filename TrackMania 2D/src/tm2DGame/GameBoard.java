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
public class GameBoard extends JPanel implements KeyListener, ActionListener, Constants {
	int frame = 40;
	Timer timer = new Timer(frame, this);
	double currentTime=0;

	int level = 1;
	int nombreCoup = 0;
	
	public static int nombreCoupT;

	private Circuit circuit;
	
	CarComponent voiture1;
	CarComponent voiture2;
	List<CarComponent> cars;
	
	Font levelFont = new Font("SansSerif", Font.BOLD, 15);
	Frame gFrame;
	
	List<Terrain> path;

	public GameBoard(Frame gF, List<CarComponent> playercars) {
		
		this.cars = new ArrayList<>();
		this.voiture1 = playercars.get(0);
		this.voiture1.initPosition(55, 550);
		this.voiture1.setKeys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
		this.cars.add(this.voiture1);

		if(playercars.size() ==2){
			this.voiture2 = playercars.get(1);
			this.voiture2.initPosition(40, 540);
			this.voiture2.setKeys(KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_D);
			this.cars.add(voiture2);
		}

		loadTrack();

		gFrame = gF;
		setFocusable(true);
		addKeyListener(this);
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
		Graphics2D circuit2D = (Graphics2D) g.create();

		circuit.paint(circuit2D);
		circuit2D.dispose();
		g.setColor(Color.BLACK);
		g.setFont(levelFont);
		StringBuilder legends = new StringBuilder();
		legends.append("Level : ");
		legends.append(level);
		legends.append("|| time : " );
		legends.append(Math.floor(currentTime));
		
		g.drawString(legends.toString(), 15, 585);
		g.setColor(Color.cyan);
		path.stream().forEach(terr->g.drawRect(terr.getX(), terr.getY(), 10, 10));
		for(CarComponent car :cars){
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(Color.black);	
			g2d.rotate(car.getCurrentAngle(), car.getpX(), car.getpY());
			g2d.drawImage(car.getImage(), car.getImageX(), car.getImageY(), null);
			g2d.dispose();
		}
	}

	public void nextTrack() {

		for(CarComponent car :cars){
			Rectangle voitureRec;
			voitureRec = car.getBounds();

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
	}

	public void collision() {
		cars.forEach(car -> {
			for (Terrain terrain : circuit.getCollisionTerrains(car.getBounds())) {
				if (terrain instanceof Mur) {
					car.initPosition();
				} else {
					car.setSpeedDecreaseCoef(terrain.getSpeedDecreaseCoef());
				}
			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		currentTime += frame/1000;
		cars.forEach(car -> {
			car.accelerate(frame);

			if (car.isRotate()) {
				car.turn();
			}
		});
		collision();
		cars.forEach(car -> {
			car.rotate(frame);
			car.move();
			car.position();
		});
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		cars.forEach(car -> {
			if (key == car.getKeyUp()) {
				car.setDirection(1);
				car.setAccelerate(true);
			}
			if (key == car.getKeyDown()) {
				car.setDirection(-1);
				car.setAccelerate(true);
			}
			if (key == car.getKeyRight()) {
				car.setRotateDirection(1);
				car.setRotate(true);
			}
			if (key == car.getKeyLeft()) {
				car.setRotateDirection(-1);
				car.setRotate(true);
			}

		});
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		int key = arg0.getKeyCode();
		
		
		if (key == KeyEvent.VK_R) { 
			cars.forEach(CarComponent::initPosition);
			nombreCoup += 10;

		} else if (key == KeyEvent.VK_ESCAPE) {
			@SuppressWarnings("unused")
			MenuMain f = new MenuMain();
			timer.stop();
			gFrame.dispose();
		}
		cars.forEach(car -> {
			if (key == car.getKeyUp() || key == car.getKeyDown()) {
				car.setAccelerate(false);
			}
			if (key == car.getKeyRight() || key == car.getKeyLeft()) {
				car.setRotate(false);
			}
		});
		

		repaint();
		nextTrack();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
