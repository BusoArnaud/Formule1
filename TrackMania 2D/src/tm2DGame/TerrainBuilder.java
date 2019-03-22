package tm2DGame;

import tm2DGame.terrain.*;

public class TerrainBuilder {
   
  private String type;
  private Terrain terrain;

  public TerrainBuilder(char txt, int x, int y){
    if (txt == ' ') {
      this.type= "PISTE";
      this.terrain = new Piste(x * 10, y * 10);
    } else if (txt == '.') {
      this.type = "HERBE";
      this.terrain = new Herbe(x * 10, y * 10);
    } else if (txt == 'B') {
      this.type = "BORDURE";
      this.terrain = new Bordure(x * 10, y * 10);
    } else if (txt == 'S') {
      this.type = "SABLE";
      this.terrain = new Sable(x * 10, y * 10);
    } else if (txt == 'x') {
      this.type = "MUR";
      this.terrain = new Mur(x * 10, y * 10);
    } else if (txt == 'o') {
      this.type = "EAU";
      this.terrain = new Eau(x * 10, y * 10);
    } else if (txt == 'D') {
      this.type = "DAMIER";
      this.terrain = new Damier(x * 10, y * 10);
    }
  }
  
  public boolean isTerrain(){
    return this.type != null;
  }

  public Terrain getTerrain() {
    return this.terrain;
  }

  public String getType() {
    return this.type;
  }
}