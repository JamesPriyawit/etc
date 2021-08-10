package com.example.etc.configs;

import com.cupcodeteam.treasuristservice.entities.user.User;
import com.cupcodeteam.treasuristservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JdbcUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String authenticate) throws UsernameNotFoundException {
        User user = null;

        if (authenticate != null) {
            user = userRepository.findOneByUsername(authenticate);
        } else {
            throw new UsernameNotFoundException("Username not found");
        }

        return user;
    }
}