package com.example.springjdbcdemo.infrastruction.user;

import com.example.springjdbcdemo.domain.user.User;
import com.example.springjdbcdemo.domain.user.UserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.convert.EntityRowMapper;
import org.springframework.data.jdbc.core.convert.JdbcConverter;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;
import org.springframework.data.relational.core.mapping.RelationalPersistentEntity;
import org.springframework.data.relational.core.sql.*;
import org.springframework.data.relational.core.sql.render.SqlRenderer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zuozhu.meng
 * @since 2020/12/14
 **/
@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private final SqlRenderer sqlRenderer = SqlRenderer.create();
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final RelationalMappingContext context;
    private final JdbcConverter converter;

    @Override
    public List<User> customFind(UserQuery userQuery) {
        Table table = Table.create(getRequiredPersistentEntity(User.class).getTableName());
        Column name = table.column(User.Fields.name);
        Column age = table.column(User.Fields.age);
        String userQueryName = userQuery.getName();
        Integer userQueryAge = userQuery.getAge();
        FilterCondition filterCondition = FilterCondition.create()
                .and(name.isEqualTo(SQL.literalOf(userQueryName)), userQueryName != null)
                .and(age.isEqualTo(SQL.literalOf(userQueryAge)), userQueryAge != null);
        Select select = StatementBuilder.select()
                .select(name, age)
                .from(table)
                .where(filterCondition)
                .build();
        return query(select);
    }

    @SuppressWarnings("unchecked")
    private List<User> query(Select select) {
        RowMapper<User> entityRowMapper = (RowMapper<User>) getEntityRowMapper(User.class);
        return namedParameterJdbcOperations.query(sqlRenderer.render(select), entityRowMapper);
        // you can also use BeanPropertyRowMapper
        // return namedParameterJdbcTemplate.query(sqlRenderer.render(select), getBeanPropertyRowMapper(User.class));
    }

    private EntityRowMapper<?> getEntityRowMapper(Class<?> type) {
        return new EntityRowMapper<>(getRequiredPersistentEntity(type), converter);
    }

    private RelationalPersistentEntity<?> getRequiredPersistentEntity(Class<?> type) {
        return context.getRequiredPersistentEntity(type);
    }

    private <T> BeanPropertyRowMapper<T> getBeanPropertyRowMapper(Class<T> mappedClass) {
        return BeanPropertyRowMapper.newInstance(mappedClass);
    }

}
