package ia.ga.impl.CarSubject;


import ia.ga.core.FitnessCalc;
import ia.ga.core.Individual;
import ia.subject.GeneComplex;
import tm2D.Constants;
 
public class BehaviorSubject extends Individual<GeneComplex> implements Constants {


  public BehaviorSubject() {
  }

  @Override
  public void mutate(double mutationRate) {
    if (Math.random() <= mutationRate) {
      if(genes.get(0) == null){
        genes.set(0, geneSupplier());
      }else{
        genes.set(0, genes.get(0).mutation(mutationRate));
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
    return 1;
  }

  @Override
  protected int getFitness(FitnessCalc<GeneComplex> fitnessCalc) {
    if (fitness == null){
      fitness = fitnessCalc.getFitness(this);
    }

    return fitness;
  }

}
