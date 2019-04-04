package ia.ga.core;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

//T here is the Gene, K is the individual that has the gene
public class GeneticAlgorithmTemplate<T, K extends Individual<T>> {
  
  private FitnessCalc<T> fitnessCalc;
  
  private EvolutionAlgorithm<T, K> evolutionAlgorithm;
  
  private final int maxNumberOfGeneration;
  
  private final int populationSize;
  
  private Class<K> clazz;
  
  public GeneticAlgorithmTemplate(FitnessCalc<T> fitnessCalc, EvolutionAlgorithm<T, K> evolutionAlgorithm,
      Class<K> clazz, int maxNumberOfGeneration, int populationSize) {
    this.fitnessCalc = fitnessCalc;
    this.evolutionAlgorithm = evolutionAlgorithm;
    this.clazz = clazz;
    this.maxNumberOfGeneration = maxNumberOfGeneration;
    this.populationSize = populationSize;
  }
  
  public List<T> getSolution() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
    
    Population<T, K> pop = initializePopulation(populationSize,clazz);
    
    // Evolve our population until we reach an optimum solution
    int generationCount = 0;
    
    K fittest = pop.getSortedList().get(0);
    while (fittest.getFitness() < fitnessCalc.getMaxFitness() && generationCount<maxNumberOfGeneration) {
      generationCount++;
      pop = evolutionAlgorithm.evolvePopulation(pop);
      fittest = pop.getSortedList().get(0);
    }
    System.out.println("Solution found!");
    System.out.println("Generation: " + generationCount);
    System.out.println("Genes:");
    System.out.println(fittest);
    return new LinkedList<>(fittest.getChromosome());
  }
  
  protected Population<T, K> initializePopulation(int size, Class<K> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
    Population<T, K> pop = new Population<>(size, fitnessCalc);
    pop.initialize(clazz);
    return pop;
  }
  
  
}
