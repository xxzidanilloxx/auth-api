package br.com.xxzidanilloxx.authapi.service;

import br.com.xxzidanilloxx.authapi.dto.AuthenticationRequestDTO;
import br.com.xxzidanilloxx.authapi.dto.LoginResponseDTO;
import br.com.xxzidanilloxx.authapi.dto.RegisterRequestDTO;
import br.com.xxzidanilloxx.authapi.entity.User;
import br.com.xxzidanilloxx.authapi.repository.UserRepository;
import br.com.xxzidanilloxx.authapi.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public void register(RegisterRequestDTO data) {
        if (userRepository.findByLogin(data.login()) != null) {
            throw new IllegalArgumentException("Login já existe");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.login(), encryptedPassword, data.role());

        userRepository.save(user);
    }

    public LoginResponseDTO login(AuthenticationRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token);
    }
}
