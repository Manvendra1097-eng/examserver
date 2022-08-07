package com.exam.controller;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // create user
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        Set<UserRole> userRoles = new HashSet<>();

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);
        userRoles.add(userRole);
       return userService.createUser(user,userRoles);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userService.getUser(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User updatedUser,@PathVariable("id") Long id) throws Exception {
        User user = userService.getUserById(id);
        if(user!=null){

            user.setUsername(updatedUser.getUsername());
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setProfile(updatedUser.getProfile());
            userService.updateUser(user);
            return user;
        }else{
            // create new employee
           user = createUser(user);
        }
        return user;
    }

//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<?> exceptionHandler(UserNotFoundException ex){
//
//    }
}
