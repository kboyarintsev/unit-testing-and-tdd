package com.acme.banking.dbo.domain;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ClientTest {

    @Test
    public void shouldCreateClientWithCorrectInfoWhenCorrectDataProvided() {
        // When
        Client sut = new Client(1, "dummy name");

        // Then
        MatcherAssert.assertThat(sut,
                Matchers.allOf(
                        Matchers.notNullValue(),
                        Matchers.hasProperty("id", Matchers.equalTo(1)),
                        Matchers.hasProperty("name", Matchers.equalTo("dummy name"))
                )
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100, Integer.MIN_VALUE})
    public void shouldShowErrorWhenIdIsNotValid(int invalidId) {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Client(invalidId, "dummy name")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "TooLongStringTooLongStringTooLongStringTooLongStringTooLongStringTooLongStringTooLongString"})
    public void shouldErrorWhenNameIsNotCorrect(String name) {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Client(1, name)
        );
    }

    @Test
    public void shouldThrowExceptionWhenNameIsNull() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Client(1, null)
        );
    }
}