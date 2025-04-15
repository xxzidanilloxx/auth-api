package br.com.xxzidanilloxx.authapi.dto;

public record AuthenticationRequestDTO(
        String login,
        String password) {
}
