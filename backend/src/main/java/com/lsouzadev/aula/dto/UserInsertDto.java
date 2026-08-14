package com.lsouzadev.aula.dto;

import com.lsouzadev.aula.services.validation.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@UserInsertValid
public class UserInsertDto extends UserDto {

    @NotBlank(message = "Campo obrigatorio")
    @Size(min = 8, max = 30, message = "Senha deve ter entre 8 e 30 caracteres")
    private String password;


    public UserInsertDto() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
