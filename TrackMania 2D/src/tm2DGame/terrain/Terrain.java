package tm2DGame.terrain;

import java.awt.Image;
import java.awt.Rectangle;

import tm2D.Constants;

public abstract class Terrain implements Constants {

	int x, y;
	protected boolean end;
	protected boolean block;
	Image image;

	final Rectangle rectangle;

	protected static final double NORMAL_SPEED_COEF = 1d;

	protected static final double SAND_SPEED_COEF = 0.3;

	protected static final double WATER_SPEED_COEF = 0.8;

	protected static final double GRASS_SPEED_COEF = 0.6;

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
		return NORMAL_SPEED_COEF;
	}
	public boolean isBlock() {
		return this.block;
	}
	
	public boolean isEnd() {
		return this.end;
	}
	
	public double getDistance(Terrain endPoint) {
		return Math.abs(this.getBounds().getCenterX()
				- endPoint.getBounds().getCenterX())
				+ Math.abs(this.getBounds().getCenterY()
				- endPoint.getBounds().getCenterY());
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
	  
	  @Override
    public String toString() {
	    return "X:" + x + " Y:" + y;
    }
	
}
