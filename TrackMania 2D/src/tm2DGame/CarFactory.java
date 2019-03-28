package tm2DGame;

import tm2DGame.Voiture;

import tm2D.Constants;
public class CarFactory implements Constants{
  public static Double DEFAULT_ROTATION = Math.PI / 16;
  public static Double ROTATION_MAX = Math.PI / 14;
  public static Double ROTATION_MIN = Math.PI / 18;

  public static Voiture CAR_0 = new Voiture(6, DEFAULT_ROTATION, 0.7, RELATIVE_PATH_CARS + "car0.png");
  public static Voiture CAR_1 = new Voiture(8, ROTATION_MAX, 0.9, RELATIVE_PATH_CARS + "car6_litle.png");
  public static Voiture CAR_2 = new Voiture(10, ROTATION_MIN, 0.8, RELATIVE_PATH_CARS + "car7_litle.png");
}