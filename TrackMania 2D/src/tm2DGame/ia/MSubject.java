package tm2DGame.ia;

import java.util.ArrayList;
import java.util.List;

import tm2D.Constants;

public class MSubject implements Constants, MGene {

  MGenePool rotationGenePool;
  MGenePool speedGenePool;
  MAssumptionGene assumptionGene;
  public MSubject(){
    rotationGenePool = new MGenePool(TYPE_ROTATION);
    speedGenePool = new MGenePool(TYPE_ROTATION);
    assumptionGene = new MAssumptionGene();
  }


  public MSubject(MSubject parent1, MSubject parent2, double parent1Proportion){

  }

  @Override
  public MSubject mutation(double probability) {
    return null;
  }

}
