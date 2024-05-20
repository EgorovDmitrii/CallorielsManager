package JavaOOP.Section1_class;

import JavaOOP.Strucktures.DynamicArray;
import java.util.Arrays;

public class DynamicArrayTest {

  public static void main(String[] args) {
    DynamicArray dynamicArray = new DynamicArray();
    dynamicArray.add(4);
    dynamicArray.add(3);
    dynamicArray.add(8);
    dynamicArray.add(9);

    dynamicArray.add(new int[]{1, 4, 7, -5, -6});

    DynamicArray dynamicArray2 = new DynamicArray();
    dynamicArray2.add(5);
    dynamicArray2.add(78);
    dynamicArray2.add(25);
    dynamicArray2.add(15);
    dynamicArray2.add(95);

    //dynamicArray2.add();

    dynamicArray.add(dynamicArray2);

    System.out.println(Arrays.toString(dynamicArray.toArray()));
    dynamicArray.remove(8);
    System.out.println(Arrays.toString(dynamicArray.toArray()));

    dynamicArray.clear();
    System.out.println(Arrays.toString(dynamicArray.toArray()));
  }

}
