package ru.dmitrii_egorov.storage;

import ru.dmitrii_egorov.exeption.ExistStorageExeption;
import ru.dmitrii_egorov.exeption.NotExistStorageExeption;
import ru.dmitrii_egorov.model.Resume;

public abstract class AbstractStorage <SK> implements Storage {

  protected abstract boolean isExist(final SK index);

  protected abstract SK getSearchKey(final String uuid);

  protected abstract void doSave(final Resume resume, final SK index);

  protected abstract Resume doGet(final SK index);

  protected abstract void doUpdate(final Resume resume, final SK index);

  protected abstract void doDelete(final SK index);

  @Override
  public void save(Resume resume) {
    final var index = getSearchKey(resume.getUuid());

    if (isExist(index)) {
      throw new ExistStorageExeption(resume.getUuid());
    } else {
      doSave(resume, index);
    }
  }


  @Override
  public Resume get(String uuid) {
    final var index = getSearchKey(uuid);

    if (!isExist(index)) {
      throw new NotExistStorageExeption(uuid);
    } else {
      return doGet(index);
    }
  }


  @Override
  public void update(Resume resume) {
    final var index = getSearchKey(resume.getUuid());

    if (!isExist(index)) {
      throw new NotExistStorageExeption(resume.getUuid());
    } else {
      doUpdate(resume, index);
    }
  }


  @Override
  public void delete(String uuid) {
    final var index = getSearchKey(uuid);

    if (!isExist(index)) {
      throw new NotExistStorageExeption(uuid);
    } else {
      doDelete(index);
    }
  }
}
