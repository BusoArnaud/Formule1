package tm2DGame.terrain;

import java.awt.Image;
import java.awt.Rectangle;

import tm2D.Constants;

public abstract class Terrain implements Constants {

  int x, y;
	Image image;
	
	//un terrain ne bouge pas. on n'a pas besoin de redéfinir un nouveau rectangle à chaque 
	//fois
	final Rectangle rectangle;
	
	protected static double speedDecreaseCoef = 1d;

	public Terrain(int startX, int startY) {
			x = startX;
			y = startY;
			rectangle = new Rectangle(x, y, 10, 10);
		}

	public Rectangle getBounds() {
		return rectangle;
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
