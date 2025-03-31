package com.burnedpanic.notificationpattern.domain;

import com.burnedpanic.notificationpattern.domain.enumerate.Category;
import com.burnedpanic.notificationpattern.domain.notification.DomainViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AthleteTest {

    @Test
    void givenValidInputs_whenCreateAthleteIsCalled_thenAthleteIsReturned() {
        // Given
        String expected = "POR001";
        Contact contact = Contact.of("johndoe@gmail.com", null, null);
        PersonalData personalData = PersonalData.of("123", "John", 25, contact);

        // When
        Athlete actual = Athlete.create(expected, personalData);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual.id());
        Assertions.assertEquals(Category.ELITE, actual.category());
        assertThat(actual.personalData()).usingRecursiveComparison().isEqualTo(personalData);
    }

    @Test
    void givenInvalidInputs_whenCreateAthleteIsCalled_thenExceptionIsThrown() {
        // Given
        String id = "POR001";

        // When
        DomainViolationException exception = assertThrows(
                DomainViolationException.class,
                () -> Athlete.create(id, null)
        );

        // Then
        assertEquals("Violation of Domain rules in object Athlete", exception.getMessage());
        assertEquals(1, exception.getViolations().size());
        assertThat(exception.getViolations()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(Set.of(
                new DomainViolationException.Violation("personalData", "field cannot be null")
        ));
    }

    @Test
    void giveNewEmail_whenUpdateEmailIsCalled_thenEmailIsUpdated() {
        // Given
        String expected = "john@gmail.com";
        Contact contact = Contact.of("johndoe@gmail.com", null, null);
        PersonalData personalData = PersonalData.of("123456789", "John Doe", 28, contact);
        Athlete actual = Athlete.create("1", personalData);

        // When
        actual.updateEmail(expected);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual.personalData().contact().email());
    }

    @Test
    void giveInvalidEmail_whenUpdateEmailIsCalled_thenIllegalArgumentExceptionThrown() {
        // Given
        String invalidEmail = "johngmail.com";
        Contact contact = Contact.of("johndoe@gmail.com", null, null);
        PersonalData personalData = PersonalData.of("123456789", "John Doe", 28, contact);
        Athlete actual = Athlete.create("1", personalData);

        // When
        DomainViolationException exception = assertThrows(
                DomainViolationException.class,
                () -> actual.updateEmail(invalidEmail)
        );

        // Then
        assertEquals("Violation of Domain rules in object Contact", exception.getMessage());
        assertEquals(1, exception.getViolations().size());
        assertThat(exception.getViolations()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(Set.of(
                new DomainViolationException.Violation("email", "Email: johngmail.com is null or does not conform to specific format")
        ));
    }
}
