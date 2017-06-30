package com.codeup.svcs;

import com.codeup.models.User;
import com.codeup.repositories.RolesRepository;
import com.codeup.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by roxana on 6/29/17.
 */
@Service("customUserDetailsService")
public class UserDetailsLoader implements UserDetailsService {
    private final UsersRepository users;
    private final RolesRepository roles;

    @Autowired
    public UserDetailsLoader(UsersRepository users, RolesRepository roles) {
        this.users = users;
        this.roles = roles;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        List<String> userRoles = roles.ofUserWith(username);
        return new UserWithRoles(user, userRoles);
    }
}
