package com.example.springjdbcdemo.infrastruction.user;

import com.example.springjdbcdemo.domain.user.User;
import com.example.springjdbcdemo.domain.user.UserRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * The interface User jdbc repository.
 *
 * @author zuozhu.meng
 * @since 2020 /12/9
 */
public interface UserJdbcRepository extends PagingAndSortingRepository<User, Long>, UserRepository,
        CustomUserRepository {

    /**
     * Batch save.
     *
     * @param entities the entities
     * @return the iterable
     */
    @Override
    default Iterable<User> batchSave(Iterable<User> entities) {
        return saveAll(entities);
    }

    /**
     * Find by age.
     * user @Query
     *
     * @param age the age
     * @return the list
     */
    @Query("SELECT * FROM user WHERE age = :age")
    @Override
    List<User> findByAge(Integer age);

    /**
     * Update age.
     * use @Query and @Modifying
     *
     * @param id  the id
     * @param age the age
     */
    @Override
    @Modifying
    @Query("update user set age = :age where id = :id")
    void updateAge(Long id, Integer age);

}
