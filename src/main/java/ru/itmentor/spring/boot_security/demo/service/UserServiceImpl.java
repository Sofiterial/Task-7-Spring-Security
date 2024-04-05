package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Override
    public List<User> getUserAndRoles() {
        return userRepository.listUsersAndRoles();
    }

    @Override
    public User readUser(long id) {
        Optional<User> userOptional =userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public void delete(long id) {

        userRepository.deleteById(id);

    }

    @Override
    public void update(int id, User user) {
        user.setId((long) id);
        userRepository.save(user);

    }
}
