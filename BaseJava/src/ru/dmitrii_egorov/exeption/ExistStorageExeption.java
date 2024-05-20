package ru.dmitrii_egorov.exeption;

public class ExistStorageExeption extends StorageExeption{

  public ExistStorageExeption(String uuid) {
    super("Резюме %s уже существует".formatted(uuid), uuid);
  }
}
