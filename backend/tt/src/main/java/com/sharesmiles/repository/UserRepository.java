package com.sharesmiles.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sharesmiles.model.User;

// Responsitory是Spring Data JPA提供的简化数据库访问和操作的方式，
// 定义这样的接口可以减少大量的重复性数据库操作代码，同时提供丰富的方法实现查询操作
public interface UserRepository extends JpaRepository<User, Long>{
    // 继承JpaRepository的所有操作方法，比如save(), findAll(), delete()等
    // JpaRepository接受两个范型参数，第一个代表操作的实体类型: User, 第二个代表实体类主键的数据类型: Long
    Optional<User> findByUsername(String username); 
    // 根据username字段查询User的操作
    // Optional<User>: 查询结果可能存在，也可能不存在
    
}
