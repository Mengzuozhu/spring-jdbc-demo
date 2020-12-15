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
class UserServiceTest {
    public static final int AGE = 24;
    public static final int BATCH_SIZE = 10;
    private static final String TEST = "test";
    @Autowired
    private UserService userService;

    @AfterEach
    void tearDown() {
        userService.deleteAll();
    }

    @Test
    void findById() {
        User user = userService.save(buildUser(TEST));
        Optional<User> result = userService.findById(user.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void findByName() {
        User user = userService.save(buildUser(TEST));
        List<User> users = userService.findByName(user.getName());
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(user.getName(), users.get(0).getName());
    }

    @Test
    void findByAge() {
        User user = userService.save(buildUser(TEST));
        List<User> users = userService.findByAge(user.getAge());
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(user.getAge(), users.get(0).getAge());
    }

    @Test
    void updateAge() {
        User user = userService.save(buildUser(TEST));
        int age = 26;
        userService.updateAge(user.getId(), age);
        Optional<User> result = userService.findById(user.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(age, result.map(User::getAge).get());
    }

    @Test
    void save() {
        User user = userService.save(buildUser(TEST));
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
        List<User> users = userService.customFind(UserQuery.builder()
                .name(TEST + 0)
                .age(AGE)
                .build());
        Assertions.assertEquals(1, users.size());
    }

    @Test
    void customFindShouldAll() {
        saveAll();
        List<User> users = userService.customFind(UserQuery.builder()
                .build());
        Assertions.assertEquals(BATCH_SIZE, users.size());
    }

    private Iterable<User> saveAll() {
        List<User> users = IntStream.range(0, BATCH_SIZE)
                .mapToObj(i -> buildUser(TEST + i))
                .collect(Collectors.toList());
        return userService.saveAll(users);
    }

    private User buildUser(String name) {
        return User.builder()
                .name(name)
                .remark("spring jdbc demo")
                .age(AGE)
                .build();
    }

    @SpringBootApplication(scanBasePackageClasses = {UserService.class, UserConfiguration.class})
    static class InnerConfig {
    }

}
