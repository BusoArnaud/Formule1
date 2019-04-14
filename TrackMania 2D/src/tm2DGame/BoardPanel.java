package tm2DGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.Timer;

import ia.ga.impl.car.KeyEventGame;
import tm2D.Constants;
import tm2D.MenuEnd;
import tm2D.MenuMain;
import tm2DGame.boards.RealGameBoard;
import tm2DGame.terrain.Terrain;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel implements KeyListener, ActionListener, Constants {
	int frame = 40;
	Timer timer = new Timer(frame, this);
	double currentTime = 0;

	int level = 1;
	int nombreCoup = 0;

	public static int nombreCoupT;

	Font levelFont = new Font("SansSerif", Font.BOLD, 15);
	Frame gFrame;

	boolean showAstar = false;

	private RealGameBoard gameBoard;

	Queue<KeyEventGame> actions;

	public BoardPanel(Frame gF, RealGameBoard gameBoard) {

		this.gameBoard = gameBoard;
		setFocusable(true);
		addKeyListener(this);
		repaint();
		timer.start();
	}

	private void paintCircuit(Graphics2D g2d) {
		Terrain[][] matrix = gameBoard.getCircuit().getMatrix();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				g2d.drawImage(matrix[i][j].getImage(), matrix[i][j].getX(), matrix[i][j].getY(), null);
			}
		}
	}

	private void paintAstar(Graphics g) {
		gameBoard.getAstarPath().stream().forEach(terr -> g.drawRect(terr.getX(), terr.getY(), 10, 10));
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D circuit2D = (Graphics2D) g.create();

		paintCircuit(circuit2D);
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
			paintAstar(g);
		}
		for (CarComponent car : gameBoard.getCars()) {
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.setColor(Color.black);
			g2d.rotate(car.getCurrentAngle(), car.getpX(), car.getpY());
			g2d.drawImage(car.getImage(), car.getImageX(), car.getImageY(), null);

			g2d.dispose();
			// debugCollision(g, car);
		}
	}

	@SuppressWarnings("unused")
	private void debugCollision(Graphics g, IPlayer player) {
		Graphics2D g2d2 = (Graphics2D) g.create();
		Path2D a1 = player.getCar().getArea();
		g2d2.setColor(Color.RED);
		g2d2.fill(a1);

		g2d2.dispose();
	}

	public void nextTrack() {
		level++;
		if (level == 4) {
			@SuppressWarnings("unused")
			MenuEnd f = new MenuEnd(currentTime);
			timer.stop();
		} else {
			gameBoard.loadTrack();
		}
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		currentTime += frame + .0;

		if (gameBoard.advance(frame)) {
			nextTrack();
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		gameBoard.getCars().stream().filter(car -> car instanceof PlayerCarComponent)
				.forEach(car -> ((PlayerCarComponent) car).keyPressed(key));
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		int key = arg0.getKeyCode();
		if (key == KeyEvent.VK_R) {
			gameBoard.getCars().forEach(CarComponent::initPosition);
			gameBoard.getPlayers().stream().filter(player -> player instanceof IaCarPlayer)
					.forEach(player -> ((IaCarPlayer) player).init(gameBoard, frame));
			nombreCoup += 10;

		} else if (key == KeyEvent.VK_ESCAPE) {
			@SuppressWarnings("unused")
			MenuMain f = new MenuMain();
			timer.stop();
			gFrame.dispose();
		} else if (key == KeyEvent.VK_I) {
			showAstar = !showAstar;
		}
		gameBoard.getCars().stream().filter(car -> car instanceof PlayerCarComponent)
				.forEach(car -> ((PlayerCarComponent) car).keyReleased(key));

		repaint();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
