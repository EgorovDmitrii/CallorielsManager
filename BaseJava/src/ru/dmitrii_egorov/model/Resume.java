package ru.dmitrii_egorov.model;

import java.time.Month;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import ru.dmitrii_egorov.model.Organization.JobTitle;

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
    return "Resume{\n\tuuid='%s', \n\tfullName='%s', \n\tcontacts=%s, \n\tsections=%s\n}"
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

  public static void main(String[] args) {
    Resume resume = new Resume("Дмитрий");
    resume.addContact(ContactType.SKYPE, "Zerocoloring");
    resume.addContact(ContactType.MOBILE, "+925 111111111");
    resume.addSection(SectionType.QUALIFICATION, new ListSection("Java", "SQL", "Collection", "Git"));

    resume.addSection(SectionType.EXPERIANCE,
        new OrganizationSection(
            new Organization("Гулливер", "https://luxkod.ru",
                new JobTitle(2023, Month.JANUARY, 2023, Month.MAY, "Директор", "Описание" ))));
    System.out.println(resume);
  }
}


