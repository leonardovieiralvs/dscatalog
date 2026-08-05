package com.lsouzadev.aula.services;

import com.lsouzadev.aula.dto.RoleDto;
import com.lsouzadev.aula.dto.UserDto;
import com.lsouzadev.aula.dto.UserInsertDto;
import com.lsouzadev.aula.entity.Role;
import com.lsouzadev.aula.entity.User;
import com.lsouzadev.aula.exceptions.NotFoundException;
import com.lsouzadev.aula.repository.RoleRepository;
import com.lsouzadev.aula.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder enconde) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = enconde;
    }

    public List<UserDto> findAll() {
        List<User> listEntity = userRepository.findAll();

        return listEntity.stream().map(x -> new UserDto(x)).toList();
    }

    public Page<UserDto> findAllPaged(Pageable pageable) {
        Page<User> list = userRepository.findAll(pageable);
        return list.map(x -> new UserDto(x));
    }

    public UserDto findById(Long id) {
        User entity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
        return new UserDto(entity);
    }


    public UserDto insert(UserInsertDto userDto) {

        User entity = new User();
        copyDtoToEntity(userDto, entity);
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User save = userRepository.save(entity);
        return new UserDto(save);

    }

    public UserDto update(Long id, UserDto userDto) {

        User entity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
        copyDtoToEntity(userDto, entity);

        User save = userRepository.save(entity);
        return new UserDto(save);

    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Id not found " + id);
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Integrity violation");
        }
    }

    public void copyDtoToEntity(UserDto userDto, User user) {

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());


        user.getRoles().clear();
        for (RoleDto roleDto : userDto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDto.getId());
            user.getRoles().add(role);
        }

    }
}
