package ru.dmitrii_egorov.exeption;

public class NotExistStorageExeption extends StorageExeption{

  public NotExistStorageExeption(String uuid) {
    super("Резюме %s не существует".formatted(uuid), uuid);
  }
}
