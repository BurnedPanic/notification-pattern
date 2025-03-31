package com.burnedpanic.notificationpattern.domain.notification;

public interface DomainValidation {

    <T> String getErrorMessage(T obj);
    public abstract <T> boolean isValid(T obj);

}
