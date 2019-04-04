package ia.ga.impl.string;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import ia.ga.core.EvolutionAlgorithm;
import ia.ga.core.GeneticAlgorithmTemplate;
import ia.ga.impl.SimpleEvolutionAlgorithm;

public class Solution {

  public static void main(String[] args) throws InstantiationException, IllegalAccessException,InvocationTargetException, NoSuchMethodException {

    double crossoverRate = 0.3;
    double mutationRate = 0.1;
    int selectionSize = 10;
    StringCalculator fitnessCalc = new StringCalculator();

    EvolutionAlgorithm<Character, StringIndividual> evolutionAlgo = new SimpleEvolutionAlgorithm<>(crossoverRate,
        mutationRate, selectionSize, fitnessCalc, StringIndividual.class);

    GeneticAlgorithmTemplate<Character, StringIndividual> algorithm = new GeneticAlgorithmTemplate<>(fitnessCalc,
        evolutionAlgo, StringIndividual.class, 50, 100);

    List<Character> solution = algorithm.getSolution();
    System.out.println(solution);

  }

}
