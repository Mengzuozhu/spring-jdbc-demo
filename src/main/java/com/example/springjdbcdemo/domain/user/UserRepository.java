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
     *
     * @param name the first name
     * @return the list
     */
    List<User> findByName(String name);

    /**
     * Find by id.
     *
     * @param id the id
     * @return the optional
     */
    Optional<User> findById(Long id);

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
