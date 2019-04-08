package tm2DEditor;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class EditorFrame extends JFrame {

	public EditorFrame() {
		this.setTitle("TrakcMania 2D");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 635);
		this.add(new EditorBoard(this));
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

}
