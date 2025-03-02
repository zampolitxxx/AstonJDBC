package org.example;

import org.example.repository.UserRepository;
import org.example.utils.ConnManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.example.model.User;

import java.util.List;
import java.util.Optional;

public class App {

    private static final Long ID = 1L;

    public static void main(String[] args) {
        SessionFactory sessionFactory = ConnManager.getConnection();
        Session session = sessionFactory.openSession();
        UserRepository userRepository = new UserRepository(session);

        // Create user
        System.out.println("\n" + "Создание нового пользователя");
        User user = new User();
        user.setUsername("zampolit");
        user.setPhone("89999999999");
        userRepository.saveUser(user);
        List<User> users = userRepository.getAll();
        Printer.printUsers(users);

        // Update user
        System.out.println("Изменение имени и телефона пользователя");
        Optional<User> maybeUser = userRepository.getById(ID);
        if (maybeUser.isPresent()) {
            User updateUser = maybeUser.get();
            updateUser.setUsername("newUsername");
            updateUser.setPhone("newPhone");
            userRepository.updateUser(ID, updateUser);
            Printer.printUser(updateUser);
        } else {
            System.out.println("Пользователь не найден");
        }

        // Delete user
        System.out.println("Удаление пользователя");
        userRepository.delete(ID);

        // Show all users
        System.out.println("Показ всех пользователей");
        List<User> finalUserList = userRepository.getAll();
        Printer.printUsers(finalUserList);

        session.close();
        sessionFactory.close();
    }
}
