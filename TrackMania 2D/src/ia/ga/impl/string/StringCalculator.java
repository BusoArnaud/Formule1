package ia.ga.impl.string;

import java.util.List;

import ia.ga.core.FitnessCalc;
import ia.ga.core.Individual;

public class StringCalculator implements FitnessCalc<Character>{

  String solution = "jesuissuper";
  
  @Override
  public Integer getFitness(Individual<Character> individual) {
    return solution.length()
        - hammingDist(solution.toCharArray(), convertToCharArray(individual.getChromosome()));
  }

  @Override
  public Integer getMaxFitness() {
    return solution.length();
  }
  
  private static int hammingDist(char str1[], char str2[]) {
    int count = 0;
    for (int i = 0; i < str1.length; i++) {
      if (str1[i] != str2[i])
        count++;
    }
    return count;
  }
  
  private static char[] convertToCharArray(List<Character> characters) {
    char[] arr = new char[characters.size()];
    for(int i = 0; i < characters.size(); i++ )
      arr[i] = characters.get(i).charValue();
    return arr;
  }

}
