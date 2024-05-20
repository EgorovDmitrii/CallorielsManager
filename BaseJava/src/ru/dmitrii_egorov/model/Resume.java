package ru.dmitrii_egorov.model;

import java.util.Objects;
import java.util.UUID;

public class Resume {

  public String uuid;

  public Resume(String uuid) {
    this.uuid = uuid;
  }

  public Resume() {
    this(UUID.randomUUID().toString());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Resume resume = (Resume) o;
    return Objects.equals(uuid, resume.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid);
  }

  @Override
  public String toString() {
    return uuid;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

}
