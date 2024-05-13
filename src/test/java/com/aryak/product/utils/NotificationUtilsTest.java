package com.aryak.product.utils;

import com.aryak.product.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationUtilsTest {

    @InjectMocks
    NotificationUtils notificationUtils;

    @Spy
    CustomerRepository customerRepository;

    @Test
    void testIsBlackListed_success() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        when(customerRepository.checkIfBlacklisted("blacklisted@gmail.com")).thenReturn(true);
        Method isBlackListed = NotificationUtils.class.getDeclaredMethod("isBlackListed", String.class);
        isBlackListed.setAccessible(true);
        boolean result = (boolean) isBlackListed.invoke(notificationUtils, "blacklisted@gmail.com");
        assertThat(result).isTrue();
    }

    @Test
    void testIsBlackListed_failure() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        when(customerRepository.checkIfBlacklisted("test@gmail.com")).thenReturn(false);
        Method isBlackListed = NotificationUtils.class.getDeclaredMethod("isBlackListed", String.class);
        isBlackListed.setAccessible(true);
        boolean result = (boolean) isBlackListed.invoke(notificationUtils, "test@gmail.com");
        assertThat(result).isFalse();
    }

}