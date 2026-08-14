package com.lsouzadev.aula.services.validation;

import com.lsouzadev.aula.dto.FieldMessage;
import com.lsouzadev.aula.dto.UserInsertDto;
import com.lsouzadev.aula.entity.User;
import com.lsouzadev.aula.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDto> {

    private UserRepository userRepository;

    public UserInsertValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDto dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();


        if (userRepository.findByEmail(dto.getEmail()) != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.message()).addPropertyNode(e.fieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}