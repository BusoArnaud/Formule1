package tm2D;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tm2DGame.GameFrame;
import tm2DGame.IPlayer;
import tm2DGame.PlayerCarComponent;
import tm2DGame.RealCarPlayer;
import tm2DGame.CarComponent;

@SuppressWarnings("serial")
public class MenuGame extends JFrame implements ActionListener {

	JButton cmdStart = new JButton("Start");
	List<PlayerPanel> players = new ArrayList<>();

	int playercount;

	public MenuGame(int playercount) {
		super();
		this.playercount = playercount;
		build();
	}

	private void build() {
		this.setTitle("TrackMania 2D");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(esth());
	}

	private JPanel esth() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 10));
		String[] infoString = { "Instructions:",
				"Press arrow up, left, down or right to move",
				"Press R to restart. Press Esc to back Menu" };
		JLabel infoLabel1[] = new JLabel[infoString.length];
		for (int i = 0; i < infoLabel1.length; i++) {
			panel.add(infoLabel1[i] = new JLabel(infoString[i]));
		}

		panel.add(playersPanel());

		panel.add(cmdStart);
		cmdStart.addActionListener(this);
		panel.setBackground(Color.cyan);
		return panel;
	}

	private JPanel playersPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		for (int i = 0; i < playercount; i++) {
			PlayerPanel playerPanel = new PlayerPanel(i+1);
			players.add(playerPanel);
			panel.add(playerPanel);
		}

		return panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cmdStart) {
			List<IPlayer> cars=  players.stream()
				.map(PlayerPanel::getCar)
				.map(PlayerCarComponent::new)
				.map(car -> new RealCarPlayer(car)).collect(Collectors.toList());
			@SuppressWarnings("unused")
			GameFrame f = new GameFrame(cars);
			dispose();
		}

	}

}
