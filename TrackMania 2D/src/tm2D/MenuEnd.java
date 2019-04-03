package tm2D;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tm2DGame.GameBoard;

@SuppressWarnings("serial")
public class MenuEnd extends JFrame implements ActionListener {

	JButton cmdEnd = new JButton("Revenir au Menu Principal");

	public MenuEnd(double time) {
		super();
		build(time);
	}

	private void build( double time) {
		this.setTitle("TrackMania 2D");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(esth(time));
	}

	private JPanel esth(double time) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JLabel label = new JLabel("Final Score : " + time/1000 + "s");
		panel.add(label);

		panel.add(cmdEnd);

		cmdEnd.addActionListener(this);

		return panel;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cmdEnd) {
			@SuppressWarnings("unused")
			MenuMain f = new MenuMain();
			dispose();

		}

	}

}
