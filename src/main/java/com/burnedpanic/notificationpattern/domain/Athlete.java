package com.burnedpanic.notificationpattern.domain;

import com.burnedpanic.notificationpattern.domain.enumerate.Category;
import com.burnedpanic.notificationpattern.domain.notification.DomainExceptionNotification;
import com.burnedpanic.notificationpattern.domain.base.AbstractAggregateRoot;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class Athlete extends AbstractAggregateRoot<String> {

    private PersonalData personalData;

    private Category category;

    private Athlete(String athleteId,
                    PersonalData personalData) {
        super(athleteId);

        DomainExceptionNotification notification = new DomainExceptionNotification(Athlete.class);
        this.personalData = notification.validateAndSet("personalData", personalData, DomainExceptionNotification.ValidationEnum.NULL);

        if (nonNull(personalData)) {
            this.category = notification.validateAndSet("category", Category.findCategoryFromAge(personalData().age()), DomainExceptionNotification.ValidationEnum.NULL);
        }

        notification.validateDomainObject();
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
