package com.afc.springreact.shared;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.afc.springreact.file.FileService;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class FileTypeValidator implements ConstraintValidator<FileType, String> {

    FileService fileService;

    String[] types;

    @Autowired
    FileTypeValidator(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public void initialize(FileType constraintAnnotation) {
        this.types = constraintAnnotation.types();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        String fileType = fileService.detectType(value);
        for (String type : types) {
            if (fileType.contains(type)) {
                return true;
            }
        }

        String supportedTypes = Arrays.stream(this.types).collect(Collectors.joining(", "));
        
        context.disableDefaultConstraintViolation();

        HibernateConstraintValidatorContext hibernateContext = context.unwrap(HibernateConstraintValidatorContext.class);
        hibernateContext.addMessageParameter("types", supportedTypes);
        hibernateContext.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addConstraintViolation();

        return false;
    }
}
