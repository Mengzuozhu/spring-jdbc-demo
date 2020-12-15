package com.example.springjdbcdemo.appliacation;

import com.example.springjdbcdemo.domain.user.User;
import com.example.springjdbcdemo.domain.user.UserQuery;
import com.example.springjdbcdemo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author zuozhu.meng
 * @since 2020/12/9
 **/
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userJdbcRepository;

    public List<User> findByName(String firstName) {
        return userJdbcRepository.findByName(firstName);
    }

    public Optional<User> findById(Long id) {
        return userJdbcRepository.findById(id);
    }

    public User save(User s) {
        return userJdbcRepository.save(s);
    }

    public Iterable<User> saveAll(Iterable<User> iterable) {
        return userJdbcRepository.batchSave(iterable);
    }

    public List<User> customFind(UserQuery userQuery) {
        return userJdbcRepository.customFind(userQuery);
    }

    public void deleteAll() {
        userJdbcRepository.deleteAll();
    }

}
