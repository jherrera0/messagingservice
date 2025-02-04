package com.backendchallenge.messagingservice.application.jpa.entity;

import com.backendchallenge.messagingservice.domain.until.ConstTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationPinIdTest {

    @Test
    void equals_sameObject_returnsTrue() {
        NotificationPinId id = new NotificationPinId(ConstTest.PHONE, ConstTest.PIN_TEST);
        assertEquals(id, id);
    }
    @Test
    void equals_differentObjectSameValues_returnsTrue() {
        NotificationPinId id1 = new NotificationPinId(ConstTest.PHONE, ConstTest.PIN_TEST);
        NotificationPinId id2 = new NotificationPinId(ConstTest.PHONE, ConstTest.PIN_TEST);
        assertEquals(id1, id2);
    }
    @Test
    void equals_differentObjectDifferentValues_returnsFalse() {
        NotificationPinId id1 = new NotificationPinId(ConstTest.PHONE, ConstTest.PIN_TEST);
        NotificationPinId id2 = new NotificationPinId(ConstTest.PHONE2, ConstTest.PIN_TEST2);
        assertNotEquals(id1, id2);
    }
    @Test
    void equals_nullObject_returnsFalse() {
        NotificationPinId id = new NotificationPinId(ConstTest.PHONE, ConstTest.PIN_TEST);
        assertNotEquals(null, id);
    }
    @Test
    void equals_differentClass_returnsFalse() {
        NotificationPinId id = new NotificationPinId(ConstTest.PHONE, ConstTest.PIN_TEST);
        String other = ConstTest.EMPTY_TEST;
        assertNotEquals(other, id);
    }
    @Test
    void hashCode_sameValues_returnsSameHashCode() {
        NotificationPinId id1 = new NotificationPinId(ConstTest.PHONE, ConstTest.PIN_TEST);
        NotificationPinId id2 = new NotificationPinId(ConstTest.PHONE, ConstTest.PIN_TEST);
        assertEquals(id1.hashCode(), id2.hashCode());
    }
    @Test
    void hashCode_differentValues_returnsDifferentHashCode() {
        NotificationPinId id1 = new NotificationPinId(ConstTest.PHONE, ConstTest.PIN_TEST);
        NotificationPinId id2 = new NotificationPinId(ConstTest.PHONE2, ConstTest.PIN_TEST2);
        assertNotEquals(id1.hashCode(), id2.hashCode());
    }
}