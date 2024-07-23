package ru.dmitrii_egorov.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ru.dmitrii_egorov.model.Resume;
import ru.dmitrii_egorov.storage.AbstractArrayStorage;
import ru.dmitrii_egorov.storage.ArrayStorege;
import ru.dmitrii_egorov.storage.ListStorage;
import ru.dmitrii_egorov.storage.SortedArrayStorage;
import ru.dmitrii_egorov.storage.Storage;

public class MainTestArrayge {

  private final static Storage ARRAY_STOREGE = new SortedArrayStorage() {


    @Override
    protected void insertElement(Resume resume, int index) {

    }

    @Override
    protected void deleteResume(int index) {

    }
  };

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Resume resume;

    while (true) {
      System.out.print(
          "Ввведите одну из команд: {list | size | save param | delete param | get param | clear | exit}: ");
      String[] params = reader.readLine().trim().toLowerCase().split(" ");

      if (params.length < 1 || params.length > 3) {
        System.out.println("Неверная команда");
        continue;
      }

      String param = null;
      if (params.length > 1) {
        param = params[1].intern();
      }

      switch (params[0]) {
        case "list":
          printAll();
          break;
        case "size":
          System.out.println(ARRAY_STOREGE.size());
          break;
        case "save":
          resume = new Resume(param);
          ARRAY_STOREGE.save(resume);
          printAll();
          break;
        case "delete":
          ARRAY_STOREGE.delete(param);
          printAll();
          break;
        case "get":
          System.out.println(ARRAY_STOREGE.get(param));
          break;
        case "clear":
          ARRAY_STOREGE.clear();
          printAll();
          break;
        case "exit":
          return;
        default:
          System.out.println("Неверная команда");
          break;
      }
    }
  }

  private static void printAll() {
    Resume[] all = ARRAY_STOREGE.getAll();
    System.out.println("-------------------------------------------------------------------");

    if (all.length == 0) {
      System.out.println("Пустой");
    } else {
      for (Resume resume : all) {
        System.out.println(resume);
      }
    }
    System.out.println("-------------------------------------------------------------------");
  }


}
