package ru.dmitrii_egorov.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import ru.dmitrii_egorov.model.Resume;

public class MapStorage extends AbstractStorage <String>{
private final Map<String, Resume> storage = new HashMap<>();

  @Override
  protected boolean isExist(String index) {
    return index != null && storage.containsKey(index);
  }

  @Override
  protected String getSearchKey(String uuid) {
    for (Entry<String, Resume> entry : storage.entrySet()) {

      if (entry.getValue().getUuid().equals(uuid)) {
        return entry.getKey();
      }
    }
    return null;
  }

  @Override
  protected void doSave(Resume resume, String index) {
    storage.put(resume.getUuid(), resume);
  }

  @Override
  protected Resume doGet(String index) {
    return storage.get(index);
  }

  @Override
  protected void doUpdate(Resume resume, String index) {
    storage.put(index, resume);
  }

  @Override
  protected void doDelete(String index) {
    storage.remove(index);
  }

  @Override
  public Resume[] getAll() {
    return storage.values().toArray(new Resume[0]);
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
