package com.example.springjdbcdemo.appliacation;

import com.example.springjdbcdemo.domain.user.User;
import com.example.springjdbcdemo.domain.user.UserQuery;
import com.example.springjdbcdemo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type User service.
 *
 * @author zuozhu.meng
 * @since 2020 /12/9
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userJdbcRepository;

    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<User> findById(Long id) {
        return userJdbcRepository.findById(id);
    }

    /**
     * Find by name.
     *
     * @param firstName the first name
     * @return the list
     */
    public List<User> findByName(String firstName) {
        return userJdbcRepository.findByName(firstName);
    }

    public List<User> findByAge(Integer age) {
        return userJdbcRepository.findByAge(age);
    }

    public void updateAge(Long id, Integer age) {
        userJdbcRepository.updateAge(id, age);
    }

    /**
     * Save.
     *
     * @param user the user
     * @return the user
     */
    public User save(User user) {
        return userJdbcRepository.save(user);
    }

    /**
     * Save all.
     *
     * @param iterable the iterable
     * @return the iterable
     */
    public Iterable<User> saveAll(Iterable<User> iterable) {
        return userJdbcRepository.batchSave(iterable);
    }

    /**
     * Custom find.
     *
     * @param userQuery the user query
     * @return the list
     */
    public List<User> customFind(UserQuery userQuery) {
        return userJdbcRepository.customFind(userQuery);
    }

    /**
     * Delete all.
     */
    public void deleteAll() {
        userJdbcRepository.deleteAll();
    }

}
