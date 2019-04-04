package tm2DGame.ia;

import java.util.concurrent.ThreadLocalRandom;

import tm2D.Constants;

public class MSpeedGene implements Constants, MGene {

  ThreadLocalRandom random = ThreadLocalRandom.current();
  double knownAngle;
  int targetSpeed;

  public MSpeedGene(){
    knownAngle = Math.toRadians(random.nextInt(ANGLE) * 22.5);
    targetSpeed = random.nextInt(SPEED_MAX);
  }

  private MSpeedGene(double knownAngle,int targetSpeed, double mutationProbability){
    if (random.nextDouble() > mutationProbability) {
      this.knownAngle = knownAngle;
    } else {
      knownAngle = Math.toRadians(random.nextInt(ANGLE) * 22.5);
    }
    if (random.nextDouble() > mutationProbability) {
      this.targetSpeed = targetSpeed;
    } else {
      targetSpeed = random.nextInt(SPEED_MAX);
    }
  }
  
  @Override
  public MSpeedGene mutation(double probability) {
    return new MSpeedGene(this.knownAngle, this.targetSpeed, probability);
  }

}
