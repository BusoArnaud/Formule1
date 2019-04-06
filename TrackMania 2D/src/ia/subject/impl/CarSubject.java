package ia.subject.impl;

import ia.subject.Subject;
import tm2DGame.CarComponent;

public class CarSubject extends Subject<CarSubject>{


  private double scoreDistance;
  private int round;
  private double scoreSpeed;
  private CarComponent car;

  public CarSubject() {
    super(CarSubject.class);
  }

  public CarSubject(CarComponent car) {
    super(CarSubject.class);
    this.car = car;
  }
  public void updatetFitness(double scoreDistance) {
    this.scoreDistance += scoreDistance;
    this.round++;
    this.scoreSpeed += this.car.getSpeed();
  }

  /**
   * @return the car
   */
  public CarComponent getCar() {
    return car;
  }

  public int getFitness(){
    return (int) Math.round(scoreDistance * scoreSpeed / round);
  }
}
