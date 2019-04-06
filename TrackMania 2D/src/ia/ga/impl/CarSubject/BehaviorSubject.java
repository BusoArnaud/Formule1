package ia.ga.impl.CarSubject;

import ia.ga.core.Individual;
import ia.subject.Subject;
import ia.subject.impl.CarSubject;
import tm2D.Constants;
 
public class BehaviorSubject extends Individual<Subject> implements Constants {

  public BehaviorSubject(){
    super();
  }
  
  @Override
  protected Subject geneSupplier() {
    Subject pool = new CarSubject();
    pool.init(); 
    return pool;
  }

  @Override
  protected int lengthSupplier() {
    return 1;
  }
  
}
