package org.example.repository;

import lombok.AllArgsConstructor;
import org.example.model.User;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private Session session;
    public void saveUser(User user) {
        session.beginTransaction();
        // Save object to DB
        session.persist(user);
        // Commit transaction
        session.getTransaction().commit();
    }

    public List<User> getAll() {
        // HQL-querry
        String hql = "FROM User";
        session.beginTransaction();
        List<User> entities = session.createQuery(hql, User.class).getResultList();
        return entities;
    }

    public Optional<User> getById(Long id) {
        User maybeUser = session.find(User.class, id);
        Optional<User> optionalUser = Optional.ofNullable(maybeUser);
        return optionalUser;
    }

    public void updateUser(Long id, User user) {
        Optional<User> maybeUser = getById(id);
        if (maybeUser.isPresent()) {
            User updateUser = maybeUser.get();
            updateUser.setUsername(user.getUsername());
            updateUser.setPhone(user.getPhone());
            session.getTransaction().commit();
        }
    }

    public void delete(Long id) {
        session.beginTransaction();
        User maybeUser = session.find(User.class, id);

        if (maybeUser != null) {
            session.remove(maybeUser);
            System.out.println("Запись с ID " + id + " успешно удалена.");
        } else {
            System.out.println("Запись с ID " + id + " не найдена.");
        }

        session.getTransaction().commit();
    }
}
