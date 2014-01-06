package tm2DEditor;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class EditorFrame extends JFrame {

	public EditorFrame() {
		this.setTitle("Track Constructor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(806, 628);
		this.add(new EditorBoard());
		this.setVisible(true);
		this.setResizable(false);
	}

}
