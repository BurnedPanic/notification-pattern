package com.burnedpanic.notificationpattern.domain.notification;

import static io.micrometer.common.util.StringUtils.isBlank;
import static java.util.Objects.nonNull;

public class DomainExceptionNotification {

    private DomainViolationException exception;

    public <T> DomainExceptionNotification(Class<T> claz) {
        this.exception = new DomainViolationException(claz);
    }

    public <T> T validateAndSet(String objName, T obj, DomainValidation violation) {
        if (!violation.isValid(obj)) {
            this.exception.addViolation(new DomainViolationException.Violation(objName, violation.getErrorMessage(obj)));
        }

        return obj;
    }

    public void validateDomainObject() {
        if (!this.exception.getViolations().isEmpty()) {
            throw this.exception;
        }
    }

    public enum ValidationEnum implements DomainValidation {
        NULL("field cannot be null") {
            @Override
            public <T> boolean isValid(T obj) {
                return nonNull(obj);
            }
        },
        BLANK("field cannot be blank") {
            @Override
            public <T> boolean isValid(T obj) {
                return nonNull(obj) && !isBlank((String) obj);
            }
        };

        ValidationEnum(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        private final String errorMessage;

        @Override
        public <T> String getErrorMessage(T obj) {
            return errorMessage;
        }

        @Override
        public abstract <T> boolean isValid(T obj);

    }

}
