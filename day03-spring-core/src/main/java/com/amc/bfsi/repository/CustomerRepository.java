package com.amc.bfsi.repository;

import com.amc.bfsi.model.Customer;
import java.util.List;

/** The interface is what the service depends on - this is the loose coupling. */
public interface CustomerRepository {
    void save(Customer customer);
    Customer findById(int id);
    List<Customer> findAll();
}
