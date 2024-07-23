package ru.dmitrii_egorov.storage;

import java.util.ArrayList;
import java.util.List;
import ru.dmitrii_egorov.model.Resume;

public class ListStorage extends AbstractStorage<Integer> {

  protected List<Resume> storage = new ArrayList<>();

  @Override
  protected boolean isExist(Integer index) {
    return index >= 0;
  }

  @Override
  protected Integer getSearchKey(String uuid) {
    for (int i = 0; i < storage.size(); i++) {

      if (storage.get(i).getUuid().equals(uuid)) {
        return i;
      }
    }
    return -1;
  }


  @Override
  protected void doSave(Resume resume, Integer index) {
    storage.add(resume);
  }

  @Override
  protected Resume doGet(Integer index) {
    return storage.get(index);
  }

  @Override
  protected void doUpdate(Resume resume, Integer index) {
    storage.set(index, resume);
  }

  @Override
  protected void doDelete(Integer index) {
    storage.remove(index.intValue());
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
