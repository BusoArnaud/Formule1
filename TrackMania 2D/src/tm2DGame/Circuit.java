package tm2DGame;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import tm2DGame.terrain.Terrain;
import tm2DGame.terrain.TerrainBuilder;

public class Circuit {
	
	private int width = 80;
	
	private int height = 60;
	
	Terrain[][] circuitMatrix = new Terrain[width][height];

	private final List<Terrain> endTerrains = new ArrayList<>();

	private Terrain startPoint;

	public Circuit(FileReader fr) {
		int x = 0;
		int y = 0;
		int i = 0;
		try {
			while ((i = fr.read()) != -1) {
				char txt = (char) i;
				TerrainBuilder terrainBuilder = new TerrainBuilder(txt, x, y);
				if (terrainBuilder.isTerrain()) {
					circuitMatrix[x][y] = terrainBuilder.getTerrain();
					if (terrainBuilder.isEnd()) {
						endTerrains.add(circuitMatrix[x][y]);
					}
				}

				if (txt == '\r' || txt == '\n') {
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
			System.out.println(ex);
		}
	}

	public void paint(Graphics2D g2d) {
		for (int i = 0; i < circuitMatrix.length; i++) {
			for (int j = 0; j < circuitMatrix[i].length; j++) {
				g2d.drawImage(circuitMatrix[i][j].getImage(), circuitMatrix[i][j].getX(), circuitMatrix[i][j].getY(),
						null);
			}
		}
	}

	public List<Terrain> getEndTerrains() {
		return Collections.unmodifiableList(endTerrains);
	}

	public Terrain getStart() {
		return circuitMatrix[5][55];
	}
	
	public List<Terrain> getCollisionTerrains(CarComponent car) {
		final List<Terrain> collisionTerrains = new LinkedList<>();
		Rectangle bounds = car.getBounds();
		for (int i = Math.max((int) bounds.getMinX() / 10, 0); i < Math.min(bounds.getMaxX() / 10,
				circuitMatrix.length); i++) {
			for (int j = Math.max((int) bounds.getMinY() / 10, 0); j < Math.min(bounds.getMaxY() / 10,
					circuitMatrix[i].length); j++) {
				Rectangle rec = circuitMatrix[i][j].getBounds();
				if (car.getArea().intersects(rec)) {
					collisionTerrains.add(circuitMatrix[i][j]);
				}
			}
		}
		return collisionTerrains;
	}
	
	public List<Terrain> getAdjacentTiles(Terrain terrain, int rayon) {
		int matrixX = terrain.getX() / 10;

		int matrixY = terrain.getY() / 10;

		List<Terrain> adjacents = new LinkedList<>();
		for (int i = Math.max(matrixX - rayon, 0); i < Math.min(matrixX + rayon, circuitMatrix.length); i++) {
			for (int j = Math.max(matrixY - rayon, 0); j < Math.min(matrixY + rayon, circuitMatrix[i].length); j++) {
				adjacents.add(circuitMatrix[i][j]);
			}
		}
		return adjacents;
	}
	
	public List<Terrain> getAdjacentTiles(Terrain terrain) {
		List<Terrain> adjacents = new ArrayList<>();
		//up row
		int matrixX = terrain.getX()/10;
		
		int matrixY = terrain.getY()/10;
		
		int newY = matrixY - 1;
		if(newY >= 0) {
			adjacents.add(circuitMatrix[matrixX][newY]);
		}
		
		newY = matrixY + 1;
		if(newY < height) {
			adjacents.add(circuitMatrix[matrixX][newY]);
		}
		
		if(matrixX - 1 >= 0) {
			adjacents.add(circuitMatrix[matrixX - 1][matrixY]);
		}
		if(matrixX + 1 < width) {
			adjacents.add(circuitMatrix[matrixX + 1][matrixY]);
		}

		return adjacents;
	}

	public Terrain getTerrain(double x, double y) {
		return circuitMatrix[(int) x / 10][(int) y / 10];
	}

}
