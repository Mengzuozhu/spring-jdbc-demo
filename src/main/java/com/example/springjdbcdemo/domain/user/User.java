package com.example.springjdbcdemo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

/**
 * @author zuozhu.meng
 * @since 2020/12/9
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class User {
    @Id
    private Long id;
    private String name;
    private String remark;
    private Integer age;
    @LastModifiedDate
    private Instant modifyTime;
    @CreatedDate
    private Instant createTime;

}
