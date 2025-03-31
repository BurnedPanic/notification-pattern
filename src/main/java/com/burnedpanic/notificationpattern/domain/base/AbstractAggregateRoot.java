package com.burnedpanic.notificationpattern.domain.base;

public abstract class AbstractAggregateRoot<T>  extends AbstractEntity<T> {
    protected AbstractAggregateRoot(T id) {
        super(id);
    }
}
