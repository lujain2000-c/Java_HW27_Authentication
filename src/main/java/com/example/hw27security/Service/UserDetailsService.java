package com.example.hw27security.Service;

import com.example.hw27security.Api.ApiException;
import com.example.hw27security.Model.User;
import com.example.hw27security.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{
    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = authRepository.findUsersByUsername(username);

        if(user == null){
            throw new ApiException(("npt found"));
        }
        return user;

    }
}
