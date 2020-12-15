package com.example.springjdbcdemo.domain.user;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author zuozhu.meng
 * @since 2020 /12/14
 */
public interface UserRepository {
    /**
     * Save.
     *
     * @param user the user
     * @return the user
     */
    User save(User user);

    /**
     * Batch save.
     *
     * @param entities the entities
     * @return the iterable
     */
    Iterable<User> batchSave(Iterable<User> entities);

    /**
     * Find by name.
     * JDBC处理成SQL语句：SELECT * FROM user WHERE name = :name
     *
     * @param name the first name
     * @return the list
     */
    List<User> findByName(String name);

    /**
     * Find by age.
     *
     * @param age the name
     * @return the list
     */
    List<User> findByAge(Integer age);

    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     */
    Optional<User> findById(Long id);

    /**
     * Update age.
     *
     * @param id  the id
     * @param age the age
     */
    void updateAge(Long id, Integer age);

    /**
     * Custom find.
     *
     * @param userQuery the user query
     * @return the list
     */
    List<User> customFind(UserQuery userQuery);

    /**
     * Delete all.
     */
    void deleteAll();
}
