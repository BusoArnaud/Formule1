package tm2DGame.ia;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import tm2D.Constants;

public class GenePool implements Constants, Gene {

  ThreadLocalRandom random = ThreadLocalRandom.current();
  GeneComplex geneComplex;
  public GenePool() {
  }

  public void init() {
    geneComplex = new GeneComplex();
    geneComplex.init();
  }


  public GenePool(GenePool parent1, GenePool parent2, double parent1Proportion){
    GenePool son = new GenePool();
    if (random.nextDouble() >= parent1Proportion) {
      son.geneComplex.distanceEval = parent1.geneComplex.distanceEval;
    } else {
      son.geneComplex.distanceEval = parent2.geneComplex.distanceEval;
    }
    
    parent1.geneComplex.chromosomes.entrySet().forEach(entry -> {
      if (random.nextDouble() >= parent1Proportion) {
        son.geneComplex.chromosomes.put(entry.getKey(), entry.getValue());
      }
    });
    parent2.geneComplex.chromosomes.entrySet().forEach(entry -> {
      if (random.nextDouble() < parent1Proportion) {
        son.geneComplex.chromosomes.put(entry.getKey(), entry.getValue());
      }
    });
  }

  @Override
  public GenePool mutation(double probability) {
    this.geneComplex = this.geneComplex.mutation(probability);
    return this;
  }

  public Map<String, Integer> evalAll(double eval){
    return geneComplex.eval(eval);
  }
}
