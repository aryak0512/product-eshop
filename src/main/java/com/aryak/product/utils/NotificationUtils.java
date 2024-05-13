package com.aryak.product.utils;

import com.aryak.product.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * The type Notification utils.
 */
@Slf4j
public class NotificationUtils {

    private CustomerRepository customerRepository;

    /**
     * Instantiates a new Notification utils.
     *
     * @param customerRepository the customer repository
     */
    public NotificationUtils(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Send email.
     *
     * @param emailId the email id
     */
    public void sendEmail(String emailId) {
        if ( !isBlackListed(emailId) ) {
            // sending email...
            trigger(emailId);
        } else {
            // do nothing
        }
    }

    private void trigger(String emailId) {
        log.info("Im sending an email to : {}", emailId);
    }

    private boolean isBlackListed(String emailId) {
        return customerRepository.checkIfBlacklisted(emailId);
    }
}
