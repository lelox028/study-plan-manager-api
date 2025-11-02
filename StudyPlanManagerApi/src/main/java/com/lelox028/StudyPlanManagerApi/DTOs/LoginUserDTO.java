package com.lelox028.StudyPlanManagerApi.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginUserDTO {
    @NotBlank(message = "El username no puede estar vacío")
    @Size(max = 45, message = "El username debe tener máximo 45 caracteres")
    private String username;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    // Getters y setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}