package tm2D;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuMain extends JFrame implements ActionListener {

	JButton cmdGame = new JButton("Let's Play");
	JButton cmdEditor = new JButton("Let's Edit");

	public MenuMain() {
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
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5,100));

		JLabel label = new JLabel("TrackMania 2D");
		panel.add(label);

		panel.add(cmdGame);
		panel.add(cmdEditor);

		cmdGame.addActionListener(this);
		cmdEditor.addActionListener(this);

		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cmdGame) {
			@SuppressWarnings("unused")
			MenuGame f = new MenuGame();
			dispose();
		}
		if (arg0.getSource() == cmdEditor) {
			@SuppressWarnings("unused")
			MenuEditor f = new MenuEditor();
			dispose();

		}

	}

}
