package tm2D;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tm2DGame.GameFrame;

public class MenuGame extends JFrame implements ActionListener {
	
	JButton cmdStart = new JButton("Start");

	public MenuGame (){
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
	
	private JPanel esth(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JLabel label = new JLabel(/* ici mettre l'explication des commandes */);
		panel.add(label);
		
		panel.add(cmdStart);
		
		cmdStart.addActionListener(this);
		
		return panel;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == cmdStart){
			GameFrame f = new GameFrame();
			dispose();
		}

	}

}
