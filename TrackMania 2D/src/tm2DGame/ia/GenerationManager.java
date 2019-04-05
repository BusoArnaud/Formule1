package tm2DGame.ia;

import java.util.ArrayList;
import java.util.List;

import tm2D.Constants;
import tm2DGame.CarComponent;
import tm2DGame.terrain.Terrain;

public class GenerationManager implements Constants {

  double scoreDistance = 0d;
  double scoreSpeed = 0d;
  int round=0;
  GenePool genePool;
  CarComponent car;
  List<Subject> Subjects = new ArrayList<>();
  public GenerationManager(CarComponent car, List<Terrain> pathAstar, int number) {
    for (int i = 0; i < number; i++) {
      Subjects.add(new Subject(car, pathAstar));
    }
  }


}
