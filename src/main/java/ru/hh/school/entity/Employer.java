package ru.hh.school.entity;

import org.hibernate.annotations.SelectBeforeUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "employer")
public class Employer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employer_id")
  private Integer id;

  @Version
  private short version;

  @Column(name = "company_name")
  private String companyName;

  // не используйте java.util.Date
  // https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html#basic-datetime-java8
  @Column(name = "creation_time")
  private LocalDateTime creationTime;

  @OneToMany(fetch = FetchType.LAZY,
          mappedBy = "employer",
          cascade = {CascadeType.ALL},
          orphanRemoval = true)
  private List<Vacancy> vacancies = new ArrayList<>();

  @Column(name = "block_time")
  private LocalDateTime blockTime;

  public Employer(){}

  public List<Vacancy> getVacancies() {
    return vacancies;
  }

  public Integer getId() {
    return id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public LocalDateTime getBlockTime() {
    return blockTime;
  }

  public void setBlockTime(LocalDateTime blockTime) {
    this.blockTime = blockTime;
  }

  public short getVersion() {
    return version;
  }

  public Employer setVersion(short version) {
    this.version = version;
    return this;
  }

  // статьи на тему реализации equals() и hashCode():
  //
  // https://vladmihalcea.com/hibernate-facts-equals-and-hashcode/
  // https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employer employer = (Employer) o;
    return Objects.equals(companyName, employer.companyName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyName);
  }

}
