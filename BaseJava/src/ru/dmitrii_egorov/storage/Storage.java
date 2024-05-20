package ru.dmitrii_egorov.storage;

import ru.dmitrii_egorov.model.Resume;

public interface Storage {
  void save(final Resume resume);
  Resume get(String uuid);
  Resume[] getAll();
  void update(final Resume resume);
  void delete(String uuid);
  void clear();
  int size();

}


