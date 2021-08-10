package com.example.etc.configs;

import com.example.etc.entities.User;
import com.example.etc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;


@Transactional
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // get data from obj authentication
        UserService userService = (UserService) com.example.etc.configs.SpringUtils.ctx.getBean(UserService.class);
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = userService.auth(username, password);
        return this.createSuccessAuthentication(user, authentication, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, User user) {

        Collection<? extends GrantedAuthority> co = null;

        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(principal, authentication.getCredentials(), co); // co -> user.getAuthorities()
        result.setDetails(authentication.getDetails());

        return result;
    }

}
