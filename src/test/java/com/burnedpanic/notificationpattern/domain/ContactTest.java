package com.burnedpanic.notificationpattern.domain;

import com.burnedpanic.notificationpattern.domain.notification.DomainViolationException;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    void givenValidInputs_whenCreateContactIsCalled_thenContactIsReturned() {
        Contact contact = Contact.of("johndoe@gmail.com", null, null);

        assertEquals("johndoe@gmail.com", contact.email());
        assertNull(contact.address());
        assertNull(contact.mobileNumber());
    }

    @Test
    void givenInvalidInputs_whenCreateContactIsCalled_thenExceptionIsThrown() {
        DomainViolationException exception = assertThrows(
                DomainViolationException.class,
                () -> Contact.of(null, null, null)
        );

        assertEquals("Violation of Domain rules in object Contact", exception.getMessage());
        assertEquals(1, exception.getViolations().size());
        assertThat(exception.getViolations())
                .usingRecursiveComparison()
                .isEqualTo(Set.of(
                new DomainViolationException.Violation("email", "Email: null is null or does not conform to specific format")
        ));
    }

}
