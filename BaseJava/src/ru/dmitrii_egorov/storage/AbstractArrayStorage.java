package ru.dmitrii_egorov.storage;

import java.util.Arrays;
import ru.dmitrii_egorov.exeption.ExistStorageExeption;
import ru.dmitrii_egorov.exeption.NotExistStorageExeption;
import ru.dmitrii_egorov.exeption.StorageExeption;
import ru.dmitrii_egorov.model.Resume;

public abstract class AbstractArrayStorage extends AbstractStorage <Integer> {

  protected static final int STORAGE_LIMIT = 10000;
  protected Resume[] storage = new Resume[STORAGE_LIMIT];
  protected int count;

  protected abstract Integer getSearchKey(final String uuid);

  protected abstract void insertElement(final Resume resume, final int index);

  protected abstract void deleteResume(final int index);

  @Override
  protected boolean isExist(Integer index) {
    return index >= 0;
  }

  @Override
  protected void doSave(Resume resume, Integer index) {
    if (count == STORAGE_LIMIT) {
      throw new StorageExeption("Хранилище с резюме переполнено", resume.getUuid());
    } else {
      insertElement(resume, index);
      count++;
    }
  }

  @Override
  protected Resume doGet(Integer index) {
    return storage[ index];
  }

  @Override
  protected void doDelete(Integer index) {
    deleteResume(index);
  }

  @Override
  public Resume[] getAll() {
    return Arrays.copyOf(storage, count);
  }

  @Override
  protected void doUpdate(Resume resume, Integer index) {
    storage[index] = resume;
  }

  @Override
  public int size() {
    return count;
  }

  @Override
  public void clear() {
    count = 0;
    Arrays.fill(storage, 0, count, null);
  }

}
