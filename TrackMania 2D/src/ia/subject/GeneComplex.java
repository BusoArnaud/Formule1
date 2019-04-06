package ia.subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import tm2D.Constants;

public class GeneComplex implements Constants, Gene {

  ThreadLocalRandom random = ThreadLocalRandom.current();
  Map<Double, Chromosome> chromosomes;
  int distanceEval;
  public GeneComplex() {
    this.chromosomes = new HashMap<>();
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
    int geneCount = random.nextInt(5) + 1;
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
    List<Double> keysAsArray = new ArrayList<>(this.chromosomes.keySet());
    if (random.nextDouble() < mutationProbability) {
      if (random.nextInt(2) == 1) {
        addGene();
      } else {
        this.chromosomes.remove(keysAsArray.get(random.nextInt(keysAsArray.size())));
      }

    }
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
}
