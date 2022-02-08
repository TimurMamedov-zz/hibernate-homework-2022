package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;

public class EmployerDao extends GenericDao {

  public EmployerDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public void updateTime(Employer employer) {
    Session session = getSession();

    for(Vacancy vacancy : employer.getVacancies()){
      session.createQuery("""
                  update versioned Vacancy
                  set archiving_time = :archivingTime
                  where id = :vacancyId
                  """)
              .setParameter("archivingTime", vacancy.getArchivingTime())
              .setParameter("vacancyId", vacancy.getId())
              .executeUpdate();
    }

    session.createQuery("""
                update versioned Employer
                set block_time = :blockTime
                where id = :employerId
                """)
            .setParameter("blockTime", employer.getBlockTime())
            .setParameter("employerId", employer.getId())
            .executeUpdate();
  }


  /**
   * TODO: здесь нужен метод, позволяющий сразу загрузить вакасии, связанные с работодателем и в некоторых случаях
   * избежать org.hibernate.LazyInitializationException
   * Также в запрос должен передаваться параметр employerId
   * <p>
   * https://vladmihalcea.com/the-best-way-to-handle-the-lazyinitializationexception/
   */
  public Employer getEager(int employerId) {

    return getSession()
            .createQuery("SELECT e FROM Employer e JOIN FETCH e.vacancies WHERE e.id = :employerId", Employer.class)
            .setParameter("employerId", employerId)
            .getSingleResult();
  }

}
