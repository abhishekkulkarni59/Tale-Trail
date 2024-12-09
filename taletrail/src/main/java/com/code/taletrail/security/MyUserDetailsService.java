package com.code.taletrail.security;

import com.code.taletrail.exception.ResourceNotFoundException;
import com.code.taletrail.model.User;
import com.code.taletrail.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", " Email " + username, 0));
        return new UserPrincipal(user);

    }

}
