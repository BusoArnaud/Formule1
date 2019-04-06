package ia.ga.impl.CarSubject;

import java.util.List;

import ia.ga.core.FitnessCalc;
import ia.ga.core.Individual;
import ia.subject.Subject;
import tm2DGame.CarComponent;
import tm2DGame.terrain.Phatom;
import tm2DGame.terrain.Terrain;

public class SubjectFitnessCalc implements FitnessCalc<Subject> {

  double scoreDistance = 0d;
  double scoreSpeed = 0d;
  int round=0;
  Subject subject;
  CarComponent car;
  List<Terrain> pathAstar;

  public SubjectFitnessCalc(CarComponent car, List<Terrain> pathAstar) {
    this.car = car;
    this.pathAstar = pathAstar;
    //this.subject = new Subject();
    this.subject.init();
  }

  public void updateScore(int distanceAstar, double speed){
  }

  public void calc(){
    Terrain myTerrain = new Phatom((int) Math.round(car.getpX()),(int) Math.round(car.getpY()));
    int distanceparcouru = 0;
    double distance = pathAstar.get(0).getDistance(myTerrain);
    for (int i = 0; i < pathAstar.size() ; i++) {
      Terrain terrain = pathAstar.get(i);
      double dist = terrain.getDistance(myTerrain);
      if (dist < distance) {
        distance = dist;
        distanceparcouru = i*10;
      }
    }
    this.scoreDistance += (distanceparcouru / distance);
    this.round++;
    scoreSpeed += car.getSpeed();
  }


  @Override
  public Integer getFitness(Individual<Subject> individual) {
    calc();
    return (int) Math.round(scoreDistance * scoreSpeed / round);
  }

  @Override
  public Integer getMaxFitness() {
    return pathAstar.size() * 10;
  }
  
}
