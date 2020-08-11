package com.suju.vote.utils;

import com.suju.vote.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
    public static User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        User user = new User();
        user.setName("system");
        return user;
    }
}
