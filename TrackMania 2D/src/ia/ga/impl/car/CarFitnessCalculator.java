package ia.ga.impl.car;

import java.util.List;

import ia.ga.core.FitnessCalc;
import ia.ga.core.Individual;
import tm2DGame.GameBoard;
import tm2DGame.Voiture;
import tm2DGame.boards.SimulationBoard;
import tm2DGame.terrain.Terrain;

public class CarFitnessCalculator implements FitnessCalc<KeyEventGame>{

  private final GameBoard realBoard;
  
  private final SimulationBoard simulationBoard;
  
  public CarFitnessCalculator(GameBoard gameBoard) {
    this.simulationBoard = new SimulationBoard(gameBoard);
    this.realBoard = gameBoard;
  }
  
  //we could see what the kd algorithm is and how it is used; 
  //it could be faster than this implementation
  @Override
  public Integer getFitness(Individual<KeyEventGame> individual) {
    final List<Terrain> astarPath = simulationBoard.getAstarPath();
    final Voiture voiture = new Voiture(realBoard.getVoiture());
    simulationBoard.setVoiture(voiture);
    //individual.getChromosome()[ACCRIGHT, ACCRIGHT, UP, ACCLEFT, NOTHING, UP]
//    List<KeyEventGame> upKeys = Arrays.asList(KeyEventGame.ACCRIGHT,KeyEventGame.ACCRIGHT,KeyEventGame.UP,KeyEventGame.ACCLEFT,KeyEventGame.NOTHING,KeyEventGame.UP);
    for (KeyEventGame play : individual.getChromosome()) {
      play.carBehavior.accept(voiture);
      simulationBoard.advance();
    }
    Terrain carTerrain = simulationBoard.getCircuit().getTerrain(voiture.getpX(), voiture.getpY());
    if(carTerrain.isEnd()) {
      return astarPath.size() + 1;
    }
    double min = astarPath.get(0).getDistance(carTerrain);
    int index = 0;
    for(int i = 1; i<astarPath.size(); i++) {
      double distance = astarPath.get(i).getDistance(carTerrain);
      if(distance<min) {
        min = distance;
        index = i;
      }
    }
     return index;
  }

  @Override
  public Integer getMaxFitness() {
    return realBoard.getAstarPath().size() + 1;
  }

}
