package tm2DGame;

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
	
	private Terrain[][] circuitMatrix = new Terrain[width][height];

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
	
	public Terrain[][] getMatrix(){
		return this.circuitMatrix;
	}
	
	public int getTileDistance(Terrain terrain1, Terrain terrain2) {
		if(terrain1.equals(terrain2)) {
			return 0;
		}
		if(terrain1.isBlock()) {
			return -1;
		}
		int diffX = terrain2.getX() - terrain1.getX();
		diffX = diffX != 0 ? diffX / Math.abs(diffX) : 0;
		int diffY = terrain2.getY() - terrain1.getY();
		diffY = diffY != 0 ? diffY / Math.abs(diffY) : 0;
		int distance = getTileDistance(circuitMatrix[terrain1.getX()/10 + diffX][terrain1.getY()/10 + diffY], terrain2);
		return distance< 0 ? -1 : 1 + distance;  
		
	}

}
