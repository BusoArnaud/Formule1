package tm2DGame.terrain;

import java.awt.Image;
import java.awt.Rectangle;

import tm2D.Constants;

public abstract class Terrain implements Constants {

  int x, y;
	Image image;
	
	protected static double speedDecreaseCoef = 1d;

	public Terrain(int startX, int startY) {
			x = startX;
			y = startY;
		}

	public Rectangle getBounds() {
		Rectangle Box = new Rectangle(x, y, 10, 10);
		return Box;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}
	
	public double getSpeedDecreaseCoef() {
	  return speedDecreaseCoef;
	}
	
	 @Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + x;
	    result = prime * result + y;
	    return result;
	  }

	  @Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    Terrain other = (Terrain) obj;
	    return x == other.x && y == other.y;
	  }
	
	
}
