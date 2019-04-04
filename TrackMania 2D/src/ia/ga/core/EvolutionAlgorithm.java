package ia.ga.core;

public abstract class EvolutionAlgorithm<T, K extends Individual<T>> {

  /* GA parameters */
  protected final double crossoverRate;
  protected final double mutationRate;
  protected final FitnessCalc<T> fitnessCalc;

  public EvolutionAlgorithm(double crossoverRate, double mutationRate, FitnessCalc<T> fitnessCalc) {
    this.crossoverRate = crossoverRate;
    this.mutationRate = mutationRate;
    this.fitnessCalc = fitnessCalc;
  }

  /* Public methods */

  // Evolve a population
  public Population<T, K> evolvePopulation(Population<T, K> pop) {

    Population<T, K> newPopulation = new Population<>(pop.size(), fitnessCalc);
    
    newPopulation.add(pop.getSortedList().get(0));
    // Crossover population
    for (int i = 1; i < pop.size(); i++) {
      //ooo a baby how cute!!!!
      K newIndividual = crossover(selection(pop), selection(pop));
      //oh no i dropped it in the radioactive pool 
      newIndividual.mutate(mutationRate);
      //well that will be our next generation with all the radioactive babies
      newPopulation.add(newIndividual);
    }

    return newPopulation;
  }

  //SEX SEX SEX SEX SEX SEX SEX SEX
  protected abstract K crossover(K indiv1, K indiv2);
  
  //Hey beautiful
  protected abstract K selection(Population<T, K> pop);

  
}