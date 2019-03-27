package tm2D;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tm2DGame.CarFactory;
import tm2DGame.GameFrame;
import tm2DGame.Voiture;

@SuppressWarnings("serial")
public class MenuGame extends JFrame implements ActionListener {

	JButton cmdStart = new JButton("Start");
	JButton car0 = new JButton(CarFactory.CAR_0.getImageIcon());
	JButton car1 = new JButton(CarFactory.CAR_1.getImageIcon());
	JButton car2 = new JButton(CarFactory.CAR_2.getImageIcon());

	Voiture car = CarFactory.CAR_0;

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
		String[] infoString = { "Instructions:",
				"Press arrow up, left,",
				"down or right to move",
				"Press R to restart (+10)",
				"Press Esc to back Menu" };
		JLabel infoLabel1[] = new JLabel[infoString.length];
		for (int i = 0; i < infoLabel1.length; i++) {
			panel.add(infoLabel1[i] = new JLabel(infoString[i]));
		}
		panel.add(cars());

		panel.add(cmdStart);
		cmdStart.addActionListener(this);
		panel.setBackground(Color.cyan);
		return panel;
	}
	
	private JPanel cars() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		car0.addActionListener(this);
		car1.addActionListener(this);
		car2.addActionListener(this);
		car0.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		car1.setBorder(null);
		car2.setBorder(null);

		panel.add(car0);
		panel.add(car1);
		panel.add(car2);

		panel.setBackground(Color.cyan);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cmdStart) {
			@SuppressWarnings("unused")
			GameFrame f = new GameFrame(car);
			dispose();
		}
		if (arg0.getSource().equals(car0)) {
			car0.setBorder(BorderFactory.createLineBorder(Color.red, 3));
			car1.setBorder(null);
			car2.setBorder(null);
			car = CarFactory.CAR_0;
		}
		if (arg0.getSource().equals(car1)) {
			car1.setBorder(BorderFactory.createLineBorder(Color.red, 3));
			car0.setBorder(null);
			car2.setBorder(null);
			car = CarFactory.CAR_1;
		}
		if (arg0.getSource().equals(car2)) {
			car2.setBorder(BorderFactory.createLineBorder(Color.red, 3));
			car0.setBorder(null);
			car1.setBorder(null);
			car = CarFactory.CAR_2;
		}

	}

}
