package com.example.hw27security.Service;

import com.example.hw27security.Api.ApiException;
import com.example.hw27security.Model.Blog;
import com.example.hw27security.Model.User;
import com.example.hw27security.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    public void register(User user){

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
       authRepository.save(user);

    }

}
