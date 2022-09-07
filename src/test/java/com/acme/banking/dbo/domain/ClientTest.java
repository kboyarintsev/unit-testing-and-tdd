package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    public void shouldCreateClientWithCorrectInfoWhenCorrectDataProvided() {
        // When
        Client sut = new Client(1, "dummy name");

        // Then
        Assertions.assertNotNull(sut);
        Assertions.assertEquals(1, sut.getId());
        Assertions.assertEquals("dummy name", sut.getName());
    }

    @Test
    public void shouldThrowExceptionWhenIdIsZero() {
        //Given
        Client sut;

        // When
        try {
            sut = new Client(0, "dummy name");
        } catch (IllegalArgumentException e) {
            sut = null;
        }

        // Then
        Assertions.assertNull(sut);
    }

    @Test
    public void shouldThrowExceptionWhenIdIsNegative() {
        //Given
        Client sut;

        // When
        try {
            sut = new Client(-1, "dummy name");
        } catch (IllegalArgumentException e) {
            sut = null;
        }

        // Then
        Assertions.assertNull(sut);
    }

    @Test
    public void shouldThrowExceptionWhenNameIsNull() {
        //Given
        Client sut;

        // When
        try {
            sut = new Client(1, null);
        } catch (IllegalArgumentException e) {
            sut = null;
        }

        // Then
        Assertions.assertNull(sut);
    }

    @Test
    public void shouldThrowExceptionWhenNameIsEmpty() {
        //Given
        Client sut;

        // When
        try {
            sut = new Client(1, "");
        } catch (IllegalArgumentException e) {
            sut = null;
        }

        // Then
        Assertions.assertNull(sut);
    }

    @Test
    public void shouldThrowExceptionWhenNameIsToLong() {
        //Given
        Client sut;

        // When
        try {
            sut = new Client(1, "aksdfkjaslkfjksjfoksjakfjsdkfjasdjfjas;djfasjdfkjaskjfkasjkdfjkasjdfkl;jsakfjkasjdfkkjsadkfljsla");
        } catch (IllegalArgumentException e) {
            sut = null;
        }

        // Then
        Assertions.assertNull(sut);
    }
}