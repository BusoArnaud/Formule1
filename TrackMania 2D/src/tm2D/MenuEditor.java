package tm2D;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tm2DEditor.EditorFrame;

@SuppressWarnings("serial")
public class MenuEditor extends JFrame implements ActionListener {

	JButton cmdDraw = new JButton("Draw");

	public MenuEditor() {
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

		JLabel infoLabel1[] = new JLabel[8];
		String[] infoString = { "Instructions:",
				"Use the wheel to chose the block",
				"Press left click to put it", "Press right click to remove it",
				"Press S to save (Track + level)",
				"Press L to load your track",
				"Move your new track in folder Tracks",
				"Press Esc to back Menu" };

		for (int i = 0; i < infoLabel1.length; i++) {
			panel.add(infoLabel1[i] = new JLabel(infoString[i]));
		}
		panel.add(cmdDraw);
		cmdDraw.addActionListener(this);
		panel.setBackground(Color.cyan);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cmdDraw) {
			@SuppressWarnings("unused")
			EditorFrame f = new EditorFrame();
			dispose();
		}

	}

}
