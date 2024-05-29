package ru.dmitrii_egorov.storage;

import java.util.Arrays;
import ru.dmitrii_egorov.model.Resume;

public class SortedArrayStorage extends AbstractArrayStorage {

  @Override
  protected Integer getSearchKey(String uuid) {
    final var serchKey = new Resume(uuid);
    return Arrays.binarySearch(storage, 0, count, serchKey);
  }

  @Override
  protected void insertElement(Resume resume, int index) {
   // https://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array - -index - 1 //doc
    int insertIndex = -index - 1;
    System.arraycopy(storage, insertIndex, storage, insertIndex + 1, count - insertIndex);
    storage[insertIndex] = resume;
  }

  @Override
  protected void deleteResume(int index) {
    int numMoved = count - index - 1; // Если numMoved = 0 то тогда это последний элемент(тогда ничего сдвигать не надо)
    if (numMoved > 0) {
     System.arraycopy(storage, index + 1, storage, index, numMoved);
    }
  }

}
