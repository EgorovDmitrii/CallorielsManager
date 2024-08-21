package ru.dmitrii_egorov.manager.repository;

import ru.dmitrii_egorov.manager.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    boolean delete(int id);
}
