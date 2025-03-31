package com.burnedpanic.notificationpattern.domain.notification;

import java.util.HashSet;
import java.util.Set;

public class DomainViolationException extends RuntimeException {

    private Set<Violation> violations;

    public DomainViolationException(Class claz) {
        super(String.format("Violation of Domain rules in object %s", claz.getSimpleName()));
        this.violations = new HashSet<>();
    }

    public void addViolation(Violation violation) {
        this.violations.add(violation);
    }

    public Set<Violation> getViolations() {
        return violations;
    }

    public record Violation(
            String field,
            String violation
    ) { }

}
