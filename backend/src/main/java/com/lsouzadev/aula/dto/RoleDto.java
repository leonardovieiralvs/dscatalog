package com.lsouzadev.aula.dto;

import com.lsouzadev.aula.entity.Role;

public class RoleDto {

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
