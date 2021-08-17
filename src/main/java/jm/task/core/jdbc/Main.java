package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        User[] users = { new User("Ivan","Ivanov", (byte) 10),
                         new User("Petr","Petrov", (byte) 20),
                         new User("Semen","Sidorov", (byte) 30),
                         new User("Petr","Ivanov", (byte) 40) };
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        for (User user : users ) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.printf("User с именем – %s добавлен в базу данных\n",user.getName());
        }
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.shutdown();
    }
}
