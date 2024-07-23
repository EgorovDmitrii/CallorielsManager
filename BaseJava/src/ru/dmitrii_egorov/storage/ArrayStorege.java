package ru.dmitrii_egorov.storage;


import ru.dmitrii_egorov.model.Resume;

public class ArrayStorege extends AbstractArrayStorage {
  protected Integer getSearchKey(final String uuid) {
    for (int i = 0; i < count; i++) {
      if (storage[i].getUuid().equals(uuid)) {
        return i;
      }
    }
    return -1;
  }

  @Override
  protected void insertElement(Resume resume, int index) {
    storage[count] = resume;
  }


  protected void deleteResume(final int index) {
    if (index == count - 1) {
      storage[--count] = null;
    } else {
      System.arraycopy(storage, index + 1, storage, index, count - index - 1);
      count--;
    }
  }


}
