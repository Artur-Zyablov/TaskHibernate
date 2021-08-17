package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;


public class UserServiceImpl implements UserService {
    private UserDao userDaoImpl = new UserDaoHibernateImpl();
    //private UserDao userDaoImpl = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDaoImpl.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoImpl.cleanUsersTable();
    }
}
