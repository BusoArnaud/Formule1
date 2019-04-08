package ia.subject;

import java.util.concurrent.ThreadLocalRandom;

import tm2D.Constants;

public class Chromosome implements Constants, Gene {

  
  ThreadLocalRandom random = ThreadLocalRandom.current();
  double knownAngle;
  int targetRotation;
  int targetSpeed;

  /**
   * @return the targetRotation
   */
  public int getTargetRotation() {
    return targetRotation;
  }

  /**
   * @return the targetSpeed
   */
  public int getTargetSpeed() {
    return targetSpeed;
  }

  public Chromosome(){
    knownAngle = Math.toRadians(random.nextInt(ANGLE) * 22.5);
    targetRotation = ROTATIONS[random.nextInt(3)];
    targetSpeed = random.nextInt(SPEED_MAX) + 1;
  }

  private Chromosome(double knownAngle, int targetRotation, int targetSpeed, double mutationProbability){
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
  public Chromosome mutation(double probability) {
    return new Chromosome(this.knownAngle, this.targetRotation, this.targetSpeed, probability);
  }
  
  public Double getRotation() {
    return knownAngle;
  }
}
