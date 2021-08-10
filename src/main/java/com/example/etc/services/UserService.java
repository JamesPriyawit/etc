package com.example.etc.services;

import com.example.etc.configs.ServerSecurityConfig;
import com.example.etc.entities.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import javax.servlet.http.HttpServletRequest;

public class UserService {
    @Autowired
    private DelegatingPasswordEncoder delegatingPasswordEncoder;
    @Autowired
    private ServerSecurityConfig serverSecurityConfig;
    @Autowired
    private DefaultTokenServices tokenServices;

    public static org.slf4j.Logger logger = LoggerFactory.getLogger(UserService.class);

    public void logout(
            HttpServletRequest request
    ) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            tokenServices.revokeToken(tokenValue);
        }
    }

    public User get(Authentication authentication) {
        User u = (User) authentication.getPrincipal();
        return u;
    }

    public User auth(
            String username,
            String password
    ){
        User user = null;
//        emailService.sendEmail("subscription_confirm_weak.html", "ตรวจสอบและยืนยันรายการคำสั่งซื้อ", data, emails, null,null);
        return user;
    }
}
