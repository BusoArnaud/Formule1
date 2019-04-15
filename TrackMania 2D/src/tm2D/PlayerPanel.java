package tm2D;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import tm2DGame.CarFactory;
import tm2DGame.Voiture;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel implements ActionListener {

	JButton cmdStart = new JButton("Start");
	Map<Voiture,JButton> cars = new HashMap<>();

	Voiture car;
	Voiture car1;
	Voiture car2 ;
	Voiture car3;
	int playercount;

	private boolean ia;
	
	public PlayerPanel(int playerNum) {
		super();
		this.cars = new HashMap<>();
		this.car1 = CarFactory.BuildCar(CarFactory.CAR_0);
		this.car2 = CarFactory.BuildCar(CarFactory.CAR_1);
		this.car3 = CarFactory.BuildCar(CarFactory.CAR_2);
		this.car = car1;
		build(playerNum);
	}

	/**
	 * @return the car
	 */
	public Voiture getCar() {
		return car;
	}
	
	public boolean isIA() {
	  return this.ia;
	}
	private void build(int playerNum) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setVisible(true);
		cars.put(car1, new JButton(car1.getiVoiture()));
		cars.put(car2, new JButton(car2.getiVoiture()));
		cars.put(car3, new JButton(car3.getiVoiture()));

		this.add(new JLabel("player " + playerNum));
		this.add(car(car1, true));
		this.add(car(car2, false));
		this.add(car(car3, false));
		JCheckBox iaCheckbox = new JCheckBox("IA selected");
		iaCheckbox.addActionListener(this);
		this.add(iaCheckbox);
		this.setBackground(Color.cyan);
	}
	
	private JPanel car(Voiture car, boolean hasBorder) {
		JPanel panel = new JPanel();
		JButton carButton =cars.get(car);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		carButton.addActionListener(this);
		if(hasBorder){
			carButton.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		}else{
			carButton.setBorder(BorderFactory.createLineBorder(Color.cyan, 3));
		}
		panel.add(carButton);
		panel.add(barPanel(car));
		panel.setBackground(Color.cyan);
		return panel;
	}

	private JPanel barPanel(Voiture car){
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JProgressBar speedBar = new JProgressBar(0, 10);
		speedBar.setValue(car.getMaxSpeed());
		speedBar.setForeground(Color.RED);
		speedBar.setStringPainted(true);
		speedBar.setString("Speed              ");

		JProgressBar accelerationBar = new JProgressBar(0, 10);
		accelerationBar.setValue((int) (car.getAccelerationRate() * 10));
		accelerationBar.setForeground(new Color(85, 107, 47));
		accelerationBar.setStringPainted(true);
		accelerationBar.setString("Acceleration      ");

		JProgressBar rotationBar = new JProgressBar(0, 10);
		rotationBar.setValue(Math.abs(car.getRotation() - 25));
		rotationBar.setForeground(Color.BLUE);
		rotationBar.setStringPainted(true);
		rotationBar.setString("Handling          ");

		panel.add(speedBar);
		panel.add(accelerationBar);
		panel.add(rotationBar);
		panel.setBackground(Color.cyan);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (cars.get(car1).equals(arg0.getSource()) ) {
			cars.entrySet().forEach(entry -> entry.getValue().setBorder(BorderFactory.createLineBorder(Color.cyan, 3)));
			((JButton) arg0.getSource()).setBorder(BorderFactory.createLineBorder(Color.red, 3));
			car = car1;
		}
		if (cars.get(car2).equals(arg0.getSource())) {
			cars.entrySet().forEach(entry -> entry.getValue().setBorder(BorderFactory.createLineBorder(Color.cyan, 3)));
			((JButton) arg0.getSource()).setBorder(BorderFactory.createLineBorder(Color.red, 3));
			car = car2;
		}
		if (cars.get(car3).equals(arg0.getSource())) {
			cars.entrySet().forEach(entry -> entry.getValue().setBorder(BorderFactory.createLineBorder(Color.cyan, 3)));
			((JButton) arg0.getSource()).setBorder(BorderFactory.createLineBorder(Color.red, 3));
			car = car3;
		}
		this.ia = arg0.getSource() instanceof JCheckBox && ((JCheckBox)arg0.getSource()).isSelected();
	}

}
