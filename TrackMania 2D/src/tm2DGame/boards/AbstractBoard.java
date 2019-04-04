package tm2DGame.boards;

import java.util.List;

import tm2D.Constants;
import tm2DGame.CarComponent;
import tm2DGame.Circuit;
import tm2DGame.Voiture;
import tm2DGame.terrain.Mur;
import tm2DGame.terrain.Terrain;

public abstract class AbstractBoard implements Constants{
  
  protected static final int frame = 40;
  
  protected CarComponent voiture;

  protected Circuit circuit;
  
  protected List<Terrain> astarPath;
  
  int level = 1;
  
  public void advance() {
    voiture.accelerate(frame);

    if (voiture.isRotate()) {
      voiture.turn();
    }
    collision();
    voiture.rotate(frame);
    voiture.move();
    voiture.position();
  }
  
  private void collision() {
    for (Terrain terrain : circuit.getCollisionTerrains(voiture)) {
      if (terrain instanceof Mur) {
        voiture.initPosition(50, 550);
      } else {
        voiture.setSpeedDecreaseCoef(terrain.getSpeedDecreaseCoef());
      }
    }
  }
  
  public CarComponent getVoiture() {
    return voiture;
  }

  public Circuit getCircuit() {
    return circuit;
  }

  public List<Terrain> getAstarPath() {
    return astarPath;
  }
  
}
