package tm2DEditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EditorBoard extends JPanel implements MouseListener,
		MouseMotionListener, MouseWheelListener, KeyListener {

	String Editor[][] = new String[80][60];
	String ImageSelect[] = { null, "PISTE", "HERBE", "BORDURE", "SABLE", "MUR",
			"EAU", "DAMIER" };
	String ImageCourante = null;
	int Sx, Sy; // Positions souris
	int RInc; // Incrémenter rotation roulette souris
	Image Piste;
	Image Herbe;
	Image Bordure;
	Image Sable;
	Image Mur;
	Image Eau;
	Image Damier;
	FileWriter fw;
	FileReader fr;

	public EditorBoard() {
		ImageIcon iPiste = new ImageIcon("ImagesCircuit/Piste10.jpg");
		Piste = iPiste.getImage();

		ImageIcon iHerbe = new ImageIcon("ImagesCircuit/Herbe10.jpg");
		Herbe = iHerbe.getImage();

		ImageIcon iBordure = new ImageIcon("ImagesCircuit/Bordure10.jpg");
		Bordure = iBordure.getImage();

		ImageIcon iSable = new ImageIcon("ImagesCircuit/Sable10.jpg");
		Sable = iSable.getImage();

		ImageIcon iMur = new ImageIcon("ImagesCircuit/Mur10.jpg");
		Mur = iMur.getImage();

		ImageIcon iEau = new ImageIcon("ImagesCircuit/Eau10.jpg");
		Eau = iEau.getImage();

		ImageIcon iDamier = new ImageIcon("ImagesCircuit/Damier10.jpg");
		Damier = iDamier.getImage();

		setFocusable(true);

		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);

	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < 80; i++) {
			for (int j = 0; j < 60; j++) {
				if (Editor[i][j] == "PISTE") {
					g2d.drawImage(Piste, i * 10, j * 10, null);
				}
				if (Editor[i][j] == "HERBE") {
					g2d.drawImage(Herbe, i * 10, j * 10, null);
				}
				if (Editor[i][j] == "BORDURE") {
					g2d.drawImage(Bordure, i * 10, j * 10, null);
				}
				if (Editor[i][j] == "SABLE") {
					g2d.drawImage(Sable, i * 10, j * 10, null);
				}
				if (Editor[i][j] == "MUR") {
					g2d.drawImage(Mur, i * 10, j * 10, null);
				}
				if (Editor[i][j] == "EAU") {
					g2d.drawImage(Eau, i * 10, j * 10, null);
				}
				if (Editor[i][j] == "DAMIER") {
					g2d.drawImage(Damier, i * 10, j * 10, null);
				}

			}
		}
		if (ImageCourante == "PISTE") {
			g2d.drawImage(Piste, Sx, Sy, null);
		}
		if (ImageCourante == "HERBE") {
			g2d.drawImage(Herbe, Sx, Sy, null);
		}
		if (ImageCourante == "BORDURE") {
			g2d.drawImage(Bordure, Sx, Sy, null);
		}
		if (ImageCourante == "SABLE") {
			g2d.drawImage(Sable, Sx, Sy, null);
		}
		if (ImageCourante == "MUR") {
			g2d.drawImage(Mur, Sx, Sy, null);
		}
		if (ImageCourante == "EAU") {
			g2d.drawImage(Eau, Sx, Sy, null);
		}
		if (ImageCourante == "DAMIER") {
			g2d.drawImage(Damier, Sx, Sy, null);
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		int Key = arg0.getKeyCode();

		if (Key == KeyEvent.VK_S) {
			try {
				fw = new FileWriter(JOptionPane.showInputDialog(null,
						"Track Name", "Track Constructor",
						JOptionPane.QUESTION_MESSAGE));
				for (int i = 0; i < 80; i++) {
					for (int j = 0; j < 60; j++) {
						if (Editor[j][i] == "PISTE") {
							fw.write("+");
						}
						if (Editor[j][i] == "HERBE") {
							fw.write("¤");
						}
						if (Editor[j][i] == "BORDURE") {
							fw.write("b");
						}
						if (Editor[j][i] == "SABLE") {
							fw.write(".");
						}
						if (Editor[j][i] == "MUR") {
							fw.write("#");
						}
						if (Editor[j][i] == "EAU") {
							fw.write("o");
						}
						if (Editor[j][i] == "DAMIER") {
							fw.write("$");
						}
						if (Editor[j][i] == null) {
							fw.write(" ");
						}
					}
					fw.write("\r");
					fw.write("\n");
				}
				fw.close();
			} catch (Exception ex) {

			}
		} else if (Key == KeyEvent.VK_L) {
			try {
				fr = new FileReader(JOptionPane.showInputDialog(null,
						"Track Name", "Track Constructor",
						JOptionPane.QUESTION_MESSAGE));

				int i = 0;
				int x = 0;
				int y = 0;

				while ((i = fr.read()) != -1) {
					char strImg = (char) i;

					if (strImg == '+') {
						Editor[x][y] = "PISTE";
					} else if (strImg == '¤') {
						Editor[x][y] = "HERBE";
					} else if (strImg == 'b') {
						Editor[x][y] = "BORDURE";
					} else if (strImg == '.') {
						Editor[x][y] = "SABLE";
					} else if (strImg == '#') {
						Editor[x][y] = "MUR";
					} else if (strImg == 'o') {
						Editor[x][y] = "EAU";
					} else if (strImg == '$') {
						Editor[x][y] = "DAMIER";
					} else if (strImg == ' ') {
						Editor[x][y] = null;
					} else if (strImg == '\r' || strImg == '\n') {
						x--;
					}
					if (x == 79) {
						y++;
						x = 0;
					} else {
						x++;
					}
				}
			} catch (Exception ex) {

			}
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		int rot = arg0.getWheelRotation();

		if (rot < 0) {
			if (RInc > 0) {
				RInc--;
			}
		} else if (rot > 0) {
			if (RInc < 7) {
				RInc++;
			}
		}

		ImageCourante = ImageSelect[RInc];
		repaint();

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		Sx = arg0.getX() - 5;
		Sy = arg0.getY() - 5;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		int x = arg0.getX() / 10;
		int y = arg0.getY() / 10;

		if (arg0.getButton() == MouseEvent.BUTTON1) {
			Editor[x][y] = ImageCourante;
		} else if (arg0.getButton() == MouseEvent.BUTTON3) {
			Editor[x][y] = null;
		}

	}
}
