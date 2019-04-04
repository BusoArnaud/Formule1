package ia.ga.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import ia.ga.core.EvolutionAlgorithm;
import ia.ga.core.FitnessCalc;
import ia.ga.core.Individual;
import ia.ga.core.Population;

public class SimpleEvolutionAlgorithm<T, K extends Individual<T>> extends EvolutionAlgorithm<T,K>{

  private static Random rand = new Random();
  
  private final Class<K> clazz;
  
  private final int selectionSize;
  
  public SimpleEvolutionAlgorithm(double crossoverRate, double mutationRate, int selectionSize,
      FitnessCalc<T> fitnessCalc, Class<K> clazz) {
    super(crossoverRate, mutationRate, fitnessCalc);
    this.clazz = clazz;
    this.selectionSize = selectionSize;
  }

  @Override
  protected K crossover(K indiv1, K indiv2) {
  
  try {
    // Preparing the baby
    K newSol = clazz.getDeclaredConstructor().newInstance();
    for (int i = 0; i < indiv1.size(); i++) {
      //DNA mixing
      if (rand.nextDouble() <= crossoverRate) {
        newSol.add(indiv1.getGene(i));
      } else {
        newSol.add(indiv2.getGene(i));
      }
    }
    return newSol;
  } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
      | NoSuchMethodException | SecurityException e) {
    e.printStackTrace();
  }
    return null;
  }

  
  
//  @Override
//  protected K selection(Population<T, K> pop) {
//    pop.getTotalWeight();
//    double randNumber = (rand.nextDouble() * pop.getTotalWeight()) + pop.size();
//    for(K ind : pop.getSortedList()) {
//      randNumber -= (ind.getFitness() + 1);
//      if(randNumber<=0)
//        return ind;
//    }
//    return null;
//  }
  
  @Override
  public K selection(Population<T, K> pop) {
    
  Population<T, K> tournament = new Population<>(pop.size(),fitnessCalc);

    // For each place in the tournament get a random individual
    for (int i = 0; i < selectionSize; i++) {
      tournament.add(pop.getSortedList().get(rand.nextInt(pop.getSortedList().size())));
    }
    
    // Get the fittest 
    return tournament.getSortedList().get(0);
  }

  
}
