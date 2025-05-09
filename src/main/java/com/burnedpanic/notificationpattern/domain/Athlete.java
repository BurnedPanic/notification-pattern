package com.burnedpanic.notificationpattern.domain;

import com.burnedpanic.notificationpattern.domain.base.AbstractAggregateRoot;
import com.burnedpanic.notificationpattern.domain.enumerate.Category;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

public class Athlete extends AbstractAggregateRoot<String> {

    private PersonalData personalData;

    private Category category;

    private Athlete(String athleteId,
                    PersonalData personalData) {
        super(athleteId);
        this.personalData = requireNonNull(
                personalData,
                "The personalData cannot be null."
        );
        this.category = Arrays.stream(Category.values())
                .filter(c -> personalData.age() >= c.minAge() && personalData.age() <= c.maxAge())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Category not found for the age: " + personalData.age()));
    }

    public static Athlete create(final String athleteId,
                                 final PersonalData personalData) {
        return new Athlete(
                athleteId,
                personalData
        );
    }

    public void updateEmail(final String newEmail) {
        this.personalData.updateEmail(newEmail);
    }

    public PersonalData personalData() {
        return personalData;
    }

    public Category category() {
        return category;
    }
}
