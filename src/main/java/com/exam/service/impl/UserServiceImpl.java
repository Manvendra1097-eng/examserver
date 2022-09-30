package com.exam.service.impl;

import com.exam.helper.UserFoundException;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws UserFoundException {

        // check if username is available or not in database
        User local = this.userRepository.findByUsername(user.getUsername());
        if (local != null) {
            System.out.println("User already present");
            throw new UserFoundException();
        } else {
            // user create
            for (UserRole ur : userRoles) {
                Role role = ur.getRole();
                this.roleRepository.save(role);
            }

            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        if(!user.isPresent()){
            throw new RuntimeException("User not found");
        }else{
            return user.get();
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
