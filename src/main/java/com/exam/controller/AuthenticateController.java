package com.exam.controller;

import com.exam.helper.JwtUtil;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    // generate token

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest){
         try{

             authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());

         }catch (UsernameNotFoundException e){
             throw new UsernameNotFoundException("User not found");
         } catch (Exception e) {
             throw new RuntimeException(e);
         }

         // authenticated user
       UserDetails userDetails = userDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    private void authenticate(String username, String password) throws Exception {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    username,password
            ));

        }catch (DisabledException e){

            throw new Exception("User Disabled");
        }catch (BadCredentialsException e){
            throw  new Exception("Bad credential");
        }
    }

    // return current logged user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return (User)userDetailService.loadUserByUsername(principal.getName());
    }
}
