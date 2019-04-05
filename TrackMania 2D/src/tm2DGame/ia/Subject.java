package tm2DGame.ia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tm2D.Constants;
import tm2DGame.CarComponent;
import tm2DGame.terrain.Phatom;
import tm2DGame.terrain.Terrain;

public class Subject implements Constants {

  double scoreDistance = 0d;
  double scoreSpeed = 0d;
  int round=0;
  GenePool genePool;
  CarComponent car;
  List<Terrain> pathAstar;
  public Subject(CarComponent car, List<Terrain> pathAstar) {
    this.car = car;
    this.pathAstar = pathAstar;
    this.genePool = new GenePool();
    this.genePool.init();
  }

  public void updateScore(int distanceAstar, double speed){
    this.round++;
    scoreSpeed+=speed;
  }

  public double getFitness(){
    return scoreDistance * scoreSpeed / round;
  }

  public List<String> setActions(double eval){
    List<String> actions = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : genePool.evalAll(eval).entrySet()) {
      switch (entry.getKey()) {
      case "SPEED":
        if(car.getSpeed()< entry.getValue()){
          car.setDirection(1);
          car.setAccelerate(true);
        }else if (car.getSpeed() > entry.getValue() + 2 ){
          car.setDirection(-1);
          car.setAccelerate(true);
        }else{
          car.setDirection(1);
          car.setAccelerate(false);
        }
        break;
      case "ROTATION":
        car.setRotateDirection(entry.getValue());
        car.setRotate(entry.getValue() != 0);
        break;
    
      default:
        break;
      }
      
    }
    return actions;
  }

  public double getEval(){
    Terrain myTerrain = new Phatom((int) Math.round(car.getpX()),(int) Math.round(car.getpY()));
    int distanceparcouru = 0;
    Terrain dest = pathAstar.get(0);
    double distance = pathAstar.get(0).getDistance(myTerrain);
    for (int i = 0; i < pathAstar.size() ; i++) {
      Terrain terrain = pathAstar.get(i);
      double dist = terrain.getDistance(myTerrain);
      if (dist < distance) {
        distance = dist;
        distanceparcouru = i;
        if(pathAstar.size() > i+ genePool.geneComplex.distanceEval){
          dest = pathAstar.get(i+ genePool.geneComplex.distanceEval);
        }else{
          dest = pathAstar.get(pathAstar.size() - 1);
        }
      }
    }
    this.scoreDistance += (distanceparcouru / distance); 

    double targetX = (dest.getX() - car.getpX());
    double targetY = (dest.getY() - car.getpY());
    float angle = (float) Math.toDegrees(Math.atan2(targetY - 0, targetX - 1));
    float carAngle = (float) Math.toDegrees(car.getCurrentAngle());
    double newradian = Math.toRadians((angle - carAngle) % 360) + Math.PI / 2;

    return newradian;
  }
}
