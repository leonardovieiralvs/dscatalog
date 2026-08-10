package com.lsouzadev.aula.dto;

import com.lsouzadev.aula.entity.Role;
import jakarta.validation.constraints.NotNull;

public class RoleDto {

    @NotNull(message = "Campo obrigatorio")
    private Long id;
    private String authority;

    public RoleDto(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDto(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }

    public RoleDto() {
    }

    public Long getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }
}
