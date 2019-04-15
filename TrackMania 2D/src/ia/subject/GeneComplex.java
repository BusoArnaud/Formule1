package ia.subject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import ia.ga.impl.car.KeyEventGame;
import tm2D.Constants;
import tm2DGame.CarComponent;
import tm2DGame.IPlayer;
import tm2DGame.terrain.Terrain;

public class GeneComplex implements Constants, Gene {

  ThreadLocalRandom random = ThreadLocalRandom.current();
  Map<Double, Chromosome> chromosomes;
  KeyEventGame key;
  int distanceEval;
  public GeneComplex() {
    this.chromosomes = new HashMap<>();
  }

  /**
   * @return the key
   */
  public KeyEventGame getKey() {
    return key;
  }

  /**
   * @param key the key to set
   */
  public void setKey(KeyEventGame key) {
    this.key = key;
  }
  /**
   * @return the chromosomes
   */
  public Map<Double, Chromosome> getChromosomes() {
    return chromosomes;
  }
  /**
   * @return the distanceEval
   */
  public int getDistanceEval() {
    return distanceEval;
  }

  /**
   * @param distanceEval the distanceEval to set
   */
  public void setDistanceEval(int distanceEval) {
    this.distanceEval = distanceEval;
  }
  
  public void init() {
    this.distanceEval = random.nextInt(DISTANCE_MAX);
    int geneCount = random.nextInt(ANGLE);
    chromosomes = new HashMap<>();
    for (int i = 0; i < geneCount; i++) {
      addGene();
    }
  }

  private void addGene() {
      Chromosome gene = new Chromosome();
      chromosomes.put(gene.getRotation(), gene);
  }

  public GeneComplex(GeneComplex old, double mutationProbability) {
    this.chromosomes = old.chromosomes.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    if (random.nextDouble() > mutationProbability) {
      this.distanceEval = old.distanceEval;
    } else {
      this.distanceEval = random.nextInt(DISTANCE_MAX) + 1;
    }
    Map<Double, Chromosome> newchromosomes = new HashMap<>();
    old.chromosomes.entrySet().forEach(entry -> {
      Chromosome newchromosome = entry.getValue().mutation(mutationProbability);
      newchromosomes.put(newchromosome.getRotation(), newchromosome);
    });
    this.chromosomes = newchromosomes;

  }

  @Override
  public GeneComplex mutation(double probability) {
    return new GeneComplex(this, probability);
  }

  public Chromosome eval(double evalRadian) {
    Double targetkey= null;
    Double lastKey= null;
    for( Double key: chromosomes.keySet()){
      if(targetkey == null){
        targetkey = key;
        lastKey = key;
      }else if(Math.abs(evalRadian - lastKey) < Math.abs(evalRadian - key)){
        targetkey = lastKey;
      }else{
        targetkey = key;
        lastKey = key;
      }
    }
    
    return chromosomes.get(targetkey);
  }

  public double getEval(Terrain dest, IPlayer player) {
    CarComponent car = player.getCar();
    // this.scoreDistance += distancePathAstar;
    // System.out.println(scoreDistance);
    double targetX = (dest.getX() - car.getpX());
    double targetY = (dest.getY() - car.getpY());
    float angle = (float) Math.toDegrees(Math.atan2(targetY - 0, targetX - 1));
    float carAngle = (float) Math.toDegrees(car.getCurrentAngle());
    double newradian = Math.toRadians((angle - carAngle) % 360) + Math.PI / 2;

    return newradian;
  }

  public KeyEventGame getActions(Terrain dest, IPlayer player) {
    CarComponent car = player.getCar();

    boolean accelerate = false;
    boolean rotate = false;
    int direction = 0;
    int rotationDirection = 0;
    Chromosome chromosome = this.eval(getEval(dest, player));
    if (chromosome == null) {
      return KeyEventGame.NOTHING;
    }
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
}
