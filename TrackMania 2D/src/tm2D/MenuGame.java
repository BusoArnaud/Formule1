package tm2D;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tm2DGame.GameFrame;

@SuppressWarnings("serial")
public class MenuGame extends JFrame implements ActionListener {

	JButton cmdStart = new JButton("Start");

	public MenuGame() {
		super();
		build();
	}

	private void build() {
		this.setTitle("TrackMania 2D");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(esth());
	}

	private JPanel esth() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 10));
		JLabel infoLabel1[] = new JLabel[6];
		String[] infoString = { "Instructions:",
				"Use T, Y, U, G, H, J, V, B, N", "Classical Moves: Y, G, J, B",
				"Other Moves: T, U, H, V, N", "Press R to restart (+10)",
				"Press Esc to back Menu" };

		for (int i = 0; i < infoLabel1.length; i++) {
			panel.add(infoLabel1[i] = new JLabel(infoString[i]));
		}

		panel.add(cmdStart);
		cmdStart.addActionListener(this);
		panel.setBackground(Color.cyan);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cmdStart) {
			@SuppressWarnings("unused")
			GameFrame f = new GameFrame();
			dispose();
		}

	}

}
