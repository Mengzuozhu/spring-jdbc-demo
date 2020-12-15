package com.example.springjdbcdemo.infrastruction.user;

import com.example.springjdbcdemo.domain.user.User;
import com.example.springjdbcdemo.domain.user.UserQuery;

import java.util.List;

/**
 * The interface Custom user repository.
 *
 * @author zuozhu.meng
 * @since 2020 /12/14
 */
public interface CustomUserRepository {
    /**
     * Custom find.
     *
     * @param userQuery the user query
     * @return the list
     */
    List<User> customFind(UserQuery userQuery);
}
