package com.aryak.product.repository;

public interface CustomerRepository {

    boolean checkIfBlacklisted(String emailId);
}
