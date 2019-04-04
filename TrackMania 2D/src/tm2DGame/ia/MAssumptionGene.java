package tm2DGame.ia;

import java.util.concurrent.ThreadLocalRandom;

import tm2D.Constants;

public class MAssumptionGene implements Constants, MGene {


  ThreadLocalRandom random = ThreadLocalRandom.current();
  int assumption;

  public MAssumptionGene(){
    assumption = random.nextInt(ASSUMPTIONS);
  }

  private MAssumptionGene(int assumption, double mutationProbability){

    if (random.nextDouble() > mutationProbability) {
      this.assumption = assumption;
    } else {
      assumption = ROTATIONS[random.nextInt(3)];
    }
  }

  @Override
  public MAssumptionGene mutation(double probability) {
    return new MAssumptionGene(this.assumption, probability);
  }

}
