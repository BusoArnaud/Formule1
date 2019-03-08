package tm2DGame;

public class TerrainFactory {

  private static TerrainFactory instance = new TerrainFactory();

  private TerrainFactory(){};


  public static TerrainFactory getInstance(){
    return instance;
  }
}