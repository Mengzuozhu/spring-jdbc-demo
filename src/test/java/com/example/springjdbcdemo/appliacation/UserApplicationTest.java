package com.example.springjdbcdemo.appliacation;

import com.example.springjdbcdemo.config.UserConfiguration;
import com.example.springjdbcdemo.domain.user.User;
import com.example.springjdbcdemo.domain.user.UserQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * @author zuozhu.meng
 * @since 2020/12/9
 **/
@SpringBootTest
@ExtendWith(SpringExtension.class)
// @ActiveProfiles(value = "mysql")
@ActiveProfiles(value = "h2")
class UserApplicationTest {
    public static final int AGE = 24;
    public static final int BATCH_SIZE = 10;
    private static final String TEST = "test";
    @Autowired
    private UserApplication userApplication;

    @AfterEach
    void tearDown() {
        userApplication.deleteAll();
    }

    @Test
    void findById() {
        User user = userApplication.save(buildUser(TEST));
        Optional<User> result = userApplication.findById(user.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void findByName() {
        User user = userApplication.save(buildUser(TEST));
        List<User> users = userApplication.findByName(user.getName());
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(user.getName(), users.get(0).getName());
    }

    @Test
    void findByAge() {
        User user = userApplication.save(buildUser(TEST));
        List<User> users = userApplication.findByAge(user.getAge());
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(user.getAge(), users.get(0).getAge());
    }

    @Test
    void updateAge() {
        User user = userApplication.save(buildUser(TEST));
        int age = 26;
        userApplication.updateAge(user.getId(), age);
        Optional<User> result = userApplication.findById(user.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(age, result.map(User::getAge).get());
    }

    @Test
    void save() {
        User user = userApplication.save(buildUser(TEST));
        Assertions.assertNotNull(user);
    }

    @Test
    void saveAllTest() {
        Iterable<User> result = saveAll();
        Assertions.assertTrue(StreamSupport.stream(result.spliterator(), false)
                .map(User::getName)
                .allMatch(name -> name.startsWith(TEST))
        );
    }

    @Test
    void customFindShouldOne() {
        saveAll();
        String name = TEST + 0;
        List<User> users = userApplication.customFind(UserQuery.builder()
                .name(name)
                .age(AGE)
                .build());
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(AGE, users.get(0).getAge());
        Assertions.assertEquals(name, users.get(0).getName());
    }

    @Test
    void customFindShouldAll() {
        saveAll();
        List<User> users = userApplication.customFind(UserQuery.builder()
                .build());
        Assertions.assertEquals(BATCH_SIZE, users.size());
    }

    private Iterable<User> saveAll() {
        List<User> users = IntStream.range(0, BATCH_SIZE)
                .mapToObj(i -> buildUser(TEST + i))
                .collect(Collectors.toList());
        return userApplication.saveAll(users);
    }

    private User buildUser(String name) {
        return User.builder()
                .name(name)
                .remark("spring jdbc demo")
                .age(AGE)
                .build();
    }

    @SpringBootApplication(scanBasePackageClasses = {UserApplication.class, UserConfiguration.class})
    static class InnerConfig {
    }

}
