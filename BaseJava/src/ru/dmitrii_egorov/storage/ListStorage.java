package ru.dmitrii_egorov.storage;

import java.util.ArrayList;
import java.util.List;
import ru.dmitrii_egorov.model.Resume;

public class ListStorage extends AbstractStorage {

  protected List<Resume> storage = new ArrayList<>();

  @Override
  protected int indexOf(String uuid) {
    for (int i = 0; i < storage.size(); i++) {
      if (storage.get(i).uuid.equals(uuid)) {
        return i;
      }
    }
    return -1;
  }


  @Override
  protected void doSave(Resume resume, int index) {
    storage.add(resume);
  }

  @Override
  protected Resume doGet(int index) {
    return storage.get(index);
  }

  @Override
  protected void doUpdate(Resume resume, int index) {
    storage.set(index, resume);
  }

  @Override
  protected void doDelete(int index) {
    storage.remove(index);
  }

  @Override
  public Resume[] getAll() {
    return storage.toArray(new Resume[0]);
  }

  @Override
  public void clear() {
    storage.clear();
  }

  @Override
  public int size() {
    return storage.size();
  }
}
