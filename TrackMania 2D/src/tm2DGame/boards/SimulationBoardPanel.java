package tm2DGame.boards;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ia.ga.impl.car.KeyEventGame;
import ia.subject.GeneComplex;
import pathfinding.Astar;
import tm2DGame.Circuit;
import tm2DGame.GameBoard;
import tm2DGame.IPlayer;

public class SimulationBoardPanel extends GameBoard {

	private static final long serialVersionUID = -4854720496457410133L;
	
	
	public SimulationBoardPanel(Frame gF, List<IPlayer> playercars, boolean simulate, String trackName, int level) {
		super(gF, playercars, simulate);
		this.trackName = trackName;
		this.level= level;
		loadTrack();


	}
	@Override
	protected void init(Frame gF) {
		
	};

	@Override
	protected void playSimulation() {
		
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

	}
	@Override
	public void loadTrack() {
		this.cars.forEach(IPlayer::initPosition);
		try {
			FileReader fr = new FileReader(RELATIVE_PATH_TRACKS + trackName + level);

			circuit = new Circuit(fr, 39, 28);
			path = new Astar(circuit).call();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	@Override
	public void paint(Graphics g) {
		
	}

	@Override
	public void actionPerformed(ActionEvent ev) {


	}

}
