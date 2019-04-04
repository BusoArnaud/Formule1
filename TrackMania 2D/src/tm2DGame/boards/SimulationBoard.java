package tm2DGame.boards;

import tm2DGame.CarComponent;
import tm2DGame.GameBoard;
import tm2DGame.Voiture;

public class SimulationBoard extends AbstractBoard{
 
  public final int numberOfTicksInAThirdSecond = 10;
  
  public SimulationBoard(GameBoard board) {
    this.circuit = board.getCircuit();
    this.voiture = board.getVoiture();
    this.astarPath = board.getAstarPath();
  }
  
  public void setVoiture(CarComponent voiture) {
    this.voiture = voiture;
  }
  
  @Override
  public void advance() {
    for(int i=0; i<numberOfTicksInAThirdSecond;i++)
      super.advance();
  }

}
