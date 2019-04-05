package tm2DGame.terrain;


public class Phatom extends Terrain{

	public Phatom(int startX, int startY) {
		super(startX, startY);
	}
	
	@Override
	public double getSpeedDecreaseCoef() {
	  return GRASS_SPEED_COEF; 
	}

}
