package ia.ga.impl.CarSubject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ia.ga.core.FitnessCalc;
import ia.ga.core.Individual;
import ia.ga.impl.car.KeyEventGame;
import ia.subject.Chromosome;
import ia.subject.GeneComplex;
import pathfinding.Astar;
import tm2DGame.GameBoard;
import tm2DGame.IPlayer;
import tm2DGame.PlayerCarComponent;
import tm2DGame.boards.AbstractBoard;
import tm2DGame.boards.LongSimulationBoard;
import tm2DGame.terrain.Phatom;
import tm2DGame.terrain.Terrain;

public class SubjectFitnessCalc implements FitnessCalc<GeneComplex> {

  double scoreDistance = 0d;
  double scoreSpeed = 0d;
  int round=0;
  List<Terrain> pathAstar;
  private final GameBoard realBoard;

  private AbstractBoard simulationBoard;

  String[] tracks = { "simu1", "simu2", "simu3", "simu4", "simu5", "simu6", "simu7", "simu8"};

  public SubjectFitnessCalc(GameBoard gameBoard ) {
    gameBoard.loadTrack(tracks[0]);
    this.simulationBoard = new LongSimulationBoard(gameBoard);
    this.realBoard = gameBoard;
    this.pathAstar = simulationBoard.getAstarPath();
  }

  private void updateTrack(String name) {
    this.realBoard.loadTrack(name);
    this.simulationBoard = new LongSimulationBoard(this.realBoard);
    this.pathAstar = new Astar(realBoard.getCircuit()).call();
  }

  @Override
  public Integer getFitness(Individual<GeneComplex> individual) {
    final IPlayer car = new PlayerCarComponent(realBoard.getVoiture());
    GeneComplex gene = individual.getGene(0);
    int fitness = 0;
    Map<String, Integer> trackFitness= new HashMap<>();
    for (String var : tracks) {
      scoreDistance = 0d;
      scoreSpeed = 0d;
      round = 0;
      updateTrack(var);
      car.initPosition(290, 380);
      simulationBoard.setWallCollisionCount(0);
      simulationBoard.setVoiture(car);
      for (int j = 0; j < 300; j++) {
        Terrain target = findTarget(car, gene);
        KeyEventGame play = gene.getActions(target, car);

        gene.setKey(play);
        // System.out.println(var + " "+ play);
        play.getCarBehavior().accept(car);
        if (simulationBoard.advance()) {
          fitness = 200;
          break;
        } else {
          fitness = (int) Math.round(100* this.scoreDistance / pathAstar.size()) + (int) Math.abs(scoreSpeed / round);
        }
      }
      trackFitness.put(var, fitness);
    }
    System.out.println("result : " + trackFitness.toString());
    return trackFitness.values().stream().reduce(0, (int1, int2) -> int1 + int2);
  }

  private Terrain findTarget(final IPlayer car, GeneComplex gene) {
    Terrain myTerrain = new Phatom((int) Math.round(car.getpX()), (int) Math.round(car.getpY()));
    Terrain dest = pathAstar.get(0);
    double distance = pathAstar.get(0).getDistance(myTerrain);
    for (int i = 0; i < pathAstar.size(); i++) {
      Terrain terrain = pathAstar.get(i);
      double dist = terrain.getDistance(myTerrain);
      if (dist < distance) {
        distance = dist;
        this.scoreDistance = i;
        if (pathAstar.size() > i + gene.getDistanceEval()) {
          dest = pathAstar.get(i + gene.getDistanceEval());
        } else {
          dest = pathAstar.get(pathAstar.size() - 1);
        }
      }
    }
    this.round++;
    this.scoreSpeed += car.getSpeed();
    return dest;
  }

  @Override
  public Integer getMaxFitness() {
    return tracks.length * 2 * 100;
  }



}
