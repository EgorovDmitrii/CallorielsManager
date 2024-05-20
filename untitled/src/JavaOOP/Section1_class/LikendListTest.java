package JavaOOP.Section1_class;

import JavaOOP.Strucktures.LikendListCoustom;
import java.util.Arrays;

public class LikendListTest {

  public static void main(String[] args) {
    LikendListCoustom list = new LikendListCoustom();
    list.add(4);
    list.add(8);
    list.add(10);

    System.out.println(list.asString());
    list.remove(8);


    System.out.println(list.asString());
  }

}
