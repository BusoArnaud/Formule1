package tm2DGame.ia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import tm2D.Constants;

public class MGenePool implements Constants, MGene {

  ThreadLocalRandom random = ThreadLocalRandom.current();
  List<MGene> genePool;
  String type;

  public MGenePool(String type) {
    int geneCount = random.nextInt(5);
    genePool = new ArrayList<>();
    for (int i = 0; i < geneCount; i++) {
      addGene();
    }
  }

  private void addGene() {
    switch (type) {
    case TYPE_ROTATION:
      genePool.add(new MRotationGene());
      break;
    case TYPE_SPEED:
      genePool.add(new MSpeedGene());
      break;
    default:
      break;
    }
  }

  public MGenePool(List<MGene> genePool2, double mutationProbability) {
    if (random.nextDouble() > mutationProbability) {
      this.genePool = genePool2;
    } else {
      if(random.nextInt(2) == 1 ){
        addGene();
      }else{
        Collections.shuffle(genePool);
        genePool.remove(genePool.size() - 1);
      }
      
    }
    genePool = genePool.stream().map(gene -> gene.mutation(MUTATION)).collect(Collectors.toList());
  }

  @Override
  public MGenePool mutation(double probability) {
    return new MGenePool(this.genePool, probability);
  }

}
