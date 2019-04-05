package tm2DGame.ia;


public interface  Gene {
  public static double MUTATION = 0.1d;
  public static String TYPE_SPEED = "TYPE_SPEED";
  public static String TYPE_ROTATION = "TYPE_ROTATION";
  public static int SPEED_MAX = 20;
  public static int DISTANCE_MAX = 10;
  public static int ANGLE = 16;
  public static int[] ROTATIONS = {-1, 0, 1};

  public Gene mutation(double probability);

}