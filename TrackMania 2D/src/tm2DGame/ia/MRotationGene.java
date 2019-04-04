package tm2DGame.ia;

import java.util.concurrent.ThreadLocalRandom;

import tm2D.Constants;

public class MRotationGene implements Constants, MGene {

  
  ThreadLocalRandom random = ThreadLocalRandom.current();
  double knownAngle;
  int targetRotation;

  public MRotationGene(){
    knownAngle = Math.toRadians(random.nextInt(ANGLE) * 22.5);
    targetRotation = ROTATIONS[random.nextInt(3)];
  }

  private MRotationGene(double knownAngle,int targetRotation, double mutationProbability){
    if(random.nextDouble() > mutationProbability){
      this.knownAngle = knownAngle;
    }else{
      knownAngle = Math.toRadians(random.nextInt(ANGLE) * 22.5);
    }
    if(random.nextDouble() > mutationProbability){
      this.targetRotation = targetRotation;
    } else {
      targetRotation = ROTATIONS[random.nextInt(3)];
    }
  }

  @Override
  public MRotationGene mutation(double probability) {
    return new MRotationGene(this.knownAngle, this.targetRotation, probability);
  }

}
