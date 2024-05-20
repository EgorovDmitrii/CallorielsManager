package ru.dmitrii_egorov.storage;

import ru.dmitrii_egorov.exeption.ExistStorageExeption;
import ru.dmitrii_egorov.exeption.NotExistStorageExeption;
import ru.dmitrii_egorov.model.Resume;

public abstract class AbstractStorage implements Storage {

  protected abstract int indexOf(final String uuid);

  protected abstract void doSave(final Resume resume, final int index);

  protected abstract Resume doGet(final int index);

  protected abstract void doUpdate(final Resume resume, final int index);

  protected abstract void doDelete(final int index);

  @Override
  public void save(Resume resume) {
    final var index = indexOf(resume.uuid);
    if (index >= 0) {
      throw new ExistStorageExeption(resume.uuid);
    } else {
      doSave(resume, index);
    }

  }

  @Override
  public Resume get(String uuid) {
    final var index = indexOf(uuid);
    if (index < 0) {
      throw new NotExistStorageExeption(uuid);
    } else {
      return doGet(index);
    }
  }


  @Override
  public void update(Resume resume) {
    final var index = indexOf(resume.getUuid());

    if (index < 0) {
      throw new NotExistStorageExeption(resume.uuid);
    } else {
      doUpdate(resume, index);
    }
  }

  @Override
  public void delete(String uuid) {
    final var index = indexOf(uuid);

    if (index < 0) {
      throw new NotExistStorageExeption(uuid);
    } else {
      doDelete(index);
    }

  }
}
