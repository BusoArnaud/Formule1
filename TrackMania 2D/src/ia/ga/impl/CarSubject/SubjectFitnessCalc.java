package ia.ga.impl.CarSubject;

import java.util.List;

import ia.ga.core.FitnessCalc;
import ia.ga.core.Individual;
import ia.ga.impl.car.KeyEventGame;
import ia.subject.Chromosome;
import ia.subject.GeneComplex;
import tm2DGame.CarComponent;
import tm2DGame.GameBoard;
import tm2DGame.boards.SimulationBoard;
import tm2DGame.terrain.Phatom;
import tm2DGame.terrain.Terrain;

public class SubjectFitnessCalc implements FitnessCalc<GeneComplex> {

  double scoreDistance = 0d;
  double scoreSpeed = 0d;
  int round=0;
  GeneComplex geneComplex;
  List<Terrain> pathAstar;
  private final GameBoard realBoard;

  private final SimulationBoard simulationBoard;

  public SubjectFitnessCalc(GameBoard gameBoard ) {
    this.simulationBoard = new SimulationBoard(gameBoard);
    this.realBoard = gameBoard;
    this.geneComplex = new GeneComplex();
    this.geneComplex.init();
    this.pathAstar = simulationBoard.getAstarPath();
  }

  public void calc(CarComponent car){
    Terrain myTerrain = new Phatom((int) Math.round(simulationBoard.getVoiture().getpX()),(int) Math.round(car.getpY()));
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
    scoreSpeed += simulationBoard.getVoiture().getSpeed();
  }


  @Override
  public Integer getFitness(Individual<GeneComplex> individual) {
    final List<Terrain> astarPath = simulationBoard.getAstarPath();
    final CarComponent car = new CarComponent(realBoard.getVoiture());
    simulationBoard.setVoiture(car);
    // individual.getChromosome()[ACCRIGHT, ACCRIGHT, UP, ACCLEFT, NOTHING, UP]
    // List<KeyEventGame> upKeys =
    // Arrays.asList(KeyEventGame.RIGHT,KeyEventGame.ACCLEFT,KeyEventGame.ACCLEFT,KeyEventGame.DOWNLEFT,KeyEventGame.DOWN);
    KeyEventGame play = getActions(getEval(geneComplex, car), geneComplex, car);
    play.getCarBehavior().accept(car);
    if (simulationBoard.advance()) {
      return astarPath.size() + 1;
    }
    Terrain carTerrain = simulationBoard.getCircuit().getTerrain(car.getpX(), car.getpY());
    double min = astarPath.get(0).getDistance(carTerrain);
    int index = 0;
    for (Terrain terrain : simulationBoard.getCircuit().getAdjacentTiles(carTerrain, 2)) {
      int temp;
      if ((temp = astarPath.indexOf(terrain)) > -1) {
        double distance = astarPath.get(temp).getDistance(carTerrain);
        if (distance < min) {
          min = distance;
          index = temp;
        }
      }
    }
    return index;
  }

  @Override
  public Integer getMaxFitness() {
    return pathAstar.size() * 10;
  }
  
  public KeyEventGame getActions(double eval, GeneComplex gene, CarComponent car) {

    boolean accelerate = false;
    boolean rotate = false;
    int direction = 0;
    int rotationDirection = 0;
    Chromosome chromosome = gene.eval(eval);
    if (car.getSpeed() < chromosome.getTargetSpeed()) {
      direction = 1;
      accelerate = true;
    } else if (car.getSpeed() > chromosome.getTargetSpeed() + 2) {
      direction = -1;
      accelerate = true;
    }
    rotationDirection = chromosome.getTargetRotation();
    rotate = chromosome.getTargetRotation() != 0;

    return KeyEventGame.find(accelerate, rotate, direction, rotationDirection);
  }

  public double getEval(GeneComplex gene, CarComponent car) {
    Terrain myTerrain = new Phatom((int) Math.round(car.getpX()), (int) Math.round(car.getpY()));
    Terrain dest = pathAstar.get(0);
    double distance = pathAstar.get(0).getDistance(myTerrain);
    for (int i = 0; i < pathAstar.size(); i++) {
      Terrain terrain = pathAstar.get(i);
      double dist = terrain.getDistance(myTerrain);
      if (dist < distance) {
        distance = dist;
        if (pathAstar.size() > i + gene.getDistanceEval()) {
          dest = pathAstar.get(i + gene.getDistanceEval());
        } else {
          dest = pathAstar.get(pathAstar.size() - 1);
        }
      }
    }

    double targetX = (dest.getX() - car.getpX());
    double targetY = (dest.getY() - car.getpY());
    float angle = (float) Math.toDegrees(Math.atan2(targetY - 0, targetX - 1));
    float carAngle = (float) Math.toDegrees(car.getCurrentAngle());
    double newradian = Math.toRadians((angle - carAngle) % 360) + Math.PI / 2;

    return newradian;
  }

}
