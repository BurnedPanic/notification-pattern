package com.burnedpanic.notificationpattern.domain;

import com.burnedpanic.notificationpattern.domain.base.AbstractEntity;

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
        this.age = age;
        this.contact = requireNonNull(
                contact,
                "The contact cannot be null."
        );

        this.name = requireNonNull(
                name,
                "The name cannot be null."
        );
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
