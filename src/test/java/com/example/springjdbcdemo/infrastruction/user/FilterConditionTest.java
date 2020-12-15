package com.example.springjdbcdemo.infrastruction.user;

import com.example.springjdbcdemo.domain.user.UserQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.data.relational.core.sql.Table;

/**
 * @author zuozhu.meng
 * @since 2020/12/15
 **/
class FilterConditionTest {
    private final Table table = SQL.table("user");

    @Test
    void and() {
        UserQuery userQuery = buildQuery();
        String userQueryName = userQuery.getName();
        Integer userQueryAge = userQuery.getAge();
        FilterCondition filterCondition = FilterCondition.create()
                .and(table.column("name").isEqualTo(SQL.literalOf(userQueryName)), userQueryName != null)
                .and(table.column("age").isEqualTo(SQL.literalOf(userQueryAge)), userQueryAge != null);
        Assertions.assertEquals("1 = 1 AND user.name = 'test'", filterCondition.toString());
    }

    @Test
    void or() {
        UserQuery userQuery = buildQuery();
        String userQueryName = userQuery.getName();
        Integer userQueryAge = userQuery.getAge();
        FilterCondition filterCondition = FilterCondition.create()
                .or(table.column("name").isEqualTo(SQL.literalOf(userQueryName)), userQueryName != null)
                .or(table.column("age").isEqualTo(SQL.literalOf(userQueryAge)), userQueryAge != null);
        Assertions.assertEquals("1 = 1 OR user.name = 'test'", filterCondition.toString());
    }

    private UserQuery buildQuery() {
        return UserQuery.builder()
                .name("test")
                .build();
    }
}
