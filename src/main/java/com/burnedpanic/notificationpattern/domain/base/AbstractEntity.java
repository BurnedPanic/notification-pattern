package com.burnedpanic.notificationpattern.domain.base;

import java.util.Objects;

public abstract class AbstractEntity<T> implements DomainObject {

    private final T id;

    public T id() {
        return this.id;
    }
    protected AbstractEntity(T id) {
        this.id = Objects.requireNonNull(id, "id must not be null");
    }
}
