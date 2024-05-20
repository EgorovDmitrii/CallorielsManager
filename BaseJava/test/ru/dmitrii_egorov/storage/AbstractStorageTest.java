package ru.dmitrii_egorov.storage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import ru.dmitrii_egorov.exeption.ExistStorageExeption;
import ru.dmitrii_egorov.exeption.NotExistStorageExeption;
import ru.dmitrii_egorov.model.Resume;

public abstract class AbstractStorageTest {
private static final String UUID_1 = "001";
private static final String UUID_2 = "002";
private static final String UUID_3 = "003";
private static final String UUID_4 = "004";

private static final Resume RESUME_1 = new Resume(UUID_1);
private static final Resume RESUME_2 = new Resume(UUID_2);
private static final Resume RESUME_3 = new Resume(UUID_3);
private static final Resume RESUME_4 = new Resume(UUID_4);

private final Storage storage;

  public AbstractStorageTest(Storage storage) {
    this.storage = storage;
  }

  @Before
  public void init() {
    storage.clear();
    storage.save(RESUME_1);
    storage.save(RESUME_2);
    storage.save(RESUME_3);

  }

  @Test
  public void save() {
    storage.save(RESUME_4);
    assertSize(4);
    assertGet(RESUME_4);
  }

  @Test(expected = ExistStorageExeption.class)
  public void saveIfResumeAlreadyExist() {
    storage.save(RESUME_2);
  }

  @Test
  public void get() {
    assertGet(RESUME_1);
    assertGet(RESUME_2);
    assertGet(RESUME_3);
  }

  @Test(expected = NotExistStorageExeption.class)
  public void getResumeIfUuidNotExist() {
    storage.get("Dummy");
  }

  @Test
  public void update() {
    Resume newResume = new Resume(UUID_1);
    storage.update(newResume);
    assertSame(newResume, storage.get(UUID_1));
  }

  @Test
  public void delete() {
  }

  private void assertSize(final int size) {
    assertEquals(size, storage.size());
  }

  private void assertGet(final Resume resume) {
    assertEquals(resume, storage.get(resume.getUuid()));
  }
}