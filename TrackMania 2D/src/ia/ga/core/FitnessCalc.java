package ia.ga.core;

public interface FitnessCalc<T> {

    Integer getFitness(Individual<T> individual);
    
    Integer getMaxFitness();

}