package ia.ga.impl.string;

import java.util.Random;

import ia.ga.core.Individual;

public class StringIndividual extends Individual<Character>{

  private static final Random r = new Random();
  @Override
  protected Character geneSupplier() {
    return (char)(r.nextInt(26) + 'a');
  }

  @Override
  protected int lengthSupplier() {
    return 11;
  }

}
