package tm2DGame.ia;


public interface  MGene {
  public static double MUTATION = 0.1d;
  public static String TYPE_SPEED = "TYPE_SPEED";
  public static String TYPE_ROTATION = "TYPE_ROTATION";
  public static int SPEED_MAX = 20;
  public static int ASSUMPTIONS = 30;
  public static int ANGLE = 16;
  public static int[] ROTATIONS = {-1, 0, 1};

  public MGene mutation(double probability);
}