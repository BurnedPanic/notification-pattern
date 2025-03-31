package com.burnedpanic.notificationpattern.domain;

import com.burnedpanic.notificationpattern.domain.base.AbstractEntity;
import com.burnedpanic.notificationpattern.domain.notification.DomainExceptionNotification;

import static java.util.Objects.requireNonNull;

public class PersonalData extends AbstractEntity<String> {

    private String name;

    private int age;

    private Contact contact;

    private PersonalData(String citizenId,
                         String name,
                         int age,
                         Contact contact) {
        super(citizenId);
        DomainExceptionNotification notification = new DomainExceptionNotification(PersonalData.class);

        this.contact = notification.validateAndSet("contact", contact, DomainExceptionNotification.ValidationEnum.NULL);
        this.age = age;
        this.name = notification.validateAndSet("name", name, DomainExceptionNotification.ValidationEnum.BLANK);

        notification.validateDomainObject();
    }

    public static PersonalData of(final String citizenId,
                                  final String name,
                                  final int age,
                                  final Contact contact) {
        return new PersonalData(
                citizenId,
                name,
                age,
                contact
        );
    }

    protected void updateEmail(final String newEmail) {
        this.contact = Contact.of(
                newEmail,
                contact.mobileNumber(),
                contact.address()
        );
    }

    public Contact contact() {
        return contact;
    }

    public String name() {
        return name;
    }

    public int age() {
        return age;
    }
}
