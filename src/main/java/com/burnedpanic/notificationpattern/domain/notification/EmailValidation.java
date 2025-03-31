package com.burnedpanic.notificationpattern.domain.notification;

import java.util.regex.Pattern;

import static java.util.Objects.nonNull;

public class EmailValidation implements DomainValidation {


    @Override
    public <T> boolean isValid(T obj) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return nonNull(obj) && Pattern.compile(regexPattern)
                .matcher((String) obj)
                .matches();
    }

    @Override
    public <T> String getErrorMessage(T obj) {
        return String.format("Email: %s is null or does not conform to specific format", obj);
    }

}
