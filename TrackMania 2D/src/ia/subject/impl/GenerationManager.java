package ia.subject.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import ia.ga.impl.car.KeyEventGame;
import ia.subject.Chromosome;
import ia.subject.Subject;
import tm2D.Constants;
import tm2DGame.CarComponent;
import tm2DGame.terrain.Phatom;
import tm2DGame.terrain.Terrain;

public class GenerationManager implements Constants {
  private static final double MUTATION_PROBABILITE = 0.05;

  ThreadLocalRandom random = ThreadLocalRandom.current();

  List<CarSubject> subjects = new ArrayList<>();

  List<Terrain> pathAstar;
  int crossoverCount;

  public GenerationManager( List<Terrain> pathAstar, int populationMax) {
    this.pathAstar = pathAstar;
    crossoverCount = (int) Math.abs(populationMax / 2.0);
    for (int i = 0; i < populationMax; i++) {
      subjects.add(new CarSubject());
    }
  }

  public KeyEventGame getActions(double eval, CarSubject subject) {
    boolean accelerate = false;
    boolean rotate = false;
    int direction = 0;
    int rotationDirection = 0;
    Chromosome chromosome = subject.evalAll(eval);
    if (subject.getCar().getSpeed() < chromosome.getTargetSpeed()) {
      direction = 1;
      accelerate = true;
    } else if (subject.getCar().getSpeed() > chromosome.getTargetSpeed() + 2) {
      direction = -1;
      accelerate = true;
    }
    rotationDirection = chromosome.getTargetRotation();
    rotate = chromosome.getTargetRotation() != 0;

    return KeyEventGame.find(accelerate, rotate, direction, rotationDirection);
  }

  public double getEval(CarSubject subject) {
    Terrain myTerrain = new Phatom((int) Math.round(subject.getCar().getpX()),
        (int) Math.round(subject.getCar().getpY()));
    Terrain dest = pathAstar.get(0);
    double distance = pathAstar.get(0).getDistance(myTerrain);
    int distanceparcouru = 0;

    for (int i = 0; i < pathAstar.size(); i++) {
      Terrain terrain = pathAstar.get(i);
      double dist = terrain.getDistance(myTerrain);
      if (dist < distance) {
        distance = dist;
        distanceparcouru = i * 10;
        if (pathAstar.size() > i + subject.getGeneComplex().getDistanceEval()) {
          dest = pathAstar.get(i + subject.getGeneComplex().getDistanceEval());
        } else {
          dest = pathAstar.get(pathAstar.size() - 1);
        }
      }
    }

    subject.updatetFitness((distanceparcouru / distance));

    double targetX = (dest.getX() - subject.getCar().getpX());
    double targetY = (dest.getY() - subject.getCar().getpY());
    float angle = (float) Math.toDegrees(Math.atan2(targetY - 0, targetX - 1));
    float carAngle = (float) Math.toDegrees(subject.getCar().getCurrentAngle());
    double newradian = Math.toRadians((angle - carAngle) % 360) + Math.PI / 2;

    return newradian;
  }

  public List<Subject<CarSubject>> crossover( int crossoverCount,
      double probability) {
    return new ArrayList<>();
  }

}
