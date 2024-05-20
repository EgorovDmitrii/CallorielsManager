package ru.dmitrii_egorov.exeption;

public class StorageExeption extends RuntimeException{
  private final String uuid;


  public StorageExeption(String message, final String uuid) {
    super(message);
    this.uuid = uuid;
  }
}
