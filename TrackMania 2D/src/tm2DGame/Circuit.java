package tm2DGame;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
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
	
	public List<Terrain> getCollisionTerrains(Rectangle car) {
		final List<Terrain> collisionTerrains = new ArrayList<>();
		for (int i = (int) car.getMinX() / 10; i < car.getMaxX() / 10; i++) {
			for (int j = (int) car.getMinY() / 10; j < car.getMaxY() / 10; j++) {
				if (car.intersects(circuitMatrix[i][j].getBounds())) {
					collisionTerrains.add(circuitMatrix[i][j]);
				}
			}
		}
		return collisionTerrains;
	}
	
	public List<Terrain> getAdjacentTiles(Terrain terrain) {
		List<Terrain> adjacents = new ArrayList<>();
		//up row
		int matrixX = terrain.getX()/10;
		
		int matrixY = terrain.getY()/10;
		
		int newY = matrixY - 1;
		if(newY >= 0) {
//			if(matrixX - 1 >= 0) {
//				adjacents.add(circuitMatrix[(matrixX - 1)/1][newY]);
//			}
//			if(matrixX + 1 < height) {
//				adjacents.add(circuitMatrix[matrixX + 1][newY]);
//			}
			adjacents.add(circuitMatrix[matrixX][newY]);
		}
		
		newY = matrixY + 1;
		if(newY < height) {
//			if(matrixX - 1 >= 0) {
//				adjacents.add(circuitMatrix[matrixX - 1][newY]);
//			}
//			if(matrixX + 1 < width) {
//				adjacents.add(circuitMatrix[matrixX + 1][newY]);
//			}
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

}
