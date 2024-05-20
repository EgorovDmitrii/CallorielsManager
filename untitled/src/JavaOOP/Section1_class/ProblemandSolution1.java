package JavaOOP.Section1_class;

import JavaOOP.Strucktures.DynamicArray;
import java.util.Arrays;

public class ProblemandSolution1 {

  public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 4, 5};

    DynamicArray findpositivenumbers = findpositivenumbers(array);
    System.out.println(Arrays.toString(findpositivenumbers.toArray()));
  }
  private static DynamicArray findpositivenumbers(int[] ar) {
    DynamicArray dynamicArray = new DynamicArray();

    for (int element : ar) {
      if (element >= 0) {
        dynamicArray.add(element);
      }
    }

    return dynamicArray;
  }


}
