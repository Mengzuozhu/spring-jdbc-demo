package com.example.springjdbcdemo.infrastruction;

import com.example.springjdbcdemo.domain.user.User;
import com.example.springjdbcdemo.domain.user.UserRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

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

}
