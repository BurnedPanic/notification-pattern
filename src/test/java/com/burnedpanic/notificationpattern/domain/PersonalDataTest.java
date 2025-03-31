package com.burnedpanic.notificationpattern.domain;

import com.burnedpanic.notificationpattern.domain.notification.DomainViolationException;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PersonalDataTest {

    @Test
    void givenValidInputs_whenCreatePersonalDataIsCalled_thenPersonalDataIsReturned() {
        // Given
        Contact contact = Contact.of("johndoe@gmail.com", null, null);

        // When
        PersonalData personalData = PersonalData.of("123", "John", 25, contact);

        // Then
        assertEquals("123", personalData.id());
        assertEquals("John", personalData.name());
        assertEquals(25, personalData.age());
        assertThat(contact).usingRecursiveComparison().withStrictTypeChecking().isEqualTo(personalData.contact());
    }

    @Test
    void giveNewEmail_whenUpdateEmailIsCalled_thenEmailIsUpdated() {
        // Given
        String expected = "john@gmail.com";
        Contact contact = Contact.of("johndoe@gmail.com", null, null);
        PersonalData personalData = PersonalData.of("123", "John", 25, contact);

        // When
        personalData.updateEmail(expected);

        // Then
        assertNotNull(personalData);
        assertEquals(expected, personalData.contact().email());
    }

    @Test
    void givenInvalidInputs_whenCreateContactIsCalled_thenExceptionIsThrown() {
        DomainViolationException exception = assertThrows(
                DomainViolationException.class,
                () -> PersonalData.of("1234", null, 0, null)
        );

        assertEquals("Violation of Domain rules in object PersonalData", exception.getMessage());
        assertEquals(2, exception.getViolations().size());
        assertThat(exception.getViolations()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(Set.of(
                new DomainViolationException.Violation("contact", "field cannot be null"),
                new DomainViolationException.Violation("name", "field cannot be blank")
        ));
    }


}
