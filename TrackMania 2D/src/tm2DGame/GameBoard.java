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
import java.awt.geom.Path2D;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
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
	
	IPlayer player1;
	IPlayer player2;
	List<IPlayer> cars;
	
	Font levelFont = new Font("SansSerif", Font.BOLD, 15);
	Frame gFrame;
	
	private List<Terrain> path;
	
//	private Astar astar;
	
//	Queue<KeyEventGame> actions;
//
//	CircuitSolution gaAlgorithm;

	boolean showAstar = false;

	public GameBoard(Frame gF, List<IPlayer> playercars) {
		this.cars = new ArrayList<>();
		this.player1 = playercars.get(0);
		this.player1.getCar().initPosition(55, 550);
		this.player1.getCar().setKeys(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
		this.cars.add(this.player1);

		if (playercars.size() == 2) {
			this.player2 = playercars.get(1);
			this.player2.getCar().initPosition(40, 540);
			this.player2.getCar().setKeys(KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_D);
			this.cars.add(player2);
		}


		loadTrack();

		gFrame = gF;
		setFocusable(true);
		addKeyListener(this);
		timer.start();
	}

	public void loadTrack() {
		this.cars.stream().map(IPlayer::getCar).forEach(CarComponent::initPosition);
		try {			FileReader fr = new FileReader(RELATIVE_PATH_TRACKS + "Track" + level);

			circuit = new Circuit(fr);
			
			new Thread(() -> {
				path = new Astar(circuit).call();
//				gaAlgorithm = new CircuitSolution(this);
			}).start();
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
		legends.append(Math.floor(currentTime/1000));
		
		g.drawString(legends.toString(), 15, 585);
		g.setColor(Color.cyan);
		if(showAstar){
			path.stream().forEach(terr->g.drawRect(terr.getX(), terr.getY(), 10, 10));
		}
		cars.stream().map(IPlayer::getCar).forEach(car -> {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(Color.black);	
			g2d.rotate(car.getCurrentAngle(), car.getpX(), car.getpY());
			g2d.drawImage(car.getImage(), car.getImageX(), car.getImageY(), null);

			g2d.dispose();
//			debugCollision(g, car);
		});
	}

	private void debugCollision(Graphics g, IPlayer player) {
		Graphics2D g2d2 = (Graphics2D) g.create();
		Path2D a1 = player.getCar().getArea();
		g2d2.setColor(Color.RED);
		g2d2.fill(a1);

		g2d2.dispose();
	}

	public void nextTrack() {
		
		cars.stream().map(IPlayer::getCar).forEach(car -> {
			Rectangle voitureRec;
			voitureRec = car.getBounds();

			for (Terrain terrain : circuit.getEndTerrains()) {
				if (voitureRec.intersects(terrain.getBounds())) {
					level++;
					if (level == 4) {
						@SuppressWarnings("unused")
						MenuEnd f = new MenuEnd(currentTime);
						gFrame.dispose();
					}else{
						loadTrack();
					}
					break;
				}
			}
		});
	}

	public void collision() {
		cars.stream().map(IPlayer::getCar).forEach(car -> {
			List<Double> speedCoefs = new ArrayList<>();
			List<Terrain> collisions = circuit.getCollisionTerrains(car);
			for (Terrain terrain : collisions) {
				if (terrain instanceof Mur) {
					car.initPosition();
				} else {
					speedCoefs.add(terrain.getSpeedDecreaseCoef());
				}
			}
			if(collisions.size() != 0 ){
				double speedCoef = speedCoefs.stream().mapToDouble(d -> d.doubleValue()).sum() / speedCoefs.size();
				car.setSpeedDecreaseCoef(speedCoef);
			}
		});
	}
//for testing the ia. To REMOVE ONCE IA IMPLEMENTED
//	int count = 0;
	
	@Override
	public void actionPerformed(ActionEvent ev) {

//		if (count == 0) {
//			if (actions == null || actions.isEmpty()) {
//
//				try {
//					long start = System.currentTimeMillis();
//					actions = new LinkedList<>(gaAlgorithm.call().subList(0, 3));
//					System.out.println(System.currentTimeMillis() - start);
//				} catch (InstantiationException | IllegalAccessException | InvocationTargetException
//						| NoSuchMethodException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			actions.poll().getCarBehavior().accept(voiture1);
////      astar.setCarTerrain(circuit.getTerrain(voiture1.getpX(), voiture1.getpY()));
//			// new Thread(()-> path = astar.call()).start();
//		}
//
//		count = (count + 1) % 10;

		currentTime += frame + .0;
		cars.stream().map(IPlayer::getCar).forEach(car -> {
			car.accelerate(frame);

			if (car.isRotate()) {
				car.turn();
			}
			car.rotate(frame);
			car.move();
			car.position();
			car.calculateArea();
		});
		
		collision();
		nextTrack();
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		cars.stream().map(IPlayer::getCar).forEach(car -> {
			((PlayerCarComponent)car).keyPressed(key);

		});
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		int key = arg0.getKeyCode();

		if (key == KeyEvent.VK_R) {
			cars.stream().map(IPlayer::getCar).forEach(CarComponent::initPosition);
			nombreCoup += 10;

		} else if (key == KeyEvent.VK_ESCAPE) {
			@SuppressWarnings("unused")
			MenuMain f = new MenuMain();
			timer.stop();
			gFrame.dispose();
		} else if (key == KeyEvent.VK_I) {
			showAstar = !showAstar;
		}
		cars.stream().map(IPlayer::getCar).forEach(car -> {
			((PlayerCarComponent) car).keyReleased(key);
		});

		repaint();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
  public List<Terrain> getAstarPath() {
    return Collections.unmodifiableList(this.path);
  }

  public IPlayer getVoiture() {
    return this.player1;
  }

  public Circuit getCircuit() {
    return this.circuit;
  }

}
