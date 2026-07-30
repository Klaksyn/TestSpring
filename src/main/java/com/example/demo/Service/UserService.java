package com.example.demo.Service;

import com.example.demo.Entity.Auth.AppUser;
import com.example.demo.Entity.Auth.UserPrincipal;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        AppUser appUser =  userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found by email: %s!"
                        .formatted(email)));

        return new UserPrincipal(appUser);
    }
}
