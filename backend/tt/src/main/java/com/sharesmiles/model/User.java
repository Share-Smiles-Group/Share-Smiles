package com.sharesmiles.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// 表示该类是一个实体类，它将会映射到数据库中的一个表，告诉JPA这个类应该被持久化
public class User {
    @Id 
    // 表示该字段是实体类的唯一标识符，对应于数据库表中的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // GeneratedValue 为实体的ID字段自动生成值
    // GenerationType.IDENTITY 利用数据库本身的递增机制
    // 当一个新的实体被持久化（保存到数据库）时，ID字段会被留空。当这个实体真正被插入到数据库时，数据库会自动为这个ID字段生成一个新的、唯一的值
    // 性能：这种策略依赖于数据库的机制，所以它通常具有很好的性能，尤其是在高并发的情况下
    private Long id;
    private String username;
    private String password;
    private String email;

    // Constructors
    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
