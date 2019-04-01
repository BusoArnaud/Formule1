package tm2DGame;

import tm2D.Constants;
public class CarFactory implements Constants{
  public static int DEFAULT_ROTATION =16;
  public static int ROTATION_MAX = 14;
  public static int ROTATION_MIN = 18;

  public static String CAR_0 = "CAR_0";
  public static String CAR_1 = "CAR_1";
  public static String CAR_2 = "CAR_2";

  public static Voiture BuildCar(String carType){
    switch (carType) {
      case "CAR_0":
        return new Voiture(6, DEFAULT_ROTATION, 0.7, RELATIVE_PATH_CARS + "car0.png");
      case "CAR_1":
        return  new Voiture(8, ROTATION_MAX, 0.9, RELATIVE_PATH_CARS + "car6_litle.png");
      case "CAR_2":
        return new Voiture(10, ROTATION_MIN, 0.8, RELATIVE_PATH_CARS + "car7_litle.png");
      default:
      return null;
    }
  }
}