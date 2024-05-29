package ru.dmitrii_egorov.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume>{

  private final String uuid;
  private final String fullName;
  private final Map <ContactType, String> contacts = new EnumMap<>(ContactType.class);
  private final Map <SectionType, Section> sections = new EnumMap<>(SectionType.class);

  public Resume(String uuid, String fullName) {
    Objects.requireNonNull(uuid, "uuid mast not be null");
    Objects.requireNonNull(fullName, "fullName mast not be null");
    this.uuid = uuid;
    this.fullName = fullName;
  }

  public Resume(final String fullName) {
    this(UUID.randomUUID().toString(), fullName);
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
    return Objects.equals(uuid, resume.uuid) && Objects.equals(fullName,
        resume.fullName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, fullName);
  }

  @Override
  public String toString() {
    return "Resume{uuid='%s', fullName='%s', contacts=%s, sections=%s}"
        .formatted(uuid, fullName, contacts, sections);
  }

  public String getUuid() {
    return uuid;
  }

  public String getFullName() {
    return fullName;
  }

  public String getContact(final ContactType type) {
    return contacts.get(type);
  }

  public Section getSection(final SectionType type) {
    return sections.get(type);
  }

  public void addContact(final ContactType type, final String value) {
    contacts.put(type, value);
  }

  public void addSection(final SectionType type, final Section section) {
    sections.put(type, section);
  }

  @Override
  public int compareTo(Resume o) {
    return this.uuid.compareTo(o.uuid);
  }
}
