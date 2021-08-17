package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory SessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = SessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.createSQLQuery("create table IF NOT EXISTS userTable(id BIGINT primary key auto_increment, name varchar(100), lastname varchar(100), age tinyint);").executeUpdate();
            tx1.commit();
        } catch (HibernateException e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = SessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.createSQLQuery("drop table IF EXISTS userTable;").executeUpdate();
            tx1.commit();
        } catch (HibernateException e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        Session session = SessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.save(user);
            tx1.commit();
        } catch (HibernateException e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = SessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        Transaction tx1 = session.beginTransaction();
        try {
            session.delete(user);
            tx1.commit();
        } catch (HibernateException e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = SessionFactory.openSession();
        List<User> users = session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = SessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.createSQLQuery("TRUNCATE TABLE userTable;").executeUpdate();
            tx1.commit();
        } catch (HibernateException e) {
            tx1.rollback();
        } finally {
            session.close();
        }
    }
}
