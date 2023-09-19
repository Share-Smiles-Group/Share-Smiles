package com.sharesmiles.service;
import com.sharesmiles.model.User;
import com.sharesmiles.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Optional;
import java.util.regex.Pattern;

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
    
    private boolean isValidPassword(String password) {
        return (password.length() >= 6);
    }
    // 用户注册方法。
    public User userRegister(User user) {
        // 1. 检查username的格式
        if (!isValidName(user.getUsername()))
            throw new IllegalArgumentException("Username is not valid");

        // 2.检查email的格式
        if (!isValidEmail(user.getEmail()))
            throw new IllegalArgumentException("Email is not valid");
        
        // 3. 检查password的格式
        if (!isValidPassword(user.getPassword()))
            throw new IllegalArgumentException("Password is not valid");

        // 4. 检查email是否已经被使用
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) 
            // 非法状态异常，newEmail参数本身有效，但又有数据库状态，我们无法继续使用
            throw new IllegalStateException("Email already in use");
        
        // 处理与注册相关的业务逻辑，例如密码哈希、验证输入等。
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        // 处理完上述逻辑后，将user保存用户信息到数据库
        return userRepository.save(user);
    }

    public User userLogin(String email, String password) {
        // 根据名字找到该用户
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
        // 检查用户密码是否与输入密码一致
        if (passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("Invalid password");

        return user;
    }

    public User userProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return user;
    }

    private boolean isValidName(String name) {
        return !(name == null || name.length() < 1 || name.length() > 50);
    }

    public User userSetname(Long userId, String newName) {
        // 1. 验证名字格式是否有效
        if (!isValidName(newName))
            throw new IllegalArgumentException("Name should be betwwne 1 to 50 characters");

        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setUsername(newName);
        return userRepository.save(user);
    }

    private boolean isValidEmail(String email) {
        // 一个简单的电子邮件验证正则表达式
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    public User userSetEmail(Long userId, String newEmail) {
        // 1. 验证邮箱格式是否有效
        if (!isValidEmail(newEmail)) {
            // 非法参数异常，newEmail参数不满足该方法的预期格式或标准
            throw new IllegalArgumentException("Invalid email format");
        }

        // 2. 检查电子邮箱是否已被使用
        Optional<User> existingUser = userRepository.findByEmail(newEmail);
        if (existingUser.isPresent()) 
            // 非法状态异常，newEmail参数本身有效，但又有数据库状态，我们无法继续使用
            throw new IllegalStateException("Email already in use");
        
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setEmail(newEmail);
        return userRepository.save(user);
    }

}
