package tm2DGame;

import java.util.List;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	public GameFrame(List<IPlayer> cars) {
		this.setTitle("TrackMania 2D");
		this.setSize(800, 635);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new GameBoard(this, cars));
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}
}
