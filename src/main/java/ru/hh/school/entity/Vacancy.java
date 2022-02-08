package ru.hh.school.entity;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "vacancy")
public class Vacancy {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vacancy_id")
  private Integer id;

  @Version
  private short version;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employer_id")
  private Employer employer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "area_id")
  private Area area;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "compensation_from")
  private Integer compensationFrom;

  @Column(name = "compensation_to")
  private Integer  compensationTo;

  @Column(name = "compensation_gross")
  private Boolean compensationGross;

  @Column(name = "creation_time")
  private LocalDateTime creationTime;

  @Column(name = "archiving_time")
  private LocalDateTime archivingTime;

  public Vacancy() {
  }

  public Vacancy(Employer employer) {
    this.employer = employer;
  }

  public void setArea(Area area) {
    this.area = area;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCompensationFrom(Integer compensationFrom) {
    this.compensationFrom = compensationFrom;
  }

  public void setCompensationTo(Integer compensationTo) {
    this.compensationTo = compensationTo;
  }

  public LocalDateTime getArchivingTime() {
    return archivingTime;
  }

  public void setArchivingTime(LocalDateTime archivingTime) {
    this.archivingTime = archivingTime;
  }

  public short getVersion() {
    return version;
  }

  public Vacancy setVersion(short version) {
    this.version = version;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Vacancy vacancy = (Vacancy) o;
    return Objects.equals(title, vacancy.title)
            && Objects.equals(description, vacancy.description)
            && Objects.equals(employer, vacancy.employer)
            && Objects.equals(area, vacancy.area);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, description, employer, area);
  }

}
