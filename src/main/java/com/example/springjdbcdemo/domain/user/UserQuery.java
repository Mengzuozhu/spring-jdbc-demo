package com.example.springjdbcdemo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zuozhu.meng
 * @since 2020/12/14
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery {
    private String name;
    private Integer age;

}
