package ia.ga.core;

import static java.util.stream.Collectors.toList;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Population<T, K extends Individual<T>> {

  private List<K> individuals;

  private int populationSize;
  
  private final FitnessCalc<T> fitnessCalc;
  
  private boolean fitnessCalculated = false;
  
  /*
   * Constructors
   */
  // Create a population
  public Population(int populationSize, FitnessCalc<T> fitnessCalc) {
    individuals = new ArrayList<>(populationSize);
    this.populationSize = populationSize;
    this.fitnessCalc = fitnessCalc;
  }
  
  public final void initialize(Class<K> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
    // Loop and create individuals
    for (int i = 0; i < populationSize; i++) {
      K individual = clazz.getDeclaredConstructor().newInstance();
      individual.randomizeIndividual();
      add(individual);
    }
  }

  public void add(K individual) {
    individuals.add(individual);
  }

  public final int size() {
    return individuals.size();
  }

  public List<K> getSortedList() {
    if (!fitnessCalculated) {
      individuals = Collections.unmodifiableList(individuals.stream()
          .sorted((crnt, next) ->  next.getFitness(fitnessCalc) - crnt.getFitness(fitnessCalc))
          .collect(toList()));
      fitnessCalculated = true;
    }

    return individuals;
  }

  public K getElement(int index) {
    return individuals.get(index);
  }
  
}