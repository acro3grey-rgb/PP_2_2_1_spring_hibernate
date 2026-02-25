package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      Query<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
      return query.getResultList();
   }

   @Override
   public User findUserByCar(String model, int series) {
      return sessionFactory.getCurrentSession()
              .createQuery(
                      "select u from User u join u.car c " +
                              "where c.model = :model and c.series = :series",
                      User.class
              )
              .setParameter("model", model)
              .setParameter("series", series)
              .setMaxResults(1)
              .uniqueResult();
   }
}