package com.sharesmiles.service;
import com.sharesmiles.model.User;
import com.sharesmiles.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

// 服务层（Service Layer）类，服务层负责处理应用的业务逻辑，调用数据访问层（Data Access Layer）来与数据库进行交互。
// UserService类是应用的服务层部分，负责处理与用户相关的业务逻辑。例如，用户注册逻辑、查找用户等。
// 调用userRepository方法，与数据库进行交互，意味着业务逻辑和数据访问逻辑是分离的，使代码更加模块化。
public class UserService {
    @Autowired
    private UserRepository userRepository;
    // 一个调用repository的例子
    // public Optional<User> findByUsername(String username) {
    //     return userRepository.findByUsername(username);
    // }

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 用户注册方法。
    public User register(User user) {
        // 处理与注册相关的业务逻辑，例如密码哈希、验证输入等。
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // 处理完上述逻辑后，将user保存用户信息到数据库
        return userRepository.save(user);
    }

    public boolean login(String username, String password) {
        // 根据名字找到该用户
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // 验证密码
            if (passwordEncoder.matches(password, user.getPassword())) return true;
        }
        
        return false;
    }

}
