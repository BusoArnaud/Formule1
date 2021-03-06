package tm2DEditor;

import java.awt.Frame;
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

import tm2D.Constants;
import tm2D.MenuMain;

@SuppressWarnings("serial")
public class EditorBoard extends JPanel implements MouseListener,
		MouseMotionListener, MouseWheelListener, KeyListener, Constants {

	String editor[][] = new String[80][60];
	String imageSelect[] = { null, "PISTE", "HERBE", "BORDURE", "SABLE", "MUR",
			"EAU", "DAMIER" };
	String imageCourante = null;

	int sX, sY;
	int rInc;

	Image Piste;
	Image Herbe;
	Image Bordure;
	Image Sable;
	Image Mur;
	Image Eau;
	Image Damier;

	FileWriter fw;
	FileReader fr;

	Frame eFrame;

	boolean clicked = false;
	int action = MouseEvent.BUTTON1;

	public EditorBoard(Frame eF) {
		initEditor();
		ImageIcon iPiste = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Piste10.jpg");
		Piste = iPiste.getImage();

		ImageIcon iHerbe = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Herbe10.jpg");
		Herbe = iHerbe.getImage();

		ImageIcon iBordure = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Bordure10.jpg");
		Bordure = iBordure.getImage();

		ImageIcon iSable = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Sable10.jpg");
		Sable = iSable.getImage();

		ImageIcon iMur = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Mur10.jpg");
		Mur = iMur.getImage();

		ImageIcon iEau = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Eau10.jpg");
		Eau = iEau.getImage();

		ImageIcon iDamier = new ImageIcon(RELATIVE_PATH_IMAGE_CIRCUIT + "Damier10.jpg");
		Damier = iDamier.getImage();

		eFrame = eF;
		setFocusable(true);

		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);
	}

	public void initEditor(){
		for (int i = 0; i < 80; i++) {
			for (int j = 0; j < 60; j++) {
				if(i==0 || j == 0 ||  i== 79 || j == 59 ){
					editor[i][j] = "MUR";
				}else{
					editor[i][j] = "HERBE";
				}
			}
		}
	}

	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < 80; i++) {
			for (int j = 0; j < 60; j++) {
				if (editor[i][j] == "PISTE") {
					g2d.drawImage(Piste, i * 10, j * 10, null);
				}
				if (editor[i][j] == "HERBE") {
					g2d.drawImage(Herbe, i * 10, j * 10, null);
				}
				if (editor[i][j] == "BORDURE") {
					g2d.drawImage(Bordure, i * 10, j * 10, null);
				}
				if (editor[i][j] == "SABLE") {
					g2d.drawImage(Sable, i * 10, j * 10, null);
				}
				if (editor[i][j] == "MUR") {
					g2d.drawImage(Mur, i * 10, j * 10, null);
				}
				if (editor[i][j] == "EAU") {
					g2d.drawImage(Eau, i * 10, j * 10, null);
				}
				if (editor[i][j] == "DAMIER") {
					g2d.drawImage(Damier, i * 10, j * 10, null);
				}

			}
		}
		if (imageCourante == "PISTE") {
			g2d.drawImage(Piste, sX, sY, null);
		} else if (imageCourante == "HERBE") {
			g2d.drawImage(Herbe, sX, sY, null);
		} else if (imageCourante == "BORDURE") {
			g2d.drawImage(Bordure, sX, sY, null);
		} else if (imageCourante == "SABLE") {
			g2d.drawImage(Sable, sX, sY, null);
		} else if (imageCourante == "MUR") {
			g2d.drawImage(Mur, sX, sY, null);
		} else if (imageCourante == "EAU") {
			g2d.drawImage(Eau, sX, sY, null);
		} else if (imageCourante == "DAMIER") {
			g2d.drawImage(Damier, sX, sY, null);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int key = arg0.getKeyCode();

		if (key == KeyEvent.VK_S) {
			try {
				fw = new FileWriter(JOptionPane.showInputDialog(null,
						"Track Name", "Track Constructor",
						JOptionPane.QUESTION_MESSAGE));
				for (int j = 0; j < 60; j++) {
					for (int i = 0; i < 80; i++) {
						if (editor[i][j] == "PISTE") {
							fw.write(" ");
						} else if (editor[i][j] == "HERBE") {
							fw.write(".");
						} else if (editor[i][j] == "BORDURE") {
							fw.write("B");
						} else if (editor[i][j] == "SABLE") {
							fw.write("S");
						} else if (editor[i][j] == "MUR") {
							fw.write("x");
						} else if (editor[i][j] == "EAU") {
							fw.write("o");
						} else if (editor[i][j] == "DAMIER") {
							fw.write("D");
						} else if (editor[i][j] == null) {
							fw.write("*");
						}
					}
					fw.write("\r\n");
				}
				fw.close();
			} catch (Exception ex) {
			}
		} else if (key == KeyEvent.VK_L) {

			try {
				fr = new FileReader(JOptionPane.showInputDialog(null,
						"Track Name", "Track Constructor",
						JOptionPane.QUESTION_MESSAGE));

				int i = 0;
				int x = 0;
				int y = 0;

				while ((i = fr.read()) != -1) {
					char txt = (char) i;

					if (txt == ' ') {
						editor[x][y] = "PISTE";
					} else if (txt == '.') {
						editor[x][y] = "HERBE";
					} else if (txt == 'B') {
						editor[x][y] = "BORDURE";
					} else if (txt == 'S') {
						editor[x][y] = "SABLE";
					} else if (txt == 'x') {
						editor[x][y] = "MUR";
					} else if (txt == 'o') {
						editor[x][y] = "EAU";
					} else if (txt == 'D') {
						editor[x][y] = "DAMIER";
					} else if (txt == '*') {
						editor[x][y] = null;
					} else if (txt == '\r' || txt == '\n') {
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
		} else if (key == KeyEvent.VK_ESCAPE) {
			@SuppressWarnings("unused")
			MenuMain f = new MenuMain();
			eFrame.dispose();
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
			if (rInc > 0) {
				rInc--;
			}
		} else if (rot > 0) {
			if (rInc < 7) {
				rInc++;
			}
		}

		imageCourante = imageSelect[rInc];
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (clicked) {
			int x = arg0.getX() / 10;
			int y = arg0.getY() / 10;
			click(x, y, action);
		}

	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		sX = arg0.getX() - 5;
		sY = arg0.getY() - 5;

		if (clicked) {
			int x = arg0.getX() / 10;
			int y = arg0.getY() / 10;
			click(x, y, action);
		}
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
		clicked = true;
		action = arg0.getButton();
		int x = arg0.getX() / 10;
		int y = arg0.getY() / 10;
		click(x, y, action);
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		clicked = false;
	}

	public void click(int x, int y, int action){

		if (action == MouseEvent.BUTTON1) {
			editor[x][y] = imageCourante;
		} else if (action == MouseEvent.BUTTON3) {
			editor[x][y] = null;
		}
		repaint();
	}
}
