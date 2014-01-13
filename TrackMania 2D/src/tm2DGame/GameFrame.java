package tm2DGame;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	public GameFrame() {
		this.setTitle("TrackMania 2D");
		this.setSize(806, 628);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(new GameBoard());
		this.setVisible(true);
		this.setResizable(false);
		
		// this.setLocationRelativeTo(null);
		
	}
}
