package com.burnedpanic.notificationpattern.domain;

import com.burnedpanic.notificationpattern.domain.base.ValueObject;
import com.burnedpanic.notificationpattern.domain.notification.DomainExceptionNotification;
import com.burnedpanic.notificationpattern.domain.notification.EmailValidation;

public class Contact implements ValueObject {

    private String email;

    private String mobileNumber;

    private String address;

    private Contact(String email,
                    String mobileNumber,
                    String address) {
        DomainExceptionNotification notification = new DomainExceptionNotification(Contact.class);

        this.email = notification.validateAndSet("email", email, new EmailValidation());
        this.mobileNumber = mobileNumber;
        this.address = address;

        notification.validateDomainObject();
    }

    public static Contact of(final String email,
                             final String mobileNumber,
                             final String address) {
        return new Contact(
                email,
                mobileNumber,
                address
        );
    }

    public String email() {
        return email;
    }

    public String mobileNumber() {
        return mobileNumber;
    }

    public String address() {
        return address;
    }
}
