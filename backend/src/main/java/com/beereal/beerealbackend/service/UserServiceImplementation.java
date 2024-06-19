package com.beereal.beerealbackend.service;

import com.beereal.beerealbackend.model.User;
import com.beereal.beerealbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Dodaje użytkownika do bazy danych
     * @param user obiekt użytkownika
     * @return dodawany obiekt
     */
    @Override
    public User registerUser(User user) {
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        return userRepository.save(user);
    }

    @Override
    public User getUserByID(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    /**
     * Autentykuje użytkownika, sprawdza czy podane w argumentach hasło zgadza się z tym z pobranych z bazy danych
     * @param username nazwa użytkownika, którego hasło z bazy danych zostanie pobrane do porównania
     * @param password porównywane hasło
     * @return boolean - hasło się zgadza lub nie
     */
    @Override
    public boolean authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty())
            return false;
        User user = userOptional.get();
        return passwordEncoder.matches(password, user.getPassword());
    }

    /**
     *
     * @return Lista wszystkich użytkowników
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * Zwraca obiekt użytkownika o podanej nazwie
     * @param username nazwa użytkownika, którego obiekt chce się pozyskać
     * @return Obiekt użytkownika o podanej nazwie
     */
    @Override
    public User findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null);
    }
}
