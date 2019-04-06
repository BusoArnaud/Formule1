package ia.ga.impl.CarSubject;

import ia.ga.core.Individual;
import ia.subject.GeneComplex;
import tm2D.Constants;
 
public class BehaviorSubject extends Individual<GeneComplex> implements Constants {


  public BehaviorSubject() {
  }

  @Override
  public void mutate(double mutationRate) {
      for (int i = 0; i < size(); i++) {
        if (Math.random() <= mutationRate) {
          genes.set(i, genes.get(i).mutation(mutationRate));
        }
      }
  }

  @Override
  protected GeneComplex geneSupplier() {
    GeneComplex geneComplex = new GeneComplex();
    geneComplex.init();
    return geneComplex;
  }

  @Override
  protected int lengthSupplier() {
    return 0;
  }


}
