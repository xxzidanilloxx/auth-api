package br.com.xxzidanilloxx.authapi.dto;

import br.com.xxzidanilloxx.authapi.enumeration.UserRole;

public record RegisterRequestDTO(
        String login,
        String password,
        UserRole role) {
}
