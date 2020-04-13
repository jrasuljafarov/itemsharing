package com.itemsharing.userservice.service;

import com.itemsharing.userservice.model.Role;
import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.model.UserRole;
import com.itemsharing.userservice.repository.UserRepository;
import com.itemsharing.userservice.utility.SecurityUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {

        User userLocal = userRepository.findUserByUsername(user.getUsername());

        if (userLocal != null) {
            log.info("User with username {} already exists !", userLocal.getUsername());
        } else {
            Set<UserRole> userRoles = new HashSet<>();
            Role role = new Role();
            role.setRoleId(1);
            userRoles.add(new UserRole(user, role));
            user.getUserRoles().addAll(userRoles);

            String encryptPassword = SecurityUtility.passwordEncoder().encode(user.getPassword());
            user.setPassword(encryptPassword);
            userLocal = userRepository.save(user);
            log.debug("save user response: {}",userLocal);
        }

        return userLocal;
    }

    @Override
    public User getUserByUserName(String username) {
        log.info("findUser by username: {}", username);
        return userRepository.findUserByUsername(username);
    }

}
