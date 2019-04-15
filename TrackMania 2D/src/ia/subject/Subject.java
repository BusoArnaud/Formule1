package ia.subject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import tm2D.Constants;

public class Subject implements Constants, Gene {

  static ThreadLocalRandom random = ThreadLocalRandom.current();
  GeneComplex geneComplex;


  public Subject() {
  }

  public void init() {
    geneComplex = new GeneComplex();
    geneComplex.init();
  }

  /**
   * @return the geneComplex
   */
  public GeneComplex getGeneComplex() {
    return geneComplex;
  }

  public static Subject mutation(Subject parent1, Subject parent2, double parent1Proportion){
    Subject son = new Subject();
    if (random.nextDouble() >= parent1Proportion) {
      son.getGeneComplex().setDistanceEval(parent1.getGeneComplex().getDistanceEval());
    } else {
      son.getGeneComplex().setDistanceEval(parent2.getGeneComplex().getDistanceEval());
    }
    
    parent1.getGeneComplex().getChromosomes().entrySet().forEach(entry -> {
      if (random.nextDouble() >= parent1Proportion) {
        son.getGeneComplex().getChromosomes().put(entry.getKey(), entry.getValue());
      }
    });
    parent2.getGeneComplex().getChromosomes().entrySet().forEach(entry -> {
      if (random.nextDouble() < parent1Proportion) {
        son.getGeneComplex().getChromosomes().put(entry.getKey(), entry.getValue());
      }
    });

    return son;
  }

  
  public Chromosome evalAll(double eval){
    return geneComplex.eval(eval);
  }
  
  public void updatetFitness(double score) {
    
  }


  public int getFitness(){
    return 0;
  }
  
  @Override
  public Subject mutation(double probability) {
    this.geneComplex = this.geneComplex.mutation(probability);
    return this;
  }

  public  List<Subject> crossover(List<Subject> subjects, int crossoverCount, double probability)
      throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
      NoSuchMethodException, SecurityException {
    List<Subject> generation = new ArrayList<>();
    List<Subject> sorted = subjects.stream().sorted(Comparator.comparing(Subject::getFitness))
        .collect(Collectors.toList());
    Subject a = sorted.get(0);
    Subject b = sorted.get(1);
    for (int i = 0; i < crossoverCount; i++) {
      Subject newSubject = Subject.mutation(a, b, (1.0 * i / crossoverCount));
      generation.add(newSubject);
    }
    for (int i = 0; i < crossoverCount; i++) {
      a = sorted.get(random.nextInt(sorted.size()));
      b = sorted.get(random.nextInt(sorted.size()));
      Subject newSubject = Subject.mutation(a, b, random.nextDouble());
      generation.add(newSubject);
    }
    return generation.stream().map(subject -> subject.mutation(probability)).collect(Collectors.toList());
  }

}
