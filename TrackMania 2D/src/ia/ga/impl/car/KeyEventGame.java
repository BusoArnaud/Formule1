package ia.ga.impl.car;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import tm2DGame.CarComponent;
import tm2DGame.Voiture;

public enum KeyEventGame {

  UP(voiture->{voiture.setAccelerate(true); voiture.setRotate(false); voiture.setDirection(1);}),
  DOWN(voiture->{voiture.setAccelerate(true); voiture.setRotate(false); voiture.setDirection(-1);}),
  LEFT(voiture->{voiture.setAccelerate(false); voiture.setRotate(true); voiture.setRotateDirection(-1);}),
  RIGHT(voiture->{voiture.setAccelerate(false); voiture.setRotate(true); voiture.setRotateDirection(1);}),
  ACCRIGHT(voiture->{voiture.setAccelerate(true); voiture.setRotate(true); voiture.setDirection(1); voiture.setRotateDirection(1);}),
  ACCLEFT(voiture->{voiture.setAccelerate(true); voiture.setRotate(true); voiture.setDirection(1); voiture.setRotateDirection(-1);}),
  DOWNRIGHT(voiture->{voiture.setAccelerate(true); voiture.setRotate(true); voiture.setDirection(-1);  voiture.setRotateDirection(1);}),
  DOWNLEFT(voiture->{voiture.setAccelerate(true); voiture.setRotate(true); voiture.setDirection(-1);  voiture.setRotateDirection(-1);}),
  NOTHING(voiture->{voiture.setAccelerate(true); voiture.setRotate(false);});

  private static List<KeyEventGame> allActions = Arrays.asList(KeyEventGame.values());

  Consumer<CarComponent> carBehavior;
  
  private static Random rand = new Random();

  private KeyEventGame(Consumer<CarComponent> carBehavior) {
    this.carBehavior = carBehavior;
  }

  public Consumer<CarComponent> getCarBehavior() {
    return this.carBehavior;
  }

  public static KeyEventGame getRandom() {
    return allActions.get(rand.nextInt(allActions.size()));
  }

}