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
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.io.Console;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.Timer;

import ia.ga.impl.CarSubject.Solution;
import ia.ga.impl.car.CircuitSolution;
import ia.ga.impl.car.KeyEventGame;
import ia.subject.GeneComplex;
import pathfinding.Astar;
import tm2D.Constants;
import tm2D.MenuEnd;
import tm2D.MenuMain;
import tm2DGame.terrain.Mur;
import tm2DGame.terrain.Terrain;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements KeyListener, ActionListener, MouseListener, Constants {
	int frame = 40;
	public Timer timer = new Timer(frame, this);
	double currentTime = 0;
	
	protected int level = 1;
	int nombreCoup = 0;
	
	public static int nombreCoupT;
	
<<<<<<< HEAD
	PlayerCarComponent voiture1;
	PlayerCarComponent voiture2;
	List<PlayerCarComponent> cars;
=======
	protected Circuit circuit;
	
	protected IPlayer voiture1;
	IPlayer voiture2;
	protected List<IPlayer> cars;
	protected Solution solution;
	protected List<GeneComplex> solutions = null;
	CircuitSolution circuitSolution; 
	protected int count = 0;
	boolean simulate = false;
>>>>>>> solution implementation with multiple track for training, fitness not relevant
	
	Font levelFont = new Font("SansSerif", Font.BOLD, 15);
	Frame gFrame;
	protected List<Terrain> path;
	boolean showAstar = false;
	
	protected String trackName = "simu";
	private int levelMax = 8;
	
	public GameBoard(Frame gF, List<IPlayer> playercars, boolean simulate) {
		addMouseListener(this);
		this.simulate = simulate;
		this.cars = new ArrayList<>();
<<<<<<< HEAD
		this.voiture1 = (PlayerCarComponent) playercars.get(0);
		this.voiture1.initPosition(55, 550);
		this.voiture1.setKeys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
		this.cars.add(this.voiture1);

		if (playercars.size() == 2) {
			this.voiture2 = (PlayerCarComponent)  playercars.get(1);
			this.voiture2.initPosition(40, 540);
=======
		this.voiture1 = playercars.get(0);
		// this.voiture1.initPosition(55, 550);
		this.voiture1.initPosition(390, 280);
		this.voiture1.setKeys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
		this.cars.add(this.voiture1);

		if (playercars.size() == 2 && !simulate) {
			this.voiture2 = playercars.get(1);
			//this.voiture2.initPosition(40, 540);
>>>>>>> solution implementation with multiple track for training, fitness not relevant
			this.voiture2.setKeys(KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_D);
			this.cars.add(voiture2);
		}

		init(gF);
	}

	protected void init(Frame gF) {

		loadTrack();
		if (simulate) {
			solution = new Solution(this);
			circuitSolution = new CircuitSolution(this);
		}
		gFrame = gF;
		setFocusable(true);
		addKeyListener(this);
		timer.start();

		if (this.simulate) {
			playSimulation();
		}

	}

	public void loadTrack() {
<<<<<<< HEAD
		this.cars.forEach(CarComponent::initPosition);
		try {
			FileReader fr = new FileReader(RELATIVE_PATH_TRACKS + "Track" + level);
=======
		this.cars.forEach(IPlayer::initPosition);
		try {
			FileReader fr = new FileReader(RELATIVE_PATH_TRACKS + trackName + level);
>>>>>>> solution implementation with multiple track for training, fitness not relevant

			circuit = new Circuit(fr, 39, 28);
			path = new Astar(circuit).call();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		repaint();
	}

	public void loadTrack(String name) {
		this.cars.forEach(IPlayer::initPosition);
		try {
			FileReader fr = new FileReader(RELATIVE_PATH_TRACKS + name);

			circuit = new Circuit(fr, 39, 28);
			path = new Astar(circuit).call();
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

		//circuit.paint(circuit2D);
		circuit2D.dispose();
		g.setColor(Color.BLACK);
		g.setFont(levelFont);
		StringBuilder legends = new StringBuilder();
		legends.append("Level : ");
		legends.append(level);
		legends.append("|| time : ");
		legends.append(Math.floor(currentTime / 1000));

		g.drawString(legends.toString(), 15, 585);
		g.setColor(Color.cyan);
		if (showAstar) {
			path.stream().forEach(terr -> g.drawRect(terr.getX(), terr.getY(), 10, 10));
		}
		for (CarComponent car : cars) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(Color.black);
			g2d.rotate(car.getCurrentAngle(), car.getpX(), car.getpY());
			g2d.drawImage(car.getImage(), car.getImageX(), car.getImageY(), null);

			g2d.dispose();
			//debugCollision(g, car);
		}
	}

	private void debugCollision(Graphics g, CarComponent car) {
		Graphics2D g2d2 = (Graphics2D) g.create();
		Path2D a1 = car.getArea();
		g2d2.setColor(Color.RED);
		g2d2.fill(a1);

		g2d2.dispose();
	}

	public void nextTrack() {

		for (CarComponent car : cars) {
			Rectangle voitureRec;
			voitureRec = car.getBounds();

			for (Terrain terrain : circuit.getEndTerrains()) {
				if (voitureRec.intersects(terrain.getBounds())) {
					level++;
					if (level >= levelMax) {
						@SuppressWarnings("unused")
						MenuEnd f = new MenuEnd(currentTime);
						gFrame.dispose();
						timer.stop();
					} else {
						loadTrack();
					}
					break;
				}
			}
		}
	}

	public void collision() {
		cars.forEach(car -> {
			List<Double> speedCoefs = new ArrayList<>();
			List<Terrain> collisions = circuit.getCollisionTerrains(car);
			for (Terrain terrain : collisions) {
				if (terrain instanceof Mur) {
					car.initPosition();
				} else {
					speedCoefs.add(terrain.getSpeedDecreaseCoef());
				}
			}
			if (collisions.size() != 0) {
				double speedCoef = speedCoefs.stream().mapToDouble(d -> d.doubleValue()).sum() / speedCoefs.size();
				//System.out.println(speedCoef);
				car.setSpeedDecreaseCoef(speedCoef);
			}
		});
	}
//for testing the ia. To REMOVE ONCE IA IMPLEMENTED
//	int count = 0;
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		
		if(this.simulate){
			// playSimulation();
		}

		cars.forEach(car -> {
			car.accelerate(frame);

			if (car.isRotate()) {
				car.turn();
			}
		});
		cars.forEach(car -> {
			car.rotate(frame);
			car.move();
			car.position();
			car.calculateArea();
		});
		
		collision();
		nextTrack();
		repaint();
	}

	protected void playSimulation() {
		if (count == 0) {
			LinkedList<KeyEventGame> actions = null;
			if (actions == null || actions.isEmpty()) {

				try {
					long start = System.currentTimeMillis();
					solutions = solution.call();
					actions = new LinkedList<>(solutions.stream().map(GeneComplex::getKey).collect(Collectors.toList()));
					// actions = new LinkedList<>(circuitSolution.call().subList(0, 3));
					System.out.println(System.currentTimeMillis() - start);
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException
						| NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			actions.poll().getCarBehavior().accept(voiture1);
			// astar.setCarTerrain(circuit.getTerrain(voiture1.getpX(), voiture1.getpY()));
			// new Thread(()-> path = astar.call()).start();
		}

		count = (count + 1) % 10;

		currentTime += frame + .0;

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		cars.forEach(car -> {
			car.keyPressed(key);
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
		} else if (key == KeyEvent.VK_I) {
			showAstar = !showAstar;
		}
		cars.forEach(car -> {
			car.keyReleased(key);
		});

		repaint();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
  public List<Terrain> getAstarPath() {
    return this.path;
  }

  public CarComponent getVoiture() {
    return this.voiture1;
  }

  public Circuit getCircuit() {
    return this.circuit;
  }

	@Override
	public void mouseClicked(MouseEvent e) {
		CarComponent car = cars.get(0);
		double targetX = (e.getX() - car.getpX());
		double targetY = (e.getY() - car.getpY());
		float angle = (float) Math.toDegrees(Math.atan2(targetY - 0, targetX - 1));
		float carAngle = (float) Math.toDegrees(car.currentAngle);
		double newradian = Math.toRadians(( angle - carAngle)%360) +Math.PI/2;
		System.out.println("newradian = " + newradian);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
